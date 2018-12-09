package org.matt.auth;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.models.Reguser;
import org.matt.utils.PWConstants;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.matt.utils.HibernateUtil;
import org.matt.daos.UserDAO;

import com.opensymphony.xwork2.ActionSupport;

public class ShiroRegisterAction extends ShiroBaseAction {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean errorsOccured = false;
	private Subject shiroUser;
	
	// TODO: Implement an email-verification step where we send the user a confirmation first
	// TODO: Refactor this into seperate methods
	public String execute() {
		
		// ensure the user is not already logged in
		if (shiroUser == null) {
			addActionError(PWConstants.alreadyLoggedIn);
			return PWConstants.error;
		}
		
		EmailValidator emailValidator = EmailValidator.getInstance();
		
		// isValid will also protect against null strings (no null checks needed)
		if (!emailValidator.isValid(username)) {
			addActionError(PWConstants.invalidEmail);
			return PWConstants.error;
		}

		/*PasswordValidator validator = new PasswordValidator(
			// length between 8 and 16 characters
			new LengthRule(8, 16),
			
			// at least one upper-case character
			new CharacterRule(EnglishCharacterData.UpperCase, 1),
			
			// at least one lower-case character
			new CharacterRule(EnglishCharacterData.LowerCase, 1),
			
			// at least one digit character
			new CharacterRule(EnglishCharacterData.Digit, 1),
			
			// at least one symbol (special character)
			new CharacterRule(EnglishCharacterData.Special, 1),
			
			// no whitespace
			new WhitespaceRule());
		
		RuleResult result = validator.validate(new PasswordData(password));
		
		if (!result.isValid()) {
			addActionError(validator.getMessages(result).get(0));
			return PWConstants.error;
		}*/

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
			
		// If we reach this, user will now finally log in through struts2 action chaining
		return PWConstants.success;
	}

	public void registerUser(String email, String plainTextPassword) {
		Reguser user = new Reguser();
		user.setMailID(email);

		generatePassword(user, plainTextPassword);

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

	private void generatePassword(Reguser user, String plainTextPassword) {
		RandomNumberGenerator randomNum = new SecureRandomNumberGenerator();
		Object salt = randomNum.nextBytes();

		String hashedPassword = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		user.setUserPassword(hashedPassword);
		user.setSalt(salt.toString());
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
	
	public Subject getShiroUser() {
		return shiroUser;
	}

	// used by the interceptor
	public void setShiroUser(Subject shiroUser) {
		this.shiroUser = shiroUser;
	}
}
