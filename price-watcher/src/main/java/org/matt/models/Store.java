package org.matt.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the stores database table.
 * 
 */
@Entity
@Table(name="stores")
@NamedQuery(name="Store.findAll", query="SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STORE_ID")
	private Integer storeId;

	@Column(name="STORE_NAME")
	private String storeName;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="store")
	private List<Item> items;

	public Store() {
	}

	public Integer getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setStore(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setStore(null);

		return item;
	}

}