package org.matt.apps;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.matt.auth.ShiroBaseAction;
import org.matt.builders.item.TrackedItemBuilder;
import org.matt.builders.item.ItemDirector;
import org.matt.builders.item.MicroCenterTrackedItemBuilder;
import org.matt.models.TrackedItem;
import org.matt.utils.HibernateUtil;
import org.matt.utils.PWConstants;

/**
 * This class acts as the main processor when a user wants to track a new item.
 * It will create a new item record, and any applicable foreign records, in the database.
 * 
 * @author Matt
 */
public class TrackItem extends ShiroBaseAction {
	private static final long serialVersionUID = 2782991788402520731L;
	private String paramUrl;
	private TrackedItem trackedItem;
	
	public String execute() {
		
		trackedItem = buildItem(paramUrl);
		
		// actually add the record to the database
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(trackedItem);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: Set up logging here!!!
			tx.rollback();
			return PWConstants.error;
		} finally {
			session.close();
		}
		return PWConstants.success;
	}

	/**
	 * This method determines what store the URL belongs to, and
	 * subsequently builds an item object for the given store.
	 * @param url   - the URL of the item with which we want to extract item data from.
	 * @return an item with populated data.
	 */
	public TrackedItem buildItem(String url) {
		TrackedItemBuilder itemBuilder;
		ItemDirector itemDirector = null;
		
		if (url.toLowerCase().contains(PWConstants.microcenterStoreName.toLowerCase())) {
			itemBuilder = new MicroCenterTrackedItemBuilder(paramUrl, this.regUser);
			itemDirector = new ItemDirector(itemBuilder);
			itemDirector.makeItem();
		}
		return itemDirector.getTrackedItem();
	}
	
	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}
	
}