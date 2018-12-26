package org.matt.utils;

import javax.servlet.http.Cookie;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.struts2.ServletActionContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.models.Reguser;
import org.passay.PasswordValidator;

/**
 * Utility class that contains helper methods for user related functions.
 * @author Matt
 */
public class UserUtils {

	/**
	 * Given an array of cookies, this method will check to see if the guest
	 * user ID cookie exists for the user.
	 * @param cookieArray The cookie array to search.
	 * @return The String value of the guest user ID cookie if it exists, otherwise returns null.
	 */
	public static String retrieveGuestUserIDFromCookies(Cookie[] cookieArray) {
		if (cookieArray != null) {
			for (Cookie requestCookie : cookieArray) {
				if (requestCookie.getName() != null && requestCookie.getName().equals(PWConstants.guestIDCookieName)) {
					System.out.println("FOUND THE GUEST COOKIE it is: "+requestCookie.getValue());
					return requestCookie.getValue();
				}
			}
		}
		return null; // if we reach this, cookie was not found
	}
	
	/**
	 * Given an array of cookies, this method will check to see if the guest
	 * user token cookie exists for the user.
	 * @param cookieArray The cookie array to search.
	 * @return The String value of the guest user tokrn cookie if it exists, otherwise returns null.
	 */
	public static String retrieveGuestUserTokenFromCookies(Cookie[] cookieArray) {
		if (cookieArray != null) {
			for (Cookie requestCookie : cookieArray) {
				if (requestCookie.getName() != null && requestCookie.getName().equals(PWConstants.guestTokenCookieName)) {
					System.out.println("FOUND THE GUEST COOKIE it is: "+requestCookie.getValue());
					return requestCookie.getValue();
				}
			}
		}
		return null; // if we reach this, cookie was not found
	}
	
	/**
	 * Deletes the guestID cookie.
	 */
	public static void deleteGuestCookies() {
		Cookie guestIDCookie = new Cookie(PWConstants.guestIDCookieName, "");
		guestIDCookie.setPath("/");
		guestIDCookie.setMaxAge(0);
		
		Cookie guestTokenCookie = new Cookie(PWConstants.guestTokenCookieName, "");
		guestTokenCookie.setPath("/");
		guestTokenCookie.setMaxAge(0);
		
		ServletActionContext.getResponse().addCookie(guestIDCookie);
		ServletActionContext.getResponse().addCookie(guestTokenCookie);
	}
	
	/**
	 * Creates a new guest user in the database, assigning the user a unique ID.
	 * @param user The Reguser object to be persisted in the database.
	 * @return The integer ID of the newly created guest user.
	 * @throws HibernateException if there were issues persisting the object.
	 */
	public static int createGuestUser(Reguser user) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: Set up logging here!!!
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		// assign user ID token
		generateUserToken(user);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: Set up logging here!!!
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return user.getUserID();
	}
	
	/**
	 * Builds a passay PasswordValidator object. This method simply reduces code
	 * clutter by building the object within this method.
	 * @return The passay validator object with configured contructors.
	 */
	public static PasswordValidator createPasswordValidator() {
		PasswordValidator validator = new PasswordValidator(
			/*// length between 8 and 16 characters
			new LengthRule(8, 16),
			
			// at least one upper-case character
			new CharacterRule(EnglishCharacterData.UpperCase, 1),
			
			// at least one lower-case character
			new CharacterRule(EnglishCharacterData.LowerCase, 1),
			
			// at least one digit character
			new CharacterRule(EnglishCharacterData.Digit, 1),
			
			// no whitespace
			new WhitespaceRule()*/);
		
		return validator;
	}
	
	public static void generatePassword(Reguser user, String plainTextPassword) {
		RandomNumberGenerator randomNum = new SecureRandomNumberGenerator();
		Object salt = randomNum.nextBytes();

		String hashedPassword = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		user.setUserPassword(hashedPassword);
		user.setSalt(salt.toString());
	}
	
	/**
	 * Generates a hashed token to be used for guest user authentication through cookies.
	 * This token is then stored in the database for the corresponding user's userID.
	 * @param Reguser
	 */
	public static void generateUserToken(Reguser user) {
		RandomNumberGenerator randomNum = new SecureRandomNumberGenerator();
		Object userTokenSalt = randomNum.nextBytes();
		
		String tokenHash = new Sha256Hash(user.getUserID().toString(), userTokenSalt, 1024).toBase64();

		user.setUserToken(tokenHash);
	}
	
}
