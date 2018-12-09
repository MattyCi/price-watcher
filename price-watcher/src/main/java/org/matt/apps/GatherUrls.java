package org.matt.apps;

import java.io.IOException;
import java.util.Date;

import org.apache.shiro.subject.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.matt.auth.ShiroBaseAction;
import org.matt.daos.UserDAO;
import org.matt.models.Item;
import org.matt.models.Reguser;
import org.matt.models.Store;
import org.matt.utils.HibernateUtil;
import org.matt.utils.PWConstants;

import com.opensymphony.xwork2.Preparable;

/**
 * This class acts as the main processor when a user wants to track a new item.
 * It will create a new item record in the database.
 * 
 * @author Matt
 */
public class GatherUrls extends ShiroBaseAction implements Preparable {
	private static final long serialVersionUID = 1L;
	private String paramUrl;
	private Double currentItemPrice;
	private Subject shiroUser;
	private Item item = null;
	private Store store = null;
	private Document doc = null;
	
	Date date = new Date();
	java.sql.Date currentDateSQL = new java.sql.Date(date.getTime());

	public void prepare() {
		// TODO: Implement proper logging instead of using sysout...
		System.out.println("Here is the parameter: " + paramUrl);
	}

	public String execute() {

		try {
			doc = Jsoup.connect(paramUrl).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// populate some of the item data first in case this is a guest user
		item = new Item();
		item.setItemName(doc.title()); // TODO: use the span instead of the title
 		currentItemPrice = Double.parseDouble(doc.select("#pricing").first().ownText());
		item.setCurrentItemPrice(currentItemPrice);
		store = populateStore(new Store(), paramUrl);
		
		System.out.println("productName is: " + item.getItemName());
		System.out.println("currentPrice is: " + item.getCurrentItemPrice());

		// finished if the user is a guest
		if (shiroUser.getPrincipal() == null) {
			System.out.println("Guest user running command!");
			return PWConstants.success;
		}

		Reguser user = UserDAO.getUserByEmail(shiroUser.getPrincipal().toString());

		// set the rest of the item data for reg users since it matters for them
		item.setOriginalItemPrice(currentItemPrice);
		item.setLastItemPrice(currentItemPrice);
		item.setItemPriceDifference(00.00);
		item.setDateTracked(currentDateSQL);
		item.setLastPriceChangeDate(currentDateSQL);
		item.setStore(store); // foreign key
		item.setReguser(user); // foreign key

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

	public Double getCurrentItemPrice() {
		return currentItemPrice;
	}

	public void setCurrentItemPrice(Double currentPrice) {
		this.currentItemPrice = currentPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}

	public Subject getShiroUser() {
		return shiroUser;
	}

	// used by the interceptor
	public void setShiroUser(Subject shiroUser) {
		this.shiroUser = shiroUser;
	}
}