package org.matt.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="ITEM")
@Table(name="ITEMS")
public class Item implements Serializable {
	private static final long serialVersionUID = -615013449232901726L;
	
	private Integer itemID;
	private String itemName;
	private double currentItemPrice;
	private double lastItemPrice;
	private double originalItemPrice;
	private double itemPriceDifference;
	
	private Date dateTracked;
	private Date lastPriceChangeDate;
	private Reguser reguser;
	private Store store;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ITEM_ID")
	public Integer getItemID() {
		return itemID;
	}
	
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	
	@Column(name="ITEM_NAME")
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Column(name="CURRENT_ITEM_PRICE")
	public double getCurrentItemPrice() {
		return currentItemPrice;
	}
	
	public void setCurrentItemPrice(double currentItemPrice) {
		this.currentItemPrice = currentItemPrice;
	}
	
	@Column(name="LAST_ITEM_PRICE")
	public double getLastItemPrice() {
		return lastItemPrice;
	}
	public void setLastItemPrice(double lastItemPrice) {
		this.lastItemPrice = lastItemPrice;
	}
	
	@Column(name="ORIGINAL_ITEM_PRICE")
	public double getOriginalItemPrice() {
		return originalItemPrice;
	}
	public void setOriginalItemPrice(double originalItemPrice) {
		this.originalItemPrice = originalItemPrice;
	}
	
	@Column(name="ITEM_PRICE_DIFFERENCE")
	public double getItemPriceDifference() {
		return itemPriceDifference;
	}

	public void setItemPriceDifference(double itemPriceDifference) {
		this.itemPriceDifference = itemPriceDifference;
	}

	@Column(name="DATE_TRACKED")
	public Date getDateTracked() {
		return dateTracked;
	}
	public void setDateTracked(Date dateTracked) {
		this.dateTracked = dateTracked;
	}
	
	@Column(name="LAST_PRICE_CHANGE")
	public Date getLastPriceChangeDate() {
		return lastPriceChangeDate;
	}
	public void setLastPriceChangeDate(Date lastPriceChangeDate) {
		this.lastPriceChangeDate = lastPriceChangeDate;
	}
	
	@ManyToOne
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	public Reguser getReguser() {
		return reguser;
	}
	public void setReguser(Reguser reguser) {
		this.reguser = reguser;
	}
	
	@ManyToOne
	@JoinColumn(name = "STORE_ID", referencedColumnName = "STORE_ID")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
}
