package org.matt.daos;

import java.util.List;

import org.hibernate.Session;
import org.matt.models.Item;
import org.matt.utils.HibernateUtil;

/**
 * This DAO contains methods for performing CRUD operations on items.
 * @author Matt
 */
public class ItemDAO {
	public static List<Item> getItemsByUser(int userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			
		String query = "from ITEM where USER_ID=:userID order by DATE_TRACKED desc";
		@SuppressWarnings("unchecked")
		List<Item> itemList = (List<Item>) session.createQuery(query)
				.setParameter("userID", userID).list();
		session.getTransaction().commit();
		
		return itemList;
	}

	public static List<Item> getMostPopularItems() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
			
		String query = "from ITEM order by DATE_TRACKED desc";
		@SuppressWarnings("unchecked")
		List<Item> itemList = (List<Item>) session.createQuery(query).setMaxResults(4).list();
		session.getTransaction().commit();
		
		return itemList;
	}
	
}