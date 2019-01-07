package org.matt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ITEM_ID")
	private Integer itemId;

	@Column(name="IMAGE_URL")
	private String imageUrl;

	@Column(name="ITEM_NAME")
	private String itemName;

	private String url;

	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="STORE_ID")
	private Store store;

	//bi-directional many-to-one association to TrackItem
	@OneToMany(mappedBy="item")
	private List<TrackedItem> trackItems;

	public Item() {
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public List<TrackedItem> getTrackItems() {
		return this.trackItems;
	}

	public void setTrackItems(List<TrackedItem> trackItems) {
		this.trackItems = trackItems;
	}

	public TrackedItem addTrackItem(TrackedItem trackItem) {
		getTrackItems().add(trackItem);
		trackItem.setItem(this);

		return trackItem;
	}

	public TrackedItem removeTrackItem(TrackedItem trackItem) {
		getTrackItems().remove(trackItem);
		trackItem.setItem(null);

		return trackItem;
	}

}