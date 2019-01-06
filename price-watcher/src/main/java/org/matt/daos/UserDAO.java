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
		
		String query = "from Reguser where mailId=:email";
		Reguser user = (Reguser) session.createQuery(query)
				.setParameter("email", email).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
	
	public static Reguser getUserByID(int userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String query = "from Reguser where userId=:userId";
		Reguser user = (Reguser) session.createQuery(query)
				.setParameter("userId", userId).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
	
	/**
	 * Determines whether or not a given user ID is associated with a registered user.
	 * @param userId
	 * @return true if the user is a registered user, false if guest user.
	 */
	public static boolean isUserRegistered(int userId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String query = "select count(*) from Reguser where userId=:userId and mailId is not null";
		int count = ((Long) session.createQuery(query).setParameter("userId", userId).uniqueResult()).intValue();
		session.getTransaction().commit();
		
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Determines whether or not a given user ID is associated with the specified token.
	 * @param userId
	 * @param token
	 * @return true if token and id are valid, false if validation failed.
	 */
	public static boolean isTokenValid(int userId, String userToken) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String query = "select count(*) from Reguser where userId=:userId and userToken=:userToken";
		int count = ((Long) session.createQuery(query)
				.setParameter("userId", userId).setParameter("userToken", userToken)
				.uniqueResult()).intValue();
		session.getTransaction().commit();
		
		if (count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
}
