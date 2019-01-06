package org.matt.utils;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.daos.ItemDAO;
import org.matt.daos.UserDAO;
import org.matt.models.Item;
import org.matt.models.Reguser;
import org.matt.models.TrackedItem;

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
	public List<TrackedItem> getRegisteredUserItemList() {
		Subject shiroUser = SecurityUtils.getSubject();
		user = UserDAO.getUserByEmail(shiroUser.getPrincipal().toString());

		// get user's tracked items to be displayed on front-end
		List<TrackedItem> itemList = ItemDAO.getTrackedItemsByUser(user.getUserId());
		return itemList;
	}
	
	/**
	 * This method retrieves a guest user's list of tracked items.
	 * @return Array List of the user's tracked items.
	 */
	public List<TrackedItem> getGuestUserItemList(Cookie cookieArray[]) {
		String guestUserID = UserUtils.retrieveGuestUserIDFromCookies(cookieArray);

		if (guestUserID != null) {
			user = UserDAO.getUserByID(Integer.parseInt(guestUserID));
			
			// get user's tracked items to be displayed on front-end
			List<TrackedItem> itemList = ItemDAO.getTrackedItemsByUser(user.getUserId());
			
			return itemList;
		} else {
			return null; // guest did not have the user ID cookie set
		}
	}
	
	/**
	 * Persists an item object to the database, assigning the item a unique ID.
	 * @return A new item object that has been persisted in the databse.
	 * @throws HibernateException if there were issues persisting the object.
	 */
	public static Item persistItem(Item item) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(item);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: Set up logging here!!!
			tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		
		return item;
	}
}
