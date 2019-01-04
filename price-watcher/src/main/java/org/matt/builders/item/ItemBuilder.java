package org.matt.builders.item;

import org.matt.models.Item;

public interface ItemBuilder {

	void buildAll();
	
	void buildItemName();

	void buildItemPrices();

	void buildUrl();

	void buildImageUrl();

	void buildTrackingDates();

	void buildStore();

	void buildReguser();
	
	public Item getItem();
}
