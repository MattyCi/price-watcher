package org.matt.builders.item;

import org.matt.models.TrackedItem;

public interface TrackedItemBuilder {

	void buildAll();
	
	void buildItemId();
	
	void buildItemName();

	void buildItemPrices();

	void buildItemUrl();

	void buildItemImageUrl();

	void buildItemTrackingDates();

	void buildItemStore();

	void buildTrackedItemReguser();
	
	public TrackedItem getTrackedItem();
}
