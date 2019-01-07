package org.matt.builders.item;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.matt.models.Item;
import org.matt.models.Reguser;
import org.matt.models.TrackedItem;
import org.matt.utils.HibernateUtil;
import org.matt.utils.ItemUtils;

public abstract class TrackedItemBuilderImpl implements TrackedItemBuilder {
	protected Item item;
	protected String url;
	protected Reguser regUser;
	TrackedItem trackedItem;
	BigDecimal currentPrice;
	Document doc;
	
	/**
	 * When extending this class, always call <code>super(url, regUser);</code>
	 * in the subclass constructor. The superclass constructor will set all
	 * relevant fields for the object.
	 * @param url - the URL to grab item data from.
	 * @param regUser - the currently executing user.
	 */
	public TrackedItemBuilderImpl(String url, Reguser regUser) {
		this.trackedItem = new TrackedItem();
		this.item = new Item();
		this.trackedItem.setItem(item);
		this.url = url;
		this.regUser = regUser;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Use this builder method to automatically populate all data
	 * for the tracked item object. The object must be instantiated first
	 * using the constructor that takes the item URL as a parameter.
	 */
	public void buildAll() {
		buildItemName();
		buildItemId();
		buildItemPrices();
		buildItemUrl();
		buildItemImageUrl();
		buildItemStore();
		this.item = ItemUtils.persistItem(this.item);
		buildItemTrackingDates();
		buildTrackedItemReguser();
	}

	public void buildItemId() {
	}
	
	public void buildItemName() {
	}

	public void buildItemPrices() {
	}

	public void buildItemUrl() {
		trackedItem.getItem().setUrl(this.url);
	}

	public void buildItemImageUrl() {
	}

	public void buildItemTrackingDates() {
		Timestamp timeStamp = HibernateUtil.getCurrentTimeStamp();
		trackedItem.setDateTracked(timeStamp);
		trackedItem.setLastPriceChange(timeStamp);
	}

	public void buildItemStore() {
	}

	public void buildTrackedItemReguser() {
		trackedItem.setReguser(this.regUser);
	}

	public TrackedItem getTrackedItem() {
		return this.trackedItem;
	}
}
