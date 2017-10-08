package com.muthagroup.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.muthagroup.dao.MuthaGroupDAO;
import com.muthagroup.vo.MuthaGroupVO;
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    MuthaGroupDAO dao=new MuthaGroupDAO();
	MuthaGroupVO vo=new MuthaGroupVO();
	String date=null;
	String records[]=null;
	Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Formatter fmt = new Formatter();
			date=fmt.format("%tb",c)+" "+String.format("%04d",year);	
			fmt.close();
			HttpSession session=request.getSession();
			boolean flag=false;
			switch(request.getParameter("type"))
			{
			 case "login":
						flag=validateUser(request, response);
						break;
			 case "logout":
						flag=logOutUser(request, response); 
						break;
			 case "date":
				 		date=request.getParameter("date");
				 		vo.setDate(date);
				 		flag=true;
				 		break;
			 case "records":
			     			records=request.getParameterValues("records");
			     			flag=true;
			     			break;
			 case "barcode":
				           flag=getBarcode(request, response);
				           break;
			  
			}
			if(flag)
			{
				if ((session.getAttribute("user")!=null)) 
		    	{
					dao.getMachineStatus(vo);
		    	}
				response.sendRedirect("mainpage.jsp");
			}
			else
			{	
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private boolean getBarcode(HttpServletRequest request, HttpServletResponse response) {
		try {
		    vo.setMachineName(request.getParameter("machineName"));
			vo.setMachineCategory(request.getParameter("machineCategory"));
			vo.setMachineMake(request.getParameter("machineMake"));
			vo.setPurchaseDate(request.getParameter("puchaseDate"));
			vo.setPurchaseCost(request.getParameter("puchaseCost"));
			vo.setLocation(request.getParameter("location"));
			vo.setCondtion(request.getParameter("condtion"));
			int machine_id=dao.getBarcode(vo);
			String machineName=vo.getMachineName();
   			String machineCategory=vo.getMachineCategory();
   			String machineMake=vo.getMachineMake();
   			String purchaseDate=vo.getPurchaseDate();
   			String purchaseCost=vo.getPurchaseCost();
   			String location=vo.getLocation();
   			String condtion=vo.getCondtion();
   			Document document = new Document();
   		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
   		    PdfWriter.getInstance(document, baos);
   		    document.open();
			BarcodeQRCode qrcode = new BarcodeQRCode("Machine Name:"+machineName+"\n"+"Machine Category: "
					+machineCategory+"\n"+"Machine Make: "+machineMake+"\n"+"Purchase Date: "+purchaseDate+"\n"
					+"Purchase Cost: "+purchaseCost+"\n"+"Location: "+location+"\n"+"Condition: "+ condtion, 1, 1, null);
			Image qrcodeImage = qrcode.getImage();
			PdfPTable table = new PdfPTable(1);
			table.setWidthPercentage(100);
			table.setSpacingAfter(5f);
			qrcodeImage.scaleAbsolute(100,100);
			Chunk chunk = new Chunk(qrcodeImage, 0,0);
			PdfPCell cell= new PdfPCell();
			cell.addElement(chunk);
			table.addCell(cell);
			table.addCell(Integer.toString(machine_id));
			document.add(qrcodeImage);
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
			}catch (Exception e) {
				e.printStackTrace();
			}
		return true;
	}

	private boolean logOutUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session=request.getSession();  
            session.invalidate();   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean validateUser(HttpServletRequest request, HttpServletResponse response) {
    	try{
    	boolean validationStatus=false;
    	String username=request.getParameter("user");
    	String password=request.getParameter("pass");
		vo.setUserName(username);
		vo.setPassword(password);
		validationStatus=dao.validateUser(vo);
			if(validationStatus)
			{
				HttpSession session=request.getSession(false);
				session=request.getSession();  
				session.setAttribute("user",request.getParameter("user"));
				session.setAttribute("vo",vo);
				return true;		
			}
			else
			{
				return false;
			}
    	}catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		return false;
	}
	

}
