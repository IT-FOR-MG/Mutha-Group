package com.muthagroup.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.muthagroup.dao.MuthaGroupDAO;
import com.muthagroup.vo.MuthaGroupVO;

/**
 * Servlet implementation class FirstServlet
 */

@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	MuthaGroupDAO dao=new MuthaGroupDAO();
	MuthaGroupVO vo=new MuthaGroupVO();
	Calendar c ;
	int  year,month,day,hour,minute;
	String date,inTime,outTime;
	private static final long serialVersionUID = 1L;
    public FirstServlet() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		 c = Calendar.getInstance();
		 year = c.get(Calendar.YEAR);
	     month = c.get(Calendar.MONTH)+1;
		 day=c.get(Calendar.DAY_OF_MONTH);
	     hour=c.get(Calendar.HOUR);
	     minute=c.get(Calendar.MINUTE);
	     date=String.valueOf(year)+"/"+String.format("%02d", month)+"/"+String.format("%02d", day);
		 vo.setDate(date);
		boolean flag=false;
		switch(request.getParameter("type")){
		  case "login":
			flag=validateUser(request, response);
		    break;
		  case "logout":
			flag=logoutUser(request, response); 
		    break;
		  case "date" :
			  date=request.getParameter("date");
		 	  vo.setDate(date);
		 	  flag=true;
		 	  break;
		}
		if(flag)
		{
			if ((session.getAttribute("user")!=null)) 
	    	{
	    		dao.displayEntry(vo);
	    	}
			response.sendRedirect("mainpage.jsp");
			//RequestDispatcher view=getServletContext().getRequestDispatcher("/mainpage.jsp");
			//view.forward(request, response);
		}
		else
		{	
			response.sendRedirect("index.jsp");
			//RequestDispatcher view=getServletContext().getRequestDispatcher("/index.jsp");
			//view.forward(request, response);	
		}
	}
	public boolean validateUser(HttpServletRequest request, HttpServletResponse response){
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
	public boolean logoutUser(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session=request.getSession();  
            session.invalidate();   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}	
}