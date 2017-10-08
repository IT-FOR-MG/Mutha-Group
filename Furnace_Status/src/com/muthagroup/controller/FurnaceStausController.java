package com.muthagroup.controller;

import java.util.Base64;
import java.util.Calendar;
import com.muthagroup.dao.FurnaceStatusDAO;
import com.muthagroup.vo.FurnaceStatusVO;

public class FurnaceStausController {
public static void main(String args[]){
		 FurnaceStatusDAO dao=new FurnaceStatusDAO();
		 FurnaceStatusVO vo=new FurnaceStatusVO();
		 Calendar c = Calendar.getInstance();
		 int  year,month,day,hours_of_day,hour,minute,pm_am;
		 String date,time;	    
		 year = c.get(Calendar.YEAR);
	     month = c.get(Calendar.MONTH)+1;
		 day=c.get(Calendar.DAY_OF_MONTH);
	     hour=c.get(Calendar.HOUR);
	     hours_of_day=c.get(Calendar.HOUR_OF_DAY);
	     minute=c.get(Calendar.MINUTE);
	     pm_am=c.get(Calendar.AM_PM);
	     String pm_amString=null;
	     if(pm_am==0)
	     {
	    	 pm_amString="AM";
	     }
	     else {
			pm_amString="PM";
		}
	     date=String.valueOf(year)+"/"+String.format("%02d", month)+"/"+String.format("%02d", day);
		 time=String.format("%02d", hour)+":"+String.format("%02d", minute)+" "+pm_amString;
	     vo.setUserID(args[0]);
		 vo.setFurnaceId(args[1]);
		 vo.setImageId(args[2]);
		 vo.setDate(date);
		 vo.setTime(time);
		 vo.setShift(new FurnaceStausController().getShift(hours_of_day));
	     vo.setUser(dao.getUserName(vo));
	     vo.setByteImage(dao.getImage(vo));
	     vo.setStringImage(Base64.getEncoder().encodeToString(vo.getByteImage()));
	     dao.sendMail(vo);
	}

public String getShift(int hours_of_day) {
	  String shift=null;
	     if(hours_of_day>=8&&hours_of_day<4)
	     {
	    	 shift="First Shift";
	     }
	     if(hours_of_day>=4&&hours_of_day<23)
	     {
	    	 shift="Second Shift";
	     }
	     if(hours_of_day>=0&&hours_of_day<8)
	     {
	    	 shift="Third Shift";
	     }
		return shift;
		
	}
}
