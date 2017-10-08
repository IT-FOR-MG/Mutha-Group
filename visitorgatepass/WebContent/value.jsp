<%@ page import="java.io.*,java.sql.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="com.muthagroup.vo.MuthaGroupVO"%>
<%
			MuthaGroupVO vo=(MuthaGroupVO)session.getAttribute("vo"); 
            String sn=request.getParameter("ver");
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/odk_prod", "root","root");
                    Statement st=con.createStatement();
                    ArrayList<String> empUser=vo.getEmpUser();
                    //ResultSet rs = st.executeQuery("select * from emp where empno="+sn);
                    ResultSet rs = st.executeQuery("select * from visitor_entry where mob = '"+sn+"'");  // this is for name
                    if(rs.next())
                    {   
                    	%>
                      <input type="text" name="firstName" value=<%=rs.getString("first_name") %> id="firstName" placeholder="First Name" required>
       				  <input type="text" name="middleName" value=<%=rs.getString("middle_name") %> id="middleName" placeholder="Middle Name" required>
       				  <input type="text" name="lastName"  value=<%=rs.getString("last_name") %> id="lastName" placeholder="Last Name" required>
       				  <input type="text" name="address"  value=<%=rs.getString("address") %> id="address" placeholder="Address" required>	
                        <%
                    }
                    else
                    {
                    	%>
                      <input type="text" name="firstName"  value="" id="firstName" placeholder="First Name" required>
       				  <input type="text" name="middleName" value="" id="middleName" placeholder="Middle Name" required>
       				  <input type="text" name="lastName"   value="" id="lastName" placeholder="Last Name" required>
       				  <input type="text" name="address"    value="" id="address" placeholder="Address" required>	
						<%                    
                    }
 
rs.close();
st.close();
con.close();
 
%>