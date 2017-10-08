package dailyVedndorStock;

import java.io.ByteArrayOutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class DetailReport {

	public static void main(String[] args) {
		try{
			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
   			table.setWidths(new int[]{2,7,1});
   			table.setSpacingAfter(5f);
   			PdfPCell cell= new PdfPCell();
            document.open(); 
			Connection con = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        con = DriverManager.getConnection("jdbc:sqlserver://192.168.0.6:1433;databaseName=FOUNDRYERP;user=BWAYS;password=BWAYSKING321 ");
	        String SQL = "select * from  MSTMATERIALS where MATERIAL_TYPE=101 "; 
	     	Statement stmt = con.createStatement(); 
	     	ResultSet rs = stmt.executeQuery(SQL);
	     	 
    
	        
	        
	        
	        
	        CallableStatement cs = con.prepareCall("{call Sel_RptDespatchPlanSale('103', '0', '101110189101110377101110190', '20170316')}");
	        //ResultSet rs = cs.executeQuery();
	}catch(Exception e){
		e.printStackTrace();
	}

	}
}
