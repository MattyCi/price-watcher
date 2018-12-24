package org.matt.daos;

import org.hibernate.Session;

import org.matt.models.Reguser;
import org.matt.utils.HibernateUtil;

/**
 * This DAO contains methods for performing CRUD operations on users.
 * @author Matt
 */
public class UserDAO {
	public static Reguser getUserByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String query = "from REGUSER where mailID=:email";
		Reguser user = (Reguser) session.createQuery(query)
				.setParameter("email", email).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
	
	public static Reguser getUserByID(int userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String query = "from REGUSER where userID=:userID";
		Reguser user = (Reguser) session.createQuery(query)
				.setParameter("userID", userID).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
}
