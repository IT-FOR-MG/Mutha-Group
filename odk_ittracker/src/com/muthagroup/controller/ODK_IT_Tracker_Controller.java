package com.muthagroup.controller;

import com.muthagroup.dao.ODK_IT_Tracker_DAO;
import com.muthagroup.vo.ODK_IT_Tracker_VO;

public class ODK_IT_Tracker_Controller {
	
	
	public static void main(String args[]){
		
		ODK_IT_Tracker_VO vo=new ODK_IT_Tracker_VO();
		ODK_IT_Tracker_DAO dao=new ODK_IT_Tracker_DAO();
		dao.getMaxReqId(vo);
		vo.setUid(Integer.parseInt(args[0]));
		vo.setRel_id(Integer.parseInt(args[1]));
		vo.setReq_type_id(Integer.parseInt(args[2]));
		vo.setReq_details(args[3]);
		dao.addReq(vo);
	}
}
