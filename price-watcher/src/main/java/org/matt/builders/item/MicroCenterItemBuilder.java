package org.matt.builders.item;

import java.io.IOException;
import java.sql.Timestamp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.matt.models.Item;
import org.matt.models.Reguser;
import org.matt.models.Store;
import org.matt.utils.HibernateUtil;
import org.matt.utils.PWConstants;

public class MicroCenterItemBuilder implements ItemBuilder {
	private Item item;
	private String url;
	private Reguser regUser;
	Double currentPrice;
	Document doc;
	
	/**
	 * Use this constructor to build item data by using the item's
	 * URL from the store.
	 * @param url - the URL to grab item data from.
	 * @param regUser - the currently executing user.
	 */
	public MicroCenterItemBuilder(String url, Reguser regUser) {
		this.item = new Item();
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
		buildUrl();
		buildImageUrl();
		buildTrackingDates();
		buildReguser();
		buildStore();
	}

	public void buildItemName() {
		item.setItemName(
			doc.select("span[class~=ProductLink_[0-9]+]").first().attr("data-name"));
	}

	public void buildItemPrices() {
		currentPrice = Double.parseDouble(doc.select("#pricing").first().ownText());
		item.setCurrentItemPrice(currentPrice);
		item.setOriginalItemPrice(currentPrice);
		item.setLastItemPrice(currentPrice);
		item.setItemPriceDifference(00.00);
	}
	
	public void buildUrl() {
		item.setUrl(this.url);
	}

	public void buildImageUrl() {
		Element imgElement = doc.select(".productImageZoom").first();
		item.setImageUrl(imgElement.absUrl("src"));
	}

	public void buildTrackingDates() {
		Timestamp timeStamp = HibernateUtil.getCurrentTimeStamp();
		item.setDateTracked(timeStamp);
		item.setLastPriceChangeDate(timeStamp);
	}

	public void buildReguser() {
		item.setReguser(this.regUser);
	}

	public void buildStore() {
		Store store = new Store();
		store.setStoreID(PWConstants.microcenterStoreId);
		store.setStoreName(PWConstants.microcenterStoreName);
		item.setStore(store); // foreign key
		
	}

	public Item getItem() {
		return this.item;
	}

}
