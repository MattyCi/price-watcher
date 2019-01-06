package org.matt.daos;

import java.util.List;

import org.hibernate.Session;
import org.matt.models.Item;
import org.matt.models.TrackedItem;
import org.matt.utils.HibernateUtil;

/**
 * This DAO contains methods for performing CRUD operations on items.
 * @author Matt
 */
public class ItemDAO {
	public static List<TrackedItem> getTrackedItemsByUser(Integer userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			
		String query = "from TrackedItem where USER_ID=:userId order by DATE_TRACKED desc";
		@SuppressWarnings("unchecked")
		List<TrackedItem> trackedItemList = (List<TrackedItem>) session.createQuery(query)
				.setParameter("userId", userId).list();
		session.getTransaction().commit();
		
		return trackedItemList;
	}

	public static List<Item> getMostPopularItems() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			
		String query = "from Item";
		@SuppressWarnings("unchecked")
		List<Item> itemList = (List<Item>) session.createQuery(query).setMaxResults(4).list();
		session.getTransaction().commit();
		
		return itemList;
	}
	
}