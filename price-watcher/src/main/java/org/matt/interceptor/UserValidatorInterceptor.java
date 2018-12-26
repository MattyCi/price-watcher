package org.matt.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.matt.auth.ShiroBaseAction;
import org.matt.daos.UserDAO;
import org.matt.models.Reguser;
import org.matt.utils.PWConstants;
import org.matt.utils.UserUtils;

/**
 * Determines whether the current executing user is registered or not. It the
 * user is a guest, either retrieve or create a new record in the users table
 * depending on the cookies that exist from the client. Inserts the current
 * Reguser user into the value stack so that it can be injected into Struts 2
 * actions should they have a JavaBeans setter
 * <code>setRegUser(org.matt.models.Reguser regUser)</code>.
 */
public class UserValidatorInterceptor implements Interceptor {
	private static final long serialVersionUID = -4919863537675303155L;

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInvocation) throws Exception {

		if ((actionInvocation.getAction() instanceof ShiroBaseAction)) {
			Subject shiroUser = SecurityUtils.getSubject();
			char userType;
			String guestUserIDFromCookie;
			String guestUserTokenFromCookie;
			Reguser regUser;

			/*
			 * determine whether this is a guest or registered user if guest user, decide if
			 * we need a new guest user record or not
			 */
			if (shiroUser.getPrincipal() == null) {
				System.out.println("Guest user running command!");

				// check for guest user cookie
				guestUserIDFromCookie = UserUtils
						.retrieveGuestUserIDFromCookies(ServletActionContext.getRequest().getCookies());

				if (guestUserIDFromCookie == null) {
					// user is not registered and they are a new guest
					System.out.println("user is not registered and they are a new guest");
					userType = PWConstants.newGuestUserType;
					regUser = new Reguser();
					UserUtils.createGuestUser(regUser);
s
					// set guest cookies
					Cookie guestIDCookie = new Cookie(PWConstants.guestIDCookieName, regUser.getUserID().toString());
					guestIDCookie.setPath("/");
					guestIDCookie.setMaxAge(60 * 60 * 24 * 365);
					
					Cookie guestTokenCookie = new Cookie(PWConstants.guestTokenCookieName, regUser.getUserToken());
					guestTokenCookie.setPath("/");
					guestTokenCookie.setMaxAge(60 * 60 * 24 * 365);
					
					ServletActionContext.getResponse().addCookie(guestIDCookie);
					ServletActionContext.getResponse().addCookie(guestTokenCookie);
				} else {
					// user is not registered but they already have a guest ID
					System.out.println("user is not registered but they already have a guest ID");
					
					guestUserTokenFromCookie = UserUtils
							.retrieveGuestUserTokenFromCookies(ServletActionContext.getRequest().getCookies());
					
					/**
					 * Validate the cookies are authentic. Ensure the user ID is not
					 * of a registered user, then ensure the token matches the ID.
					 */
					if (UserDAO.isUserRegistered(Integer.parseInt(guestUserIDFromCookie))
							|| !UserDAO.isTokenValid(Integer.parseInt(guestUserIDFromCookie), guestUserTokenFromCookie)) {
						System.out.println("cookie tampering detected!");
						
						((ActionSupport) actionInvocation.getAction()).addActionError(PWConstants.cookieTampering);
						return "stdError";
					}

					regUser = UserDAO.getUserByID(Integer.parseInt(guestUserIDFromCookie));
					userType = PWConstants.guestUserType;
				}
			} else {
				// user is registered
				System.out.println("user is registered");
				userType = PWConstants.regUserType;
				regUser = UserDAO.getUserByEmail(shiroUser.getPrincipal().toString());
			}

			actionInvocation.getStack().setValue("userType", userType);
			actionInvocation.getStack().setValue("regUser", regUser);
			actionInvocation.getStack().setValue("shiroUser", shiroUser);
			System.out.println("shiroUser is: "+shiroUser.getPrincipal());
		}
		return actionInvocation.invoke();

	}
}
