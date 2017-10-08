package com.muthagroup.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
			 vo.setDate(date);
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
			     			vo.setRecords(records);
			     			flag=true;
			}
			if(flag)
			{
				if ((session.getAttribute("user")!=null)) 
		    	{
					dao.getStockStatus(vo);
					dao.getItemwiseStockStatus(vo);
					dao.getMaterialList(vo);
		    	}
				response.sendRedirect("mainpage.jsp");
			}
			else
			{	
				response.sendRedirect("index.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
		}
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
