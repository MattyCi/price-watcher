package org.matt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Regusers implements Serializable {
	private static final long serialVersionUID = -2699743010498334622L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer USER_ID;

	@Column
	private String MAIL_ID;

	@Column
	private String user_pword;

	@Column
	private String salt;

	/*No-arg constructor*/
	public Regusers() {

	}

	public String getMAIL_ID() {
		return MAIL_ID;
	}

	public Integer getUSER_ID() {
		return USER_ID;
	}

	public String getuser_pword() {
		return user_pword;
	}

	public String getSalt() {
		return salt;
	}

	public void setMAIL_ID(String MAIL_ID) {
		this.MAIL_ID = MAIL_ID;
	}

	public void setUSER_ID(Integer USER_ID) {
		this.USER_ID = USER_ID;
	}

	public void setuser_pword(String user_pword) {
		this.user_pword = user_pword;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
