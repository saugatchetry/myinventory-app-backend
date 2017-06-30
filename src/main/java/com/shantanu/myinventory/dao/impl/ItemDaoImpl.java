package com.shantanu.myinventory.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shantanu.myinventory.dao.ItemDao;
import com.shantanu.myinventory.model.DataFromClient;
import com.shantanu.myinventory.model.Items;
import com.shantanu.myinventory.model.Receipt;
import com.shantanu.myinventory.model.StockTransfer;
import com.shantanu.myinventory.model.StoreItems;
import com.shantanu.myinventory.model.Vendors;

@Component
public class ItemDaoImpl implements ItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<Items> getAllItems() {
		Criteria criteria = sessionFactory.openSession().createCriteria(Items.class);
		return (ArrayList<Items>) criteria.list();
	}

	@Override
	public Items addItem(Items items) {
		System.out.println("Items = "+items.getItemName());
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(items);
		session.getTransaction().commit();
		return null;
	}

	@Override
	public HashMap<String, Float> getItemsByVendorName(String storeName) {
		
		HashMap<String,Float> allItems = new HashMap<String,Float>();
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select itemName, rate from Items where outlet like :storeName");
		query.setParameter("storeName", storeName);
		List data = query.list();
		
		for (Iterator it = data.iterator(); it.hasNext(); ) {
            Object[] myResult = (Object[]) it.next();
            String itemName = (String) myResult[0];
            Float rate = (Float) myResult[1];
            allItems.put(itemName, rate);
            //System.out.println( "ItemName = " + itemName + " rate = " + rate );
         }
		
		session.getTransaction().commit();
		
		return allItems;
		
		
	}

	@Override
	public ArrayList<StoreItems> getItemsByVendorName1(String storeName) {
		ArrayList<StoreItems> allItems = new ArrayList<StoreItems>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select itemName, rate, uom from Items where outlet like :storeName");
		query.setParameter("storeName", storeName);
		ArrayList data = (ArrayList) query.list();
		
		for (Iterator it = data.iterator(); it.hasNext(); ) {
            Object[] myResult = (Object[]) it.next();
            String itemName = (String) myResult[0];
            Float rate = (Float) myResult[1];
            String uom = (String) myResult[2];
            
            allItems.add(new StoreItems(itemName,rate,uom));
            
         }
		
		session.getTransaction().commit();
		return allItems;
	}

	@Override
	public void addReceipts(ArrayList<DataFromClient> receipts) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		for(DataFromClient r : receipts){
			Receipt receipt = new Receipt();
			receipt.setCustomerName(r.getCustomerName());
			receipt.setReceiptOutletName(r.getReceiptOutletName());
			receipt.setDate(r.getReceiptDate());
			receipt.setItemName(r.getItemName());
			receipt.setQuantity(r.getQuantity());
			receipt.setAmount(r.getRate());
			
			System.out.println("Customer name = "+receipt.getCustomerName()+
					   " Items name = "+receipt.getItemName() + 
					   " quantity = "+receipt.getQuantity() +
					   " rate = "+receipt.getAmount() +
					   " date = "+receipt.getDate() +
					   " outlet = "+receipt.getReceiptOutletName());
			session.save(receipt);
		}
		
		
		session.getTransaction().commit();
		
	}

	@Override
	public ArrayList<Vendors> getAllVendors() {
		
		Criteria criteria = sessionFactory.openSession().createCriteria(Vendors.class);
		return (ArrayList<Vendors>) criteria.list();
	}

	@Override
	public ArrayList<Receipt> getAllReceipts() {
		Criteria criteria = sessionFactory.openSession().createCriteria(Receipt.class);
		return (ArrayList<Receipt>) criteria.list();
	}

	@Override
	public ArrayList<StockTransfer> getAllStockTransfer(String storeName) {
		ArrayList<StockTransfer> stocks = new ArrayList<StockTransfer>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("select transferId, sourceVendor, date, itemName, quantity from StockTransfer where targetVendor like :storeName and status = :status");
		query.setParameter("storeName", storeName);
		query.setParameter("status","Initiated");
		ArrayList data = (ArrayList) query.list();
		
		for (Iterator it = data.iterator(); it.hasNext(); ) {
            Object[] myResult = (Object[]) it.next();
            int transferId = (Integer) myResult[0];
            String sourceVendor = (String) myResult[1];
            String date = (String) myResult[2];
            String itemName = (String) myResult[3];
            Float quantity = (Float) myResult[4];
            String targetVendor = storeName;
            String status = "Initiated";
            stocks.add(new StockTransfer(transferId,sourceVendor,targetVendor,date,itemName,quantity,status));
            
         }
		
		session.getTransaction().commit();

		return stocks;
		
	}

	@Override
	public void confirmedStocks(ArrayList<Integer> transferIds) {
		System.out.println("Confirmed Request DAO layer");
		
		ArrayList<Integer> ids = transferIds;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		for(Integer id : transferIds){
			
			System.out.println("Confirmed Id is = "+id);
			Query query = session.createQuery("update StockTransfer set status = :status" +
					" where transferId = :transferId");
			
			query.setParameter("status", "Confirmed");
			
			query.setParameter("transferId", id);
			query.executeUpdate();
		}
		
		session.getTransaction().commit();
	}

	@Override
	public void updateReceipts(int id,String customerName,String itemName,String quantity,String receiptOutletName,String amount,String date) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		int updatedQuantity = Integer.parseInt(quantity);
		int updatedAmount = Integer.parseInt(amount);
		
		Query query = session.createQuery("update Receipt set "+
						"customerName = :customerName," +
						"itemName = :itemName," +
						"quantity = :quantity,"+
						"receiptOutletName = :receiptOutletName,"+
						"amount = :amount,"+
						"date = :date"+
				        " where id = :id");
		
		query.setParameter("id", id);
		query.setParameter("customerName", customerName);
		query.setParameter("itemName", itemName);
		query.setParameter("quantity", updatedQuantity);
		query.setParameter("receiptOutletName", receiptOutletName);
		query.setParameter("amount", updatedAmount);
		query.setParameter("date", date);
		query.executeUpdate();
		
		session.getTransaction().commit();
	}

	@Override
	public ArrayList<StockTransfer> getEveryStockTransfers() {
		
		Criteria criteria = sessionFactory.openSession().createCriteria(StockTransfer.class);
		return (ArrayList<StockTransfer>) criteria.list();
	}

	@Override
	public void outGoingStockTransfers(ArrayList<StockTransfer> stockList) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		for(StockTransfer st: stockList){
			session.save(st);
		}
		session.getTransaction().commit();
		
		
	}

	@Override
	public void addBulkItems(ArrayList<Items> itemsList) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		for(Items items: itemsList){
			session.save(items);
		}
		session.getTransaction().commit();
		
	}
	
	
	
	
	

}
