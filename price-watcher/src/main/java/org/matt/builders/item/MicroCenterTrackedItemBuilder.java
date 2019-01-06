package org.matt.builders.item;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.matt.models.Item;
import org.matt.models.Reguser;
import org.matt.models.Store;
import org.matt.models.TrackedItem;
import org.matt.utils.HibernateUtil;
import org.matt.utils.ItemUtils;
import org.matt.utils.PWConstants;

public class MicroCenterTrackedItemBuilder implements TrackedItemBuilder {
	private Item item;
	private String url;
	private Reguser regUser;
	TrackedItem trackedItem;
	BigDecimal currentPrice;
	Document doc;

	/**
	 * Use this constructor to build item data by using the item's
	 * URL from the store.
	 * @param url - the URL to grab item data from.
	 * @param regUser - the currently executing user.
	 */
	public MicroCenterTrackedItemBuilder(String url, Reguser regUser) {
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
	 * for the item object. The object must be instantiated first
	 * using the constructor that takes the item URL as a parameter.
	 */
	public void buildAll() {
		buildItemName();
		buildItemPrices();
		buildItemUrl();
		buildItemImageUrl();
		buildItemStore();
		this.item = ItemUtils.persistItem(this.item);
		buildItemTrackingDates();
		buildTrackedItemReguser();
	}

	public void buildItemName() {
		trackedItem.getItem().setItemName(
			doc.select("span[class~=ProductLink_[0-9]+]").first().attr("data-name"));
	}

	public void buildItemPrices() {
		currentPrice = new BigDecimal(doc.select("#pricing").first().ownText());
		trackedItem.setCurrentItemPrice(currentPrice);
		trackedItem.setOriginalItemPrice(currentPrice);
		trackedItem.setLastItemPrice(currentPrice);
		trackedItem.setItemPriceDifference(new BigDecimal("0.00"));
	}
	
	public void buildItemUrl() {
		trackedItem.getItem().setUrl(this.url);
	}

	public void buildItemImageUrl() {
		Element imgElement = doc.select(".productImageZoom").first();
		trackedItem.getItem().setImageUrl(imgElement.absUrl("src"));
	}

	public void buildItemTrackingDates() {
		Timestamp timeStamp = HibernateUtil.getCurrentTimeStamp();
		trackedItem.setDateTracked(timeStamp);
		trackedItem.setLastPriceChange(timeStamp);
	}

	public void buildTrackedItemReguser() {
		trackedItem.setReguser(this.regUser);
	}

	public void buildItemStore() {
		Store store = new Store();
		store.setStoreId(PWConstants.microcenterStoreId);
		store.setStoreName(PWConstants.microcenterStoreName);
		trackedItem.getItem().setStore(store); // foreign key
	}
	
	public TrackedItem getTrackedItem() {
		return this.trackedItem;
	}
}
