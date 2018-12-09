package org.matt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="STORE")
@Table(name="STORES")
public class Store {

	private Integer storeID;
	private String storeName;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STORE_ID")
	public Integer getStoreID() {
		return storeID;
	}
	
	public void setStoreID(Integer storeID) {
		this.storeID = storeID;
	}
	
	@Column(name="STORE_NAME")
	public String getStoreName() {
		return storeName;
	}
	
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
}
