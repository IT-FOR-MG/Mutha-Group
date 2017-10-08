package com.muthagroup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;  
import java.util.Properties;
import com.muthagroup.connectionmodel.ConnectionModel;
import com.muthagroup.vo.FurnaceStatusVO;
import javax.mail.Message; 
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage; 


public class FurnaceStatusDAO {
	boolean flag = false;
	Connection con=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	public String getUserName(FurnaceStatusVO vo) {
		String userName=null;
		try {
			con = ConnectionModel.getMIDIServerConnection();
			ps = con.prepareStatement("select U_Name from user_tbl where U_Id='" + vo.getUserID() + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				userName=rs.getString("U_Name");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;		
	}

	public void sendMail(FurnaceStatusVO vo) {
		String host = "send.one.com";
		String user = "itsupports@muthagroup.com";
		String pass = "itsupports@xyz"; 
		String from = "itsupports@muthagroup.com";
		String subject = "Mutha Founders Furnace Status by "+ vo.getUser();
		boolean sessionDebug = false;
		// *********************************************************************************************
		// multiple recipients : == >
		// ********************************************************************************************* 
		try {
			String whmsg="A";
			Process proc = Runtime.getRuntime().exec("yowsup-cli demos -E s40 -c /home/itsupports/yowsup-develop/config -s 919175004128 "+whmsg);
			String recipients[] = {"udayav@muthagroup.com"};
			Properties props = System.getProperties();
			props.put("mail.host", host);
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", 465);
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int p = 0; p < recipients.length; p++) {
				addressTo[p] = new InternetAddress(recipients[p]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(
					"<p><b style='color: #0D265E;'>*** This is an automatically generated email from IT-Tracker ***</b>"
							+ "</p>"
							+ "<table border='1' width='100%'><tr style='font-size: 12px; background-color: #acc8cc; border-width: 1px; padding: 8px; border-style: solid;border-color: #729ea5;text-align: center;'>"
							+ "	<th>Shift</th>" + "	<th>Date </th>" + "	<th>Time</th>" + "	<th>Furnace ID</th>" + "	<th>Status Image</th>" + "</tr>"
							+ "	<tr style='font-size: 12px;text-align: center;'>"
							+ "	<td>" + vo.getShift() + "</td>"
							+ "	<td>" + vo.getDate() + "</td>" 
							+ "	<td>" + vo.getTime() + "</td>" 
							+ "	<td>" + vo.getFurnaceId() + "</td>" 
							+ " <td>" + " <img id='profileImage' width='300' height='200' src='data:image/jpeg;base64," + vo.getStringImage() + "'  > </td>" + "	</tr>"
							+ "	</table>"
							+ " </p><p><b style='color: #330B73;'>Thanks & Regards </b></P><p> Mutha Group Satara </p>"
							+ "<hr>" + "<p><b>Disclaimer :</b></p>" + "<font face='Times New Roman' size='1'>"
							+ "<p><b style='color: #49454F;'>The information transmitted, including attachments, is intended only for the person(s) or entity to which it is addressed and may contain confidential and/or privileged material. Any review, retransmission, dissemination or other use of, or taking of any action in reliance upon this information by persons or entities other than the intended recipient is prohibited. If you received this in error, please contact the sender and destroy any copies of this information.</b></p></font>",
					"Text/html");

			Transport transport = mailSession.getTransport("smtp");
			System.out.println("Get My Loop");
			transport.connect(host, user, pass);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			System.out.println("Get My Loop end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public byte[] getImage(FurnaceStatusVO vo) {
		byte[] byteImage=null;
		try {
			con = ConnectionModel.getLinuxServerConnection();
			ps = con.prepareStatement("select value from FNST_MY_IMAGE_BLB where _TOP_LEVEL_AURI='" + vo.getImageId() + "'");
			rs = ps.executeQuery();
			if (rs.next()) {
				byteImage=rs.getBytes("value");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return byteImage;
	}

}
