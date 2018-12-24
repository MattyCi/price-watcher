package org.matt.auth;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.models.Reguser;
import org.matt.utils.PWConstants;
import org.matt.utils.UserUtils;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import org.matt.utils.HibernateUtil;
import org.matt.daos.UserDAO;

public class ShiroRegisterAction extends ShiroBaseAction {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean errorsOccured = false;
	private Subject shiroUser;
	private char userType;
	private Reguser regUser;
	
	// TODO: Implement an email-verification step where we send the user a confirmation first
	// TODO: Convert guest users to registered users if their guest cookie exists
	public String execute() {
		
		// ensure the user is not already logged in
		if (this.shiroUser == null) {
			addActionError(PWConstants.alreadyLoggedIn);
			return PWConstants.error;
		}
		
		// validate email is valid
		EmailValidator emailValidator = EmailValidator.getInstance();
		
		// isValid will also protect against null strings (no null checks needed)
		if (!emailValidator.isValid(username)) {
			addActionError(PWConstants.invalidEmail);
			return PWConstants.error;
		}
		
		// validate password strength
		PasswordValidator validator = UserUtils.createPasswordValidator();
		RuleResult result = validator.validate(new PasswordData(password));
		
		if (!result.isValid()) {
			addActionError(validator.getMessages(result).get(0));
			return PWConstants.error;
		}

		// ensure user does not already exist
		if(UserDAO.getUserByEmail(username) != null) {
			addActionError(PWConstants.genericError);
			return PWConstants.error;
		}
		
		registerUser(username, password);
		
		if (errorsOccured) {
			addActionError(PWConstants.genericError);
			return PWConstants.error;
		}

		// if we reach this, user will now finally log in through struts2 action chaining
		return PWConstants.success;
	}

	public void registerUser(String email, String plainTextPassword) {
		Reguser user = new Reguser();
		user.setMailID(email);

		UserUtils.generatePassword(user, plainTextPassword);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			//TODO: Set up logging here!!!
			tx.rollback();
			errorsOccured = true;
		} finally {
			session.close();
		}
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
	public void setShiroUser(Subject shiroUser) {
		this.shiroUser = shiroUser;
	}
	
	public Subject getShiroUser() {
		return shiroUser;
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
