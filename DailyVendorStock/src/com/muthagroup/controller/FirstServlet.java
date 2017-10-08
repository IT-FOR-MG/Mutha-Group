package com.muthagroup.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
	MuthaGroupDAO dao=new MuthaGroupDAO();
	MuthaGroupVO vo=new MuthaGroupVO();
	
	String username=null;
	String password=null;
	String report=null;
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			boolean flag1=false;
			boolean flag2=false;
			HttpSession session=request.getSession(false);
			username=request.getParameter("user");
			password=request.getParameter("pass");
			vo.setUsername(username);
			vo.setPassword(password);
			if(username != null && password != null)
			{
			flag1=dao.validateUser(vo);
				if(flag1==true)
				{
					session=request.getSession();  
					session.setAttribute("user",request.getParameter("user"));
					flag2=dao.getReport(vo);
					if(flag2==true){
					request.setAttribute("itemList",vo.getItem());
					
					request.setAttribute("vendors1010100280",vo.getVendors1());
					request.setAttribute("vendors1010101165",vo.getVendors2());
					request.setAttribute("vendors1010101084",vo.getVendors3());
					request.setAttribute("vendors1010101075",vo.getVendors4());
					request.setAttribute("vendors1010100646",vo.getVendors5());
					request.setAttribute("vendors1010100633",vo.getVendors6());
					request.setAttribute("vendors1010101365",vo.getVendors7());
					request.setAttribute("vendors1010101370",vo.getVendors8());
					request.setAttribute("vendors1010101371",vo.getVendors9());
					request.setAttribute("vendors1010101557",vo.getVendors10());
					request.setAttribute("vendors1010101210",vo.getVendors11());
					request.setAttribute("vendors1010101209",vo.getVendors12());
					
					request.setAttribute("vendorStock1010100280",vo.getVendorstock1());
					request.setAttribute("vendorStock1010101165",vo.getVendorstock2());
					request.setAttribute("vendorStock1010101084",vo.getVendorstock3());
					request.setAttribute("vendorStock1010101075",vo.getVendorstock4());
					request.setAttribute("vendorStock1010100646",vo.getVendorstock5());
					request.setAttribute("vendorStock1010100633",vo.getVendorstock6());
					request.setAttribute("vendorStock1010101365",vo.getVendorstock7());
					request.setAttribute("vendorStock1010101370",vo.getVendorstock8());
					request.setAttribute("vendorStock1010101371",vo.getVendorstock9());
					request.setAttribute("vendorStock1010101557",vo.getVendorstock10());
					request.setAttribute("vendorStock1010101210",vo.getVendorstock11());
					request.setAttribute("vendorStock1010101209",vo.getVendorstock12());
					
					
					request.setAttribute("customerList1010100280",vo.getCustomer1());
					request.setAttribute("customerList1010101165",vo.getCustomer2());
					request.setAttribute("customerList1010101084",vo.getCustomer3());
					request.setAttribute("customerList1010101075",vo.getCustomer4());
					request.setAttribute("customerList1010100646",vo.getCustomer5());
					request.setAttribute("customerList1010100633",vo.getCustomer6());
					request.setAttribute("customerList1010101365",vo.getCustomer7());
					request.setAttribute("customerList1010101370",vo.getCustomer8());
					request.setAttribute("customerList1010101371",vo.getCustomer9());
					request.setAttribute("customerList1010101557",vo.getCustomer10());
					request.setAttribute("customerList1010101210",vo.getCustomer11());
					request.setAttribute("customerList1010101209",vo.getCustomer12());

					request.setAttribute("schedule1010100280",vo.getSchedule1());
					request.setAttribute("schedule1010101165",vo.getSchedule2());
					request.setAttribute("schedule1010101084",vo.getSchedule3());
					request.setAttribute("schedule1010101075",vo.getSchedule4());
					request.setAttribute("schedule1010100646",vo.getSchedule5());
					request.setAttribute("schedule1010100633",vo.getSchedule6());
					request.setAttribute("schedule1010101365",vo.getSchedule7());
					request.setAttribute("schedule1010101370",vo.getSchedule8());
					request.setAttribute("schedule1010101371",vo.getSchedule9());
					request.setAttribute("schedule1010101557",vo.getSchedule10());
					request.setAttribute("schedule1010101210",vo.getSchedule11());
					request.setAttribute("schedule1010101209",vo.getSchedule12());
					
					request.setAttribute("dispatch1010100280",vo.getDispatch1());
					request.setAttribute("dispatch1010101165",vo.getDispatch2());
					request.setAttribute("dispatch1010101084",vo.getDispatch3());
					request.setAttribute("dispatch1010101075",vo.getDispatch4());
					request.setAttribute("dispatch1010100646",vo.getDispatch5());
					request.setAttribute("dispatch1010100633",vo.getDispatch6());
					request.setAttribute("dispatch1010101365",vo.getDispatch7());
					request.setAttribute("dispatch1010101370",vo.getDispatch8());
					request.setAttribute("dispatch1010101371",vo.getDispatch9());
					request.setAttribute("dispatch1010101557",vo.getDispatch10());
					request.setAttribute("dispatch1010101210",vo.getDispatch11());
					request.setAttribute("dispatch1010101209",vo.getDispatch12());
					
					request.setAttribute("castrec1010100280",vo.getCastrec1());
					request.setAttribute("castrec1010100280",vo.getCastrec1());
					request.setAttribute("castrec1010101165",vo.getCastrec2());
					request.setAttribute("castrec1010101084",vo.getCastrec3());
					request.setAttribute("castrec1010101075",vo.getCastrec4());
					request.setAttribute("castrec1010100646",vo.getCastrec5());
					request.setAttribute("castrec1010100633",vo.getCastrec6());
					request.setAttribute("castrec1010101365",vo.getCastrec7());
					request.setAttribute("castrec1010101370",vo.getCastrec8());
					request.setAttribute("castrec1010101371",vo.getCastrec9());
					request.setAttribute("castrec1010101557",vo.getCastrec10());
					request.setAttribute("castrec1010101210",vo.getCastrec11());
					request.setAttribute("castrec1010101209",vo.getCastrec12());
					}
					RequestDispatcher view=getServletContext().getRequestDispatcher("/mainpage.jsp");
					view.forward(request, response);
						
				}
				else 
				{
					response.sendRedirect("index.jsp");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace(response.getWriter());
		}
		}

}
