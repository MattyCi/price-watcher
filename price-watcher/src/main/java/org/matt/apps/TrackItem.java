package org.matt.apps;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.matt.auth.ShiroBaseAction;
import org.matt.models.Item;
import org.matt.models.Store;
import org.matt.utils.HibernateUtil;
import org.matt.utils.PWConstants;

/**
 * This class acts as the main processor when a user wants to track a new item.
 * It will create a new item record in the database.
 * 
 * @author Matt
 */
public class TrackItem extends ShiroBaseAction {
	private static final long serialVersionUID = 2782991788402520731L;
	private String paramUrl;
	private Double currentItemPrice;
	private Item item = null;
	private Store store = null;
	private Document doc = null;
	
	Timestamp currentDateSQL = Timestamp.from(Instant.now());

	public String execute() {
		
		try {
			System.out.println("paramUrl is: "+paramUrl);
			doc = Jsoup.connect(paramUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// populate item data 
		item = new Item();
		item.setItemName(doc.select("span[class~=ProductLink_[0-9]+]").first().attr("data-name"));
		currentItemPrice = Double.parseDouble(doc.select("#pricing").first().ownText());
		item.setCurrentItemPrice(currentItemPrice);
		item.setOriginalItemPrice(currentItemPrice);
		item.setLastItemPrice(currentItemPrice);
		item.setItemPriceDifference(00.00);
		item.setDateTracked(currentDateSQL);
		item.setLastPriceChangeDate(currentDateSQL);
		item.setUrl(paramUrl);
		
		// get item image url
		Element imgElement = doc.select(".productImageZoom").first();
		item.setImageUrl(imgElement.absUrl("src"));
		
		store = populateStore(new Store(), paramUrl);
		item.setStore(store); // foreign key
		item.setReguser(this.getRegUser()); // foreign key

		// actually add the record to the database
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(item);
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
	 * This method will populate a store object given the URL to a product page from
	 * their site.
	 * @param store - the store we want to populate with data
	 * @param url   - the URL of the item with which we want to extract store data from.
	 * @return a store object with populated data
	 */
	public Store populateStore(Store store, String url) {

		if (url.toLowerCase().contains(PWConstants.microcenterStoreName.toLowerCase())) {
			store.setStoreID(PWConstants.microcenterStoreId);
			store.setStoreName(PWConstants.microcenterStoreName);
		}
		return store;
	}
	
	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}
	
}