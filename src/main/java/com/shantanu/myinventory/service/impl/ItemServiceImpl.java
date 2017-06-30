package com.shantanu.myinventory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shantanu.myinventory.dao.ItemDao;
import com.shantanu.myinventory.model.DataFromClient;
import com.shantanu.myinventory.model.Items;
import com.shantanu.myinventory.model.Receipt;
import com.shantanu.myinventory.model.StockTransfer;
import com.shantanu.myinventory.model.StoreItems;
import com.shantanu.myinventory.model.Vendors;
import com.shantanu.myinventory.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public ArrayList<Items> getAllItems() {
		return itemDao.getAllItems();
	}

	@Override
	public Items addItem(Items items) {
		return itemDao.addItem(items);
	}

	@Override
	public HashMap<String, Float> getItemsByVendorName(String storeName) {
		//add return statement;
		 return itemDao.getItemsByVendorName(storeName);
		
	}

	@Override
	public ArrayList<StoreItems> getItemsByVendorName1(String storeName) {
		// TODO Auto-generated method stub
		return itemDao.getItemsByVendorName1(storeName);
	}

	@Override
	public void addReceipts(ArrayList<DataFromClient> receipts) {
		// TODO Auto-generated method stub
		itemDao.addReceipts(receipts);
	}

	@Override
	public ArrayList<Vendors> getAllVendors() {
		return itemDao.getAllVendors();
	}

	@Override
	public ArrayList<Receipt> getAllReceipts() {
		
		return itemDao.getAllReceipts();
	}

	@Override
	public ArrayList<StockTransfer> getAllStockTransfer(String storeName) {
		
		return itemDao.getAllStockTransfer(storeName);
	}

	@Override
	public void confirmedStocks(ArrayList<Integer> transferIds) {
		System.out.println("Confirmed Request service layer");
		itemDao.confirmedStocks(transferIds);
		
	}

	@Override
	public void updateReceipts(int id,String customerName,String itemName,String quantity,String receiptOutletName,String amount,String date) {
		itemDao.updateReceipts(id,customerName,itemName,quantity,receiptOutletName,amount,date);
		
	}

	@Override
	public ArrayList<StockTransfer> getEveryStockTransfers() {
		
		return itemDao.getEveryStockTransfers();
	}

	@Override
	public void outGoingStockTransfers(ArrayList<StockTransfer> stockList) {
		itemDao.outGoingStockTransfers(stockList);
		
	}

	@Override
	public void addBulkItems(ArrayList<Items> itemsList) {
		itemDao.addBulkItems(itemsList);
		
	}

}
