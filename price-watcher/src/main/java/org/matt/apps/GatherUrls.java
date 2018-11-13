package org.matt.apps;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class GatherUrls extends ActionSupport implements Preparable {
	private static final long serialVersionUID = 1L;
	private String paramUrl;
	private String productName;
	private String currentPrice;
	
	public void prepare() {
		// TODO: Implement proper logging instead of using sysout...
		System.out.println("Here is the parameter: " + paramUrl);
	}

	public String execute() {

		try {
			Document doc;
			doc = Jsoup.connect(paramUrl).get();
			productName = doc.title();
			currentPrice = doc.select("#pricing").first().ownText();

			System.out.println("productName is: " + productName);
			System.out.println("currentPrice is: " + currentPrice);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";
	}

	// TODO: Put these getter/setters in a separate bean class
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}

	public String getParamUrl() {
		return paramUrl;
	}

	public void setParamUrl(String paramUrl) {
		this.paramUrl = paramUrl;
	}
}