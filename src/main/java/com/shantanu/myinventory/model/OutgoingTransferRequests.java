package com.shantanu.myinventory.model;

public class OutgoingTransferRequests {
	
	String sourceVendor;
	String targetVendor;
	String transferDate;
	String itemName;
	double quantity;
	String status;
	public String getSourceVendor() {
		return sourceVendor;
	}
	public void setSourceVendor(String sourceVendor) {
		this.sourceVendor = sourceVendor;
	}
	public String getTargetVendor() {
		return targetVendor;
	}
	public void setTargetVendor(String targetVendor) {
		this.targetVendor = targetVendor;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
