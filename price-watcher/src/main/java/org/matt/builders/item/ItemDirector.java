package org.matt.builders.item;

import org.matt.models.Item;

public class ItemDirector {
	private ItemBuilder itemBuilder;
	
	public ItemDirector(ItemBuilder itemBuilder) {
		this.itemBuilder = itemBuilder;
	}

	public Item getItem() {
		return this.itemBuilder.getItem();
	}
	
	public void makeItem() {
		// for now we can simply use the builAll method
		// rather than call each individual build method
		this.itemBuilder.buildAll();
	}
}
