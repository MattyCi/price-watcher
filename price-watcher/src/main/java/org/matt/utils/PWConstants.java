package org.matt.utils;

/**
 * Holds constant variables to be used throughout the price-watcer application.
 * @author Matt
 */
public final class PWConstants {
	// struts messages
	public static final String success = "success";
	public static final String error = "error";
	
	// user values
	public static final char newGuestUserType = 'N';
	public static final char guestUserType = 'G';
	public static final char regUserType = 'R';
	
	// cookie values
	public static final String guestIDCookieName = "guestID";
	public static final String guestTokenCookieName = "guestToken";
	
	// error messages
	public static final String genericError = "Sorry, we could not process your request.";
	public static final String invalidEmail = "The email entered is invalid!";
	public static final String noAccount = "Sorry... we could not log you in. Please check your "
			+ "credentials and try again.";
	public static final String alreadyLoggedIn = "It seems you are already logged in as a user. "
			+ "Please log out and try to register again.";
	public static final String cookieTampering = "We had some issues reading your browser cookies. "
	+ "Please clear your cookies and try again.";
	// store information
	public static final String microcenterStoreName = "Microcenter";
	public static final int microcenterStoreId = 100;
}
