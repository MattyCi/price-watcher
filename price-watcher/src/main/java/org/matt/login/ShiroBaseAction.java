package org.matt.login;import com.opensymphony.xwork2.ActionSupport;import org.apache.shiro.subject.Subject;public class ShiroBaseAction  extends ActionSupport{  private static final long serialVersionUID = 1L;  private transient Subject shiroUser;    public boolean isAuthenticated()  {    return (this.shiroUser != null) && (this.shiroUser.isAuthenticated());  }    public Subject getShiroUser()  {    return this.shiroUser;  }    public void setShiroUser(Subject shiroUser)  {    this.shiroUser = shiroUser;  }}