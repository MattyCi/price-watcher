package org.matt.auth;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
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

public class ShiroRegisterAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean errorsOccured = false;

	// TODO: Implement an email-verification step where we send the user a
	// confirmation first
	public String execute() {
		EmailValidator emailValidator = EmailValidator.getInstance();
		
		// isValid will also protect against null strings (no null checks needed)
		if (!emailValidator.isValid(username)) {
			return PWConstants.error;
		}

		PasswordValidator validator = new PasswordValidator(
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
		
		if (result.isValid()) {
			// first ensure user does not already exist
			if(UserDAO.getUserByEmail(username) != null) {
				System.out.println("User already exists!");
				return PWConstants.error;
			}
			
			registerUser(username, password);
		} else {
			System.out.println(validator.getMessages(result));
			errorsOccured = true;
		}

		if (errorsOccured)
			return PWConstants.error;

		// User is finally logged in through struts2 action chaining
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
			tx.rollback();
			errorsOccured = true;
		} finally {
			System.out.println("closing session");
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
}
