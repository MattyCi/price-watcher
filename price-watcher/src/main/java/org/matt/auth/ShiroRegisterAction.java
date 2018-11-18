package org.matt.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.hibernate.Session;
import org.matt.models.Regusers;
import org.matt.utils.HibernateUtil;

public class ShiroRegisterAction {
	private static final long serialVersionUID = 1L;
	private static final transient Logger log = LogManager.getLogger(ShiroLogInAction.class);
	private String username;
	private String password;
	private transient Subject shiroUser;
	
	public void prepare() throws Exception {
		
	}
	
	public String execute() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Regusers regUser = new Regusers();
        regUser.setMAIL_ID(username);
        regUser.setuser_pword(password);
        session.save(regUser);

        session.getTransaction().commit();
        
        //TODO: Log in user
        return "success";
    }
	
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
