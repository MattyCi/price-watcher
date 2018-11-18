package org.matt.daos;

import org.hibernate.Session;

import org.matt.models.Regusers;

public class UserDAO {
	private final Session session;

	public UserDAO(Session s) {
		session = s;
	}
}