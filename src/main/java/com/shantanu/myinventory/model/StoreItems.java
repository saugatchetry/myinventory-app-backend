package com.shantanu.myinventory.model;

public class StoreItems {
	
	String itemName;
	Float rate;
	String uom;
	
	public StoreItems(String itemName, Float rate, String uom) {
		super();
		this.itemName = itemName;
		this.rate = rate;
		this.uom = uom;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	};
	

}
