package org.matt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the track_items database table.
 * 
 */
@Entity
@Table(name="track_items")
@NamedQuery(name="TrackedItem.findAll", query="SELECT t FROM TrackedItem t")
public class TrackedItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRCK_ITM_ID")
	private String trckItmId;

	@Column(name="CURRENT_ITEM_PRICE")
	private BigDecimal currentItemPrice;

	@Column(name="DATE_TRACKED")
	private Timestamp dateTracked;

	@Column(name="ITEM_PRICE_DIFFERENCE")
	private BigDecimal itemPriceDifference;

	@Column(name="LAST_ITEM_PRICE")
	private BigDecimal lastItemPrice;

	@Column(name="LAST_PRICE_CHANGE")
	private Timestamp lastPriceChange;

	@Column(name="ORIGINAL_ITEM_PRICE")
	private BigDecimal originalItemPrice;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="ITEM_ID")
	private Item item;

	//bi-directional many-to-one association to Reguser
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Reguser reguser;

	public TrackedItem() {
	}

	public String getTrckItmId() {
		return this.trckItmId;
	}

	public void setTrckItmId(String trckItmId) {
		this.trckItmId = trckItmId;
	}

	public BigDecimal getCurrentItemPrice() {
		return this.currentItemPrice;
	}

	public void setCurrentItemPrice(BigDecimal currentItemPrice) {
		this.currentItemPrice = currentItemPrice;
	}

	public Timestamp getDateTracked() {
		return this.dateTracked;
	}

	public void setDateTracked(Timestamp dateTracked) {
		this.dateTracked = dateTracked;
	}

	public BigDecimal getItemPriceDifference() {
		return this.itemPriceDifference;
	}

	public void setItemPriceDifference(BigDecimal itemPriceDifference) {
		this.itemPriceDifference = itemPriceDifference;
	}

	public BigDecimal getLastItemPrice() {
		return this.lastItemPrice;
	}

	public void setLastItemPrice(BigDecimal lastItemPrice) {
		this.lastItemPrice = lastItemPrice;
	}

	public Timestamp getLastPriceChange() {
		return this.lastPriceChange;
	}

	public void setLastPriceChange(Timestamp lastPriceChange) {
		this.lastPriceChange = lastPriceChange;
	}

	public BigDecimal getOriginalItemPrice() {
		return this.originalItemPrice;
	}

	public void setOriginalItemPrice(BigDecimal originalItemPrice) {
		this.originalItemPrice = originalItemPrice;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Reguser getReguser() {
		return this.reguser;
	}

	public void setReguser(Reguser reguser) {
		this.reguser = reguser;
	}

}