package org.matt.auth;import com.opensymphony.xwork2.ActionSupport;import com.opensymphony.xwork2.Preparable;import org.apache.logging.log4j.LogManager;import org.apache.logging.log4j.Logger;import org.apache.shiro.SecurityUtils;import org.apache.shiro.authc.AuthenticationException;import org.apache.shiro.authc.IncorrectCredentialsException;import org.apache.shiro.authc.LockedAccountException;import org.apache.shiro.authc.UnknownAccountException;import org.apache.shiro.authc.UsernamePasswordToken;import org.apache.shiro.session.Session;import org.apache.shiro.subject.Subject;public class ShiroLogInAction extends ActionSupport implements Preparable {	private static final long serialVersionUID = 1L;	private static final transient Logger log = LogManager.getLogger(ShiroLogInAction.class);	private String username;	private String password;	private transient Subject shiroUser;	public void prepare() throws Exception {		this.shiroUser = SecurityUtils.getSubject();	}	public String execute() {		String result = "error";		if (this.shiroUser != null) {			Session session = this.shiroUser.getSession();			session.setAttribute("MyUsername", this.username);			log.info("Saving 'username' value to session [" + this.username + "]");			if (!this.shiroUser.isAuthenticated()) {				UsernamePasswordToken token = new UsernamePasswordToken(this.username, this.password);				token.setRememberMe(true);				try {					this.shiroUser.login(token);					System.out.println("logging in user: " + shiroUser.getPrincipal().toString());					result = "success";				} catch (UnknownAccountException uae) {					addActionError("There is no user with username of '" + token.getPrincipal() + "'");					log.error(uae.getMessage());				} catch (IncorrectCredentialsException ice) {					addActionError("Password for account '" + token.getPrincipal() + "' was incorrect!");					log.error(ice.getMessage());				} catch (LockedAccountException lae) {					addActionError("The account for username '" + token.getPrincipal() + "' is locked.  "							+ "Please contact your administrator to unlock it.");					log.error(lae.getMessage());				} catch (AuthenticationException ae) {					addActionError("An authentication exception has occurred trying to login user: " + token.getPrincipal());					log.error(ae.getMessage());				}			} else if (this.shiroUser.isAuthenticated()) {				result = "success";			}		}		return result;	}	public Subject getShiroUser() {		return this.shiroUser;	}	public void setShiroUser(Subject shiroUser) {		this.shiroUser = shiroUser;	}	public String getUsername() {		return this.username;	}	public void setUsername(String username) {		this.username = username;	}	public String getPassword() {		return this.password;	}	public void setPassword(String password) {		this.password = password;	}}