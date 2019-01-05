package org.matt.auth;

import org.apache.commons.validator.routines.EmailValidator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.utils.PWConstants;
import org.matt.utils.UserUtils;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;

import org.matt.utils.HibernateUtil;
import org.matt.daos.UserDAO;

public class ShiroRegisterAction extends ShiroBaseAction {
	private static final long serialVersionUID = -6328260956217475993L;
	private String username;
	private String password;
	private boolean errorsOccured = false;
	
	// TODO: Implement an email-verification step where we send the user a confirmation first
	// TODO: Convert guest users to registered users if their guest cookie exists
	public String execute() {
		
		// ensure the user is not already logged in
		if (this.shiroUser.isAuthenticated()) {
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
		
		username = username.toLowerCase();
		
		// convert guest user to registered user
		registerUser(username, password);
		
		this.userType = PWConstants.regUserType;
		
		if (errorsOccured) {
			addActionError(PWConstants.genericError);
			return PWConstants.error;
		}

		// if we reach this, user will now finally log in through struts2 action chaining
		return PWConstants.success;
	}

	public void registerUser(String email, String plainTextPassword) {
		this.regUser.setMailID(email);

		UserUtils.generatePassword(this.regUser, plainTextPassword);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(this.regUser);
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
	
}
