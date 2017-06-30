package com.shantanu.myinventory.controller;

import java.awt.PageAttributes.MediaType;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shantanu.myinventory.model.DataFromClient;
import com.shantanu.myinventory.model.Items;
import com.shantanu.myinventory.model.OutgoingTransferRequests;
import com.shantanu.myinventory.model.Receipt;
import com.shantanu.myinventory.model.StockTransfer;
import com.shantanu.myinventory.model.StoreItems;
import com.shantanu.myinventory.model.UpdateReceipts;
import com.shantanu.myinventory.model.UserDetails;
import com.shantanu.myinventory.model.Vendors;
import com.shantanu.myinventory.service.ItemService;
import com.shantanu.myinventory.service.UserService;


@Controller
public class UserController {
	
	private static String UPLOADED_FOLDER = "./src/main/resources/";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/api/getAllItems", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Items>> getAllItems() {
        
		ArrayList<Items> allItems = itemService.getAllItems();
		for(Items i : allItems){
			System.out.println("Item Name :- "+i.getItemName());
		}
		//return null;
		return new ResponseEntity<ArrayList<Items>>(allItems, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/api/getAllReceipts", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Receipt>> getAllReceipts() {
        
		ArrayList<Receipt> allReceipt = itemService.getAllReceipts();
		for(Receipt i : allReceipt){
			System.out.println("Item Name :- "+i.getItemName()+"\n customerName = "+i.getCustomerName());
		}
		//return null;
		return new ResponseEntity<ArrayList<Receipt>>(allReceipt, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/getEveryStockTransfers", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<StockTransfer>> getEveryStockTransfers() {
        
		ArrayList<StockTransfer> stockTransfers = itemService.getEveryStockTransfers();
		for(StockTransfer i : stockTransfers){
			System.out.println("Source Vendor Name :- "+i.getSourceVendor()+"\n target vendor name = "+i.getTargetVendor());
		}
		//return null;
		return new ResponseEntity<ArrayList<StockTransfer>>(stockTransfers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/getAllVendors", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Vendors>> getAllVendors() {
        
		ArrayList<Vendors> allVendors = itemService.getAllVendors();
		for(Vendors i : allVendors){
			System.out.println("Item Name :- "+i.getStoreName());
		}
		//return null;
		return new ResponseEntity<ArrayList<Vendors>>(allVendors, HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/addItem",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<Items> addItem(@RequestBody Items items){
		System.out.println("Items came = "+items);
		Items item = itemService.addItem(items);
		return new ResponseEntity<Items>(item,HttpStatus.OK);
	}  
	
	
	@RequestMapping(value="/api/receipts",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<Items> addReceipts(@RequestBody ArrayList<DataFromClient> receipts){
		
		itemService.addReceipts(receipts);
		
		return null;
	}
	
	
	@RequestMapping(value = "/api/getItemsByVendorName", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Float>> getItemsByVendorName(@RequestParam("storeName") String storeName) {
        System.out.println("storeName = "+storeName);
		HashMap<String, Float> data = itemService.getItemsByVendorName(storeName);
		return new ResponseEntity<HashMap<String, Float>>(data,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/getItemsByVendorName1", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<StoreItems>> getItemsByVendorName1(@RequestParam("storeName") String storeName) {
        System.out.println("storeName = "+storeName);
		ArrayList<StoreItems> data = itemService.getItemsByVendorName1(storeName);
		return new ResponseEntity<ArrayList<StoreItems>>(data,HttpStatus.OK);
	}
	
	//end point to get all unconfirmed stock transfer requests
	@RequestMapping(value = "/api/getAllStockTransfers", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<StockTransfer>> getAllStockTransfers(@RequestParam("storeName") String storeName) {
        System.out.println("storeName = "+storeName);
		ArrayList<StockTransfer> data = itemService.getAllStockTransfer(storeName);
		return new ResponseEntity<ArrayList<StockTransfer>>(data,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/api/confirmedIds",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<Items> confirmedReceipts(@RequestBody ArrayList<Integer> transferIds){
		
		System.out.println("Confirmed Request received and size = "+transferIds.size());
		itemService.confirmedStocks(transferIds);
		//itemService.addReceipts(receipts);
		
		return null;
	}
	
	
	@RequestMapping(value="/api/outgoingStockTransfers",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<Items> outGoingStockTransfers(@RequestBody ArrayList<OutgoingTransferRequests> stockTransfer){
		ArrayList<StockTransfer> stockList = new ArrayList<>();
		for(OutgoingTransferRequests st: stockTransfer){
			System.out.println("Source Vendor - "+st.getSourceVendor()+"\ntarget vendor = "+st.getTargetVendor()+"\n date = "+st.getTransferDate());
			StockTransfer st1 = new StockTransfer();
			st1.setSourceVendor(st.getSourceVendor());
			st1.setDate(st.getTransferDate());
			st1.setItemName(st.getItemName());
			st1.setTargetVendor(st.getTargetVendor());
			st1.setQuantity((float)st.getQuantity());
			st1.setStatus(st.getStatus());
			stockList.add(st1);
		}
		//itemService.addReceipts(receipts);
		itemService.outGoingStockTransfers(stockList);
		return null;
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/api/excelUpload",method = RequestMethod.POST,consumes= {"application/json","multipart/form-data"},produces="application/json")
	public ResponseEntity<Void> excelUpload(@RequestParam("file") MultipartFile file){
		
		String data = "Success";
		if(file.isEmpty()){
			System.out.println("Failed to upload");
			data = "Failed!!";
			
		}
		
		try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println("success check folder, file name = "+file.getOriginalFilename());
            
            
            int i = 0;
            FileInputStream excelFile = new FileInputStream(new File(UPLOADED_FOLDER+file.getOriginalFilename()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            ArrayList<Items> itemsList = new ArrayList<Items>();
            
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                i++;
                
                Iterator<Cell> cellIterator = currentRow.iterator();
                Items item = new Items();
                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    
                    if(i > 1){
                    	switch (currentCell.getColumnIndex()) {
						case 0:
							item.setItemName(currentCell.getStringCellValue());
							break;
						case 1:
							item.setItemGroup(currentCell.getStringCellValue());
							break;
						case 2:
							item.setQuantity((float)currentCell.getNumericCellValue());
							break;
						case 3:
							item.setOutlet(currentCell.getStringCellValue());
							break;
						case 4:
							item.setUom(currentCell.getStringCellValue());
							break;
						case 5:
							item.setRate((float)currentCell.getNumericCellValue());
							break;
							

						default:
							break;
						}
	                   /* if(currentCell.getColumnIndex() == 0){
	                    	System.out.print(currentCell.getStringCellValue() + "--");
	                    }*/
                    }
                }
                
                if(item.getItemName()!=null){
                	itemsList.add(item);
                }

            }
            
            itemService.addBulkItems(itemsList);

        } catch (IOException e) {
        	System.out.println("Error "+e.getMessage());
            e.printStackTrace();
            
        }
		
		//return new ResponseEntity<String>(data,HttpStatus.OK);
		return null;
	}
	
	@RequestMapping(value="/api/updateReceipts",method = RequestMethod.POST,consumes="application/json",produces="application/json")
	public ResponseEntity<Items> updateReceipts(@RequestBody UpdateReceipts receipts){
		
		String customerName = receipts.getCustomerName();
		String itemName = receipts.getItemName();
		int id = Integer.parseInt(receipts.getId());
		String quantity = receipts.getQuantity();
		String receiptOutletName = receipts.getReceiptOutletName();
		String amount = receipts.getAmount();
		String date = receipts.getDate();
		System.out.println("Update id = "+receipts.getId());
		itemService.updateReceipts(id,customerName,itemName,quantity,receiptOutletName,amount,date);
		
		return null;
	}
	
	
	
	
	@RequestMapping(value="/api/test", method = RequestMethod.GET)
	public String test(){
		return "Hello Boy";
	}
}
