package com.muthagroup.controller;

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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.muthagroup.connection.ConnectionModel;

@WebServlet("/BarcodeExcel")
public class BarcodeExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try
        {
			  FileInputStream file = new FileInputStream(new File("/home/itsupports/workspace/materialstock/howtodoinjava_demo.xlsx"));
			  
	            //Create Workbook instance holding reference to .xlsx file
	            @SuppressWarnings("resource")
				XSSFWorkbook workbook = new XSSFWorkbook(file);
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            Document document = new Document();
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                PdfWriter.getInstance(document, baos);
                document.open(); 
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                Cell cell = cellIterator.next();
	                DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
	                String mat_code=formatter.formatCellValue(cell);
	                cell = cellIterator.next();
	       			String com_name=formatter.formatCellValue(cell);
	       			cell = cellIterator.next();
	       			String mat_type=formatter.formatCellValue(cell);
	                System.out.println("Row Matcode"+mat_code);
	                System.out.println("Matcode"+mat_code);                
	                int id=0;
	            	Calendar cal = Calendar.getInstance();
	            	int year = cal.get(Calendar.YEAR);
	       			int month = cal.get(Calendar.MONTH)+1;
	       			int day=cal.get(Calendar.DAY_OF_MONTH);
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
	                
	       			for(int i=1;i <= 10;i++)
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
	        		PdfPCell pdfcell= new PdfPCell();
	        		pdfcell.addElement(chunk);
	        		pdfcell.setRowspan(3);
	       			table.addCell("Date");
	       			table.addCell(day+"/"+month+"/"+year);
	        		table.addCell(pdfcell);
	       			table.addCell("Common Name");
	       			table.addCell(com_name);
	       			table.addCell("Part Name");
	       			table.addCell(mat_type);
	       			document.add(table);
	       			}      
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
                System.out.println("");
	            file.close();  
        
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}

}
