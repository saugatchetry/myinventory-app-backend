package com.shantanu.myinventory.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.shantanu.myinventory.model.DataFromClient;
import com.shantanu.myinventory.model.Items;
import com.shantanu.myinventory.model.Receipt;
import com.shantanu.myinventory.model.StockTransfer;
import com.shantanu.myinventory.model.StoreItems;
import com.shantanu.myinventory.model.Vendors;

public interface ItemDao {
	ArrayList<Items> getAllItems();
	Items addItem(Items items);
	HashMap<String, Float> getItemsByVendorName(String storeName); // change this to return type of HashMap
	ArrayList<StoreItems> getItemsByVendorName1(String storeName);
	void addReceipts(ArrayList<DataFromClient> receipts);
	ArrayList<Vendors> getAllVendors();
	ArrayList<Receipt> getAllReceipts();
	ArrayList<StockTransfer> getAllStockTransfer(String storeName);
	void confirmedStocks(ArrayList<Integer> transferIds);
	void updateReceipts(int id,String customerName,String itemName,String quantity,String receiptOutletName,String amount,String date);
	ArrayList<StockTransfer> getEveryStockTransfers();
	void outGoingStockTransfers(ArrayList<StockTransfer> stockList);
	void addBulkItems(ArrayList<Items> itemsList);
}
