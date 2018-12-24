package org.matt.utils;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.matt.daos.ItemDAO;
import org.matt.daos.UserDAO;
import org.matt.models.Item;
import org.matt.models.Reguser;

/**
 * This class contains various utilities for items.
 * @author Matt
 */
public class ItemUtils {
	private Reguser user;
	
	/**
	 * This method retrieves a registered user's list of tracked items.
	 * @return Array List of the user's tracked items.
	 */
	public List<Item> getRegisteredUserItemList() {
		Subject shiroUser = SecurityUtils.getSubject();
		user = UserDAO.getUserByEmail(shiroUser.getPrincipal().toString());

		// get user's tracked items to be displayed on front-end
		List<Item> itemList = ItemDAO.getItemsByUser(user.getUserID());
		return itemList;
	}
	
	/**
	 * This method retrieves a guest user's list of tracked items.
	 * @return Array List of the user's tracked items.
	 */
	public List<Item> getGuestUserItemList(Cookie cookieArray[]) {
		String guestUserID = UserUtils.retrieveGuestUserIDFromCookies(cookieArray);

		if (guestUserID != null) {
			user = UserDAO.getUserByID(Integer.parseInt(guestUserID));
			
			// get user's tracked items to be displayed on front-end
			List<Item> itemList = ItemDAO.getItemsByUser(user.getUserID());
			
			return itemList;
		} else {
			return null; // guest did not have the user ID cookie set
		}
	}
	
}
