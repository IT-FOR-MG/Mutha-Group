package com.muthagroup.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muthagroup.vo.MuthaGroupVO;

/**
 * Servlet implementation class PrintDoc
 */
@WebServlet("/PrintDoc")
public class PrintDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	int count=0;
        	HttpSession session=null;	
    		session=request.getSession();  
    		MuthaGroupVO vo=(MuthaGroupVO) session.getAttribute("vo");
    		String date=vo.getDate();
    		LinkedHashMap<Long,String> item=vo.getItem();
    		ArrayList<ArrayList<String>> customerstock=vo.getCustomerRecords();
    		ArrayList<ArrayList<String>> vendorstock=vo.getVendorRecords(); 
    		ArrayList<ArrayList<String>> internalStock=vo.getInternalRecords(); 
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
        	Document document = new Document(PageSize.A4);
        	document.setMargins(10,10,10,10);
        	PdfWriter.getInstance(document, baos);
        	document.open();
        	PdfPTable mainTable = new PdfPTable(2);
    		PdfPTable custmerTable = new PdfPTable(4);
    		PdfPTable vendorTable = new PdfPTable(9);
    		PdfPTable internalTable = new PdfPTable(7);
    		PdfPCell cell;
    		mainTable.setWidthPercentage(100);	
    		custmerTable.setWidthPercentage(100);
    		internalTable.setWidthPercentage(100);
    		cell = new PdfPCell(new Phrase("DAILY STATUS OF M/C COMPONENT Date:"+date, FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));
    		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    		cell.setColspan(2);
    		mainTable.addCell(cell);
    		custmerTable.addCell(new Phrase("Item", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//1
    		custmerTable.addCell(new Phrase("Customer", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//2
    		custmerTable.addCell(new Phrase("Schedule", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//3
    		custmerTable.addCell(new Phrase("Dispatch", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//4
    		vendorTable.addCell(new Phrase("Item", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//1
    		vendorTable.addCell(new Phrase("Vendor", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//5
    		vendorTable.addCell(new Phrase("ERP Stock", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//6
    		vendorTable.addCell(new Phrase("Casting Received", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//7
    		vendorTable.addCell(new Phrase("Production", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//8
    		vendorTable.addCell(new Phrase("Packed", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//9
    		vendorTable.addCell(new Phrase("For Packing", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//10
    		vendorTable.addCell(new Phrase("For Insp.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//11
    		vendorTable.addCell(new Phrase("Total", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//12
    		internalTable.addCell(new Phrase("Item", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//1
    		internalTable.addCell(new Phrase("Yest. Invoice", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//13
    		internalTable.addCell(new Phrase("Yest. Dispatch", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//14
    		internalTable.addCell(new Phrase("Pending Invoice", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//15
    		internalTable.addCell(new Phrase("Today Plan", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//16
    		internalTable.addCell(new Phrase("Yest. Pouring", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//17
    		internalTable.addCell(new Phrase("Commu.", FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f,Font.BOLD)));//18
    		for (Map.Entry<Long,String> entry : item.entrySet()) 
			{
    			cell = new PdfPCell(new Phrase(entry.getValue(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f)));
    			count=0;
    			for(int i=0;i<customerstock.size();i++)
    			{
    			if(customerstock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    				count++;
    			}
    			}
    			if(count!=0){
    			cell.setRowspan(count);
    			}
    			custmerTable.addCell(cell);
    			for(int i=0;i<customerstock.size();i++)
    			{
    			if(customerstock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    			for(int j=1;j<=3;j++)
    			{
    					
    				custmerTable.addCell(new Phrase(customerstock.get(i).get(j), FontFactory.getFont(FontFactory.TIMES_ROMAN,4.7f)));	
    			}
    			}
    			}
    			cell = new PdfPCell(new Phrase(entry.getValue(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f)));
    			count=0;
    			for(int i=0;i<vendorstock.size();i++)
    			{
    			if(vendorstock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    				count++;
    			}
    			}
    			if(count!=0){
    			cell.setRowspan(count);
    			}
    			vendorTable.addCell(cell);
    			for(int i=0;i<vendorstock.size();i++)
    			{
    			if(vendorstock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    			for(int j=2;j<=9;j++)
    			{		
    				vendorTable.addCell(new Phrase(vendorstock.get(i).get(j), FontFactory.getFont(FontFactory.TIMES_ROMAN,4.7f)));	
    			}
    			}
    			}
    			cell = new PdfPCell(new Phrase(entry.getValue(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 4.7f)));
    			count=0;
    			for(int i=0;i<internalStock.size();i++)
    			{
    			if(internalStock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    				count++;
    			}
    			}
    			if(count!=0){
    			cell.setRowspan(count);
    			}
    			internalTable.addCell(cell);
    			for(int i=0;i<internalStock.size();i++)
    			{
    			if(internalStock.get(i).get(0).equals((entry.getKey()).toString()))
    			{
    			for(int j=1;j<=6;j++)
    			{		
    				internalTable.addCell(new Phrase(internalStock.get(i).get(j), FontFactory.getFont(FontFactory.TIMES_ROMAN,4.7f)));	
    			}
    			}
    			}
    			
    		}
    		cell=new PdfPCell(custmerTable);
    		cell.setPadding(0);
    		mainTable.addCell(cell);
    		cell=new PdfPCell(internalTable);
    		cell.setPadding(0);
    		mainTable.addCell(cell);
    		cell=new PdfPCell(vendorTable);
    		cell.setPadding(0);
    		cell.setColspan(2);
    		mainTable.addCell(cell); 
    		document.add(mainTable);
   			document.close();
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/pdf");
            response.setContentLength(baos.size());
            ServletOutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        	} catch (DocumentException e) {
			e.printStackTrace();
        	}
      
	}

}
