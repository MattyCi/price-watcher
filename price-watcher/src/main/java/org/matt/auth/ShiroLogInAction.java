package org.matt.auth;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.matt.utils.PWConstants;

public class ShiroLogInAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private transient Subject shiroUser;

	public void prepare() throws Exception {
		this.shiroUser = SecurityUtils.getSubject();
	}

	public String execute() {
		String result = PWConstants.error;
		if (this.shiroUser != null) {
			Session session = this.shiroUser.getSession();
			session.setAttribute("UserMailID", this.username);
			// TODO: create a DAO to retrieve userID and set that value to the session as well

			if (!this.shiroUser.isAuthenticated()) {
				UsernamePasswordToken token = new UsernamePasswordToken(this.username, this.password);
				token.setRememberMe(true);
				try {
					this.shiroUser.login(token);
					result = PWConstants.success;
				} catch (Exception e) {
					e.printStackTrace();
					addActionError(PWConstants.noAccount);
					return PWConstants.error;
				}
			} else if (this.shiroUser.isAuthenticated()) {
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
}
