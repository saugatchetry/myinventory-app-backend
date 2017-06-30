package com.shantanu.myinventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class StockTransfer {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transferId;
	
	@Column
	String sourceVendor;
	
	@Column
	String targetVendor;
	
	@Column
	String date;
	
	@Column
	String itemName;
	
	@Column
	Float quantity;
	
	@Column
	String status;
	
	public StockTransfer(){
		
	}
	
	
	public StockTransfer(int transferId, String sourceVendor, String targetVendor, String date, String itemName,
			Float quantity, String status) {
		super();
		this.transferId = transferId;
		this.sourceVendor = sourceVendor;
		this.targetVendor = targetVendor;
		this.date = date;
		this.itemName = itemName;
		this.quantity = quantity;
		this.status = status;
	}

	

	public int getTransferId() {
		return transferId;
	}



	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}



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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	

}
