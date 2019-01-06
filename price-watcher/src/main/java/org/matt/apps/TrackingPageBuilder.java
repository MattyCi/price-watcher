package org.matt.apps;

import java.util.List;

import org.matt.auth.ShiroBaseAction;
import org.matt.daos.ItemDAO;
import org.matt.models.Reguser;
import org.matt.models.TrackedItem;
import org.matt.utils.PWConstants;

/**
 * This class acts as the main pre-processor when a user wants to visit their
 * item tracking page. The <code>execute()</code> method grabs the users items
 * from the database.
 * 
 * @author Matt
 */
public class TrackingPageBuilder extends ShiroBaseAction {
	private static final long serialVersionUID = 5110648783076504658L;
	private List<TrackedItem> trackedItemList;
	private Reguser regUser;
	private char userType;

	public String execute() {

		// set item list from user ID
		setTrackedItemList(ItemDAO.getTrackedItemsByUser(regUser.getUserId()));

		return PWConstants.success;
	}

	public List<TrackedItem> getTrackedItemList() {
		return trackedItemList;
	}

	public void setTrackedItemList(List<TrackedItem> itemList) {
		this.trackedItemList = itemList;
	}

	// used by the interceptor
	public void setRegUser(Reguser regUser) {
		this.regUser = regUser;
	}

	public Reguser getRegUser() {
		return regUser;
	}

	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

}
