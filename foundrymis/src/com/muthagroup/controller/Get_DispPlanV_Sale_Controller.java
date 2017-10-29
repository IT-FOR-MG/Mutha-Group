package com.muthagroup.controller;

import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class Get_DispPlanV_Sale_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//*************************************************************************************************************
			System.out.println("In controller");
			String company="",cust = ""; 
			company = request.getParameter("company");					 
			cust = request.getParameter("cust");	  
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
			
			Date convertedDatefrom = formatter.parse(request.getParameter("custdPlanDate"));
			String todaysDate= new SimpleDateFormat("yyyyMMdd").format(convertedDatefrom); 
			
			//*************************************************************************************************************
			  
			 //System.out.println(" Cust = " + cust+" Date = "+todaysDate);
			if(company!="" && cust!=""){ 
					response.sendRedirect("DispPlanV_Sale.jsp?comp="+company+"&cust="+cust+"&ondate="+todaysDate);
			}  
//	Example	:	exec SEL_MSTMATERIALGLSUB @intSubGlAcNo=101110004,@intMaterialType=101
//			    exec "ENGERP"."dbo"."Sel_RptDespatchPlanSale";1 '101', '0', '101120010101110184101110287101120024101120051101110371101121443101120070101120097101120116101120118101110199101110192101121508101110291101122471101122962101110004101110005101122256101122843101110008101160001101110007101110399101121718101120192101110316101110251101120203101110010101120238101110012101110013101122629101121772101120275101110015101110016101110017101120307101120324101120340101110021101122977101122315101110282101110022101110353101122526101110200101110396101123531101120429101110209101120431101110382101110304101110408101110141101110248101121974101121388101120466101110185101120472101110027101110309101122388101123248101110144101110028101110261101123590101110187101120583101110374101120609101110161101110031101110205101122002101110347101123158101120646101110032101120648101110033101120657101110036101110376101110135101110230101110037101110045101110390101110132101120669101123634101110040101110130101110393101110400101123219101122072101120697101110342101121402101120743101110183101122106101122766101121537101120789101110370101110322101110044101110131101110186101120816101122673101110302101121561101120863101121924101120880101110387101110198101110373101122684101121061101110368101110149101120944101123661101122416101120979101121744101110191101121007101121024101110325101121979101121048101121063101121062101121065101121067101121074101110250101110249101121087101121088101110402101123530101122396101110354101110369101110307101121188101121709101110269101110305101110047101110150101110164101110140101110048101122622101110326101110298101121290101121288101110379101110049101110117101110181101110247101121322101121348101110051101121350101110063101121730101110362101110077101121863', '20141130'
		
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

}
