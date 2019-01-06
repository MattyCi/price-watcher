package org.matt.builders.item;

import java.math.BigDecimal;

import org.jsoup.nodes.Element;
import org.matt.models.Reguser;
import org.matt.models.Store;
import org.matt.utils.PWConstants;

public class MicroCenterTrackedItemBuilderImpl extends TrackedItemBuilderImpl implements TrackedItemBuilder {
	
	public MicroCenterTrackedItemBuilderImpl(String url, Reguser regUser) {
		super(url, regUser);
	}

	@Override
	public void buildItemName() {
		trackedItem.getItem().setItemName(
			doc.select("span[class~=ProductLink_[0-9]+]").first().attr("data-name"));
	}

	@Override
	public void buildItemPrices() {
		currentPrice = new BigDecimal(doc.select("#pricing").first().ownText());
		trackedItem.setCurrentItemPrice(currentPrice);
		trackedItem.setOriginalItemPrice(currentPrice);
		trackedItem.setLastItemPrice(currentPrice);
		trackedItem.setItemPriceDifference(new BigDecimal("0.00"));
	}

	@Override
	public void buildItemImageUrl() {
		Element imgElement = doc.select(".productImageZoom").first();
		trackedItem.getItem().setImageUrl(imgElement.absUrl("src"));
	}

	@Override
	public void buildItemStore() {
		Store store = new Store();
		store.setStoreId(PWConstants.microcenterStoreId);
		store.setStoreName(PWConstants.microcenterStoreName);
		trackedItem.getItem().setStore(store); // foreign key
	}
}
