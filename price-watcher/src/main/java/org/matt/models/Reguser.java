package org.matt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the regusers database table.
 * 
 */
@Entity
@Table(name="regusers")
@NamedQuery(name="Reguser.findAll", query="SELECT r FROM Reguser r")
public class Reguser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer userId;

	@Column(name="MAIL_ID")
	private String mailId;

	private String salt;

	@Column(name="USER_PWORD")
	private String userPword;

	@Column(name="USER_TOKEN")
	private String userToken;

	//bi-directional many-to-one association to TrackItem
	@OneToMany(mappedBy="reguser")
	private List<TrackedItem> trackItems;

	public Reguser() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getMailId() {
		return this.mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUserPword() {
		return this.userPword;
	}

	public void setUserPword(String userPword) {
		this.userPword = userPword;
	}

	public String getUserToken() {
		return this.userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public List<TrackedItem> getTrackItems() {
		return this.trackItems;
	}

	public void setTrackItems(List<TrackedItem> trackItems) {
		this.trackItems = trackItems;
	}

	public TrackedItem addTrackItem(TrackedItem trackItem) {
		getTrackItems().add(trackItem);
		trackItem.setReguser(this);

		return trackItem;
	}

	public TrackedItem removeTrackItem(TrackedItem trackItem) {
		getTrackItems().remove(trackItem);
		trackItem.setReguser(null);

		return trackItem;
	}

}