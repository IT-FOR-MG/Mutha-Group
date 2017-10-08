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
        	int id=0;
        	Calendar cal = Calendar.getInstance();
        	int year = cal.get(Calendar.YEAR);
   			int month = cal.get(Calendar.MONTH)+1;
   			int day=cal.get(Calendar.DAY_OF_MONTH);
   			String mat_code=request.getParameter("mat");
   			String com_name=request.getParameter("cname");
   			String mat_type=request.getParameter("pname");
   			Document document = new Document();
        	Connection con= ConnectionModel.getConnection2();
        	String SQL = "SELECT MAX(CAST((substring(barcode_id,19,4)) AS UNSIGNED)) FROM barcode_tbl where mat_code='"+mat_code+"' AND YEAR(date)=YEAR(CURDATE()) AND MONTH(date)= MONTH(CURDATE()) "; 
        	Statement stmt = con.createStatement(); 
        	HttpSession session = request.getSession();
			String user_id=(String)session.getAttribute("user"); 
        	ResultSet rs = stmt.executeQuery(SQL);
        	
        	if(rs.next())
   			{
   			if(rs.getString(1)!= null)
        	{
   				id=Integer.parseInt(rs.getString(1));
        	}
   			else
        	{
        	 id=0000;
        	}
   			}    	
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open(); 
   			
   			for(int i=1;i <= Integer.parseInt(request.getParameter("qty"));i++)
   			{
   			con= ConnectionModel.getConnection2();
   			PreparedStatement ps =con.prepareStatement("insert into barcode_tbl (mat_code,user_id,date,barcode_id,mat_name) values(?,?,?,?,?)");
   			ps.setString(1,mat_code);
   			ps.setString(2,user_id);
   			ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
   			ps.setString(4,String.format("%02d", day)+String.format("%02d",month)+String.format("%04d",year)+mat_code+String.format("%04d",++id));
   			ps.setString(5,mat_type);
   			ps.executeUpdate();
   			document.add(new Paragraph(String.format("%02d", day)+String.format("%02d",month)+String.format("%04d",year)+mat_code+String.format("%04d",id)));	
   			PdfPTable table = new PdfPTable(3);
   			table.setWidthPercentage(100);
   			table.setWidths(new int[]{2,7,1});
   			table.setSpacingAfter(5f);       
   			BarcodeQRCode qrcode = new BarcodeQRCode(String.format("%02d", day)+String.format("%02d",month)+String.format("%04d",year)+mat_code+String.format("%04d",id), 1, 1, null);
    		Image qrcodeImage = qrcode.getImage();
    		qrcodeImage.scaleAbsolute(50, 50);
    		Chunk chunk = new Chunk(qrcodeImage, 0,0);
    		PdfPCell cell= new PdfPCell();
    		cell.addElement(chunk);
    		cell.setRowspan(3);
   			table.addCell("Date");
   			table.addCell(day+"/"+month+"/"+year);
    		table.addCell(cell);
   			table.addCell("Common Name");
   			table.addCell(com_name);
   			table.addCell("Part Name");
   			table.addCell(mat_type);
   			document.add(table);
   			}
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
