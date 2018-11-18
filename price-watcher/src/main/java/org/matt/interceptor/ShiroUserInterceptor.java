package org.matt.interceptor;import com.opensymphony.xwork2.ActionInvocation;import com.opensymphony.xwork2.interceptor.Interceptor;import org.apache.shiro.SecurityUtils;import org.apache.shiro.subject.Subject;import org.matt.auth.ShiroBaseAction;/** * Inserts the current Shiro user into the value stack so that it can be * injected into Struts 2 actions should they have a JavaBeans setter * <code>setShiroUser(org.apache.shiro.subject.Subject shiroUser)</code>. */public class ShiroUserInterceptor implements Interceptor {	private static final long serialVersionUID = 1L;	public void destroy() {	}	public void init() {	}	public String intercept (ActionInvocation actionInvocation) throws Exception {		if ((actionInvocation.getAction() instanceof ShiroBaseAction)) {			Subject shiroUser = SecurityUtils.getSubject();			actionInvocation.getStack().setValue("shiroUser", shiroUser);		}		return actionInvocation.invoke();	}}