package org.matt.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.matt.models.Reguser;
import org.matt.utils.PWConstants;
import org.matt.utils.UserUtils;

// TODO: Make this extend ShiroBaseAction
public class ShiroLogInAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private transient Subject shiroUser;
	private char userType;
	private Reguser regUser;

	public void prepare() throws Exception {
		this.shiroUser = SecurityUtils.getSubject();
	}

	public String execute() {
		String result = PWConstants.error;
		if (this.shiroUser != null) {
			if (!this.shiroUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(this.username.toLowerCase(), this.password);
				token.setRememberMe(true);
				try {
					this.shiroUser.login(token);
					UserUtils.deleteGuestCookies();
					
					result = PWConstants.success;
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(PWConstants.noAccount);
					return PWConstants.error;
				}
			} else if (this.shiroUser.isAuthenticated()) {
				UserUtils.deleteGuestCookies();
				result = PWConstants.success;
			}
		}
		addActionError(PWConstants.noAccount);
		return result;
	}

	public Subject getShiroUser() {
		return this.shiroUser;
	}

	public void setShiroUser(Subject shiroUser) {
		this.shiroUser = shiroUser;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// used by the interceptor
	public void setRegUser(Reguser regUser) {
		this.regUser = regUser;
	}

	public Reguser getRegUser() {
		return regUser;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}
	
}
