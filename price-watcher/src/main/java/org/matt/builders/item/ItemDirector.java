package org.matt.builders.item;

import org.matt.models.TrackedItem;

public class ItemDirector {
	private TrackedItemBuilder trackedItemBuilder;
	
	public ItemDirector(TrackedItemBuilder itemBuilder) {
		this.trackedItemBuilder = itemBuilder;
	}

	public TrackedItem getTrackedItem() {
		return this.trackedItemBuilder.getTrackedItem();
	}
	
	public void makeItem() {
		// for now we can simply use the builAll method
		// rather than call each individual build method
		this.trackedItemBuilder.buildAll();
	}
}
