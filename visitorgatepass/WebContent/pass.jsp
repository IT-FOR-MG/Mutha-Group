<%@ page import="java.io.*,java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
 
<%
 
            String sn=request.getParameter("ver");
 
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odk_prod", "root","root");
                    Statement st=con.createStatement();
                    //ResultSet rs = st.executeQuery("select * from emp where empno="+sn);
                    ResultSet rs = st.executeQuery("select distinct(mob) from visitor_entry where mob like '"+sn+"%'");  // this is for name
                    while(rs.next())
                    {    
                    	 out.println("<option>");
%>
                        <%=rs.getString("mob") %>
<%                    	 
                    	 out.println("</option>");
                    }
 
rs.close();
st.close();
con.close();
 
%>