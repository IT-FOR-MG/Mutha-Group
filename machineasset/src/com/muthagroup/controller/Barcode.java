package com.muthagroup.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.muthagroup.connection.ConnectionModel;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import java.util.Calendar;

@WebServlet("/Barcode")
public class Barcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
        	int machine_id=0;
        	Calendar cal = Calendar.getInstance();
        	int year = cal.get(Calendar.YEAR);
   			int month = cal.get(Calendar.MONTH)+1;
   			int day=cal.get(Calendar.DAY_OF_MONTH);
   			String machineName=request.getParameter("machineName");
   			String machineCategory=request.getParameter("machineCategory");
   			String machineMake=request.getParameter("machineMake");
   			String purchaseDate=(String)request.getParameter("puchaseDate");
   			String purchaseCost=(String)request.getParameter("puchaseCost");
   			String location=request.getParameter("location");
   			String condtion=request.getParameter("condtion");
   			Document document = new Document();
        	Connection con= ConnectionModel.getConnection2();
        	String SQL = "SELECT MAX(CAST((machine_id) AS UNSIGNED)) FROM machine_data "; 
        	Statement stmt = con.createStatement(); 
        	HttpSession session = request.getSession();
			String user_id=(String)session.getAttribute("user"); 
        	ResultSet rs = stmt.executeQuery(SQL);
        	if(rs.next())
   			{
   			if(rs.getString(1)!= null)
        	{
   				machine_id=Integer.parseInt(rs.getString(1))+1;
        	}
   			else
        	{
   				machine_id=0001;
        	}
   			}    	
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open(); 
   			con= ConnectionModel.getConnection2();
   			PreparedStatement ps =con.prepareStatement("insert into machine_data values(?,?,?,?,?,?,?,?)");
 			ps.setInt(1,machine_id);
   			ps.setString(2,machineName);
   			ps.setString(3,machineCategory);
   			ps.setString(4,machineMake);
   			ps.setString(5,purchaseDate);
   			ps.setString(6,purchaseCost);
   			ps.setString(7,location);
   			ps.setString(8,condtion);
   			ps.executeUpdate();     
   			BarcodeQRCode qrcode = new BarcodeQRCode("Machine Name:"+machineName+"\n"+"Machine Category: "+machineCategory+"\n"+"Machine Make: "+machineMake+"\n"
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
        }
        catch(Exception e) {
          e.printStackTrace();
        } 
    }
}
