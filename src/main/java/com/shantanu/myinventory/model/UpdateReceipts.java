package com.shantanu.myinventory.model;

public class UpdateReceipts {

	
	private String customerName;
	private String itemName;
	private String receiptOutletName;
	private String quantity;
	private String id;
	private String amount;
	private String date;
	
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getReceiptOutletName() {
		return receiptOutletName;
	}
	public void setReceiptOutletName(String receiptOutletName) {
		this.receiptOutletName = receiptOutletName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
