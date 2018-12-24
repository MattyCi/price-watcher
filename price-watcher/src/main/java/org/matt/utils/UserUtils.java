package org.matt.utils;

import javax.servlet.http.Cookie;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.models.Reguser;

/**
 * Utility class that contains helper methods for user related functions.
 * @author Matt
 *
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
				if (requestCookie.getName() != null && requestCookie.getName().equals(PWConstants.guestCookieName)) {
					System.out.println("FOUND THE GUEST COOKIE it is: "+requestCookie.getValue());
					return requestCookie.getValue();
				}
			}
		}
		return null; // if we reach this, cookie was not found
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
		return user.getUserID();
	}
}
