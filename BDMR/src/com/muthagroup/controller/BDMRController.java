package com.muthagroup.controller;

import com.muthagroup.dao.BDMRDAO;
import com.muthagroup.vo.BDMRVO;

public class BDMRController {
public static void main(String args[]){
	BDMRVO vo=new BDMRVO();
	BDMRDAO dao=new BDMRDAO();
	vo.setMachine_type(args[0]); 
	vo.setMachine_name(args[1]);
	vo.setMachine_desc(args[2]);
	dao.insertIntoERP(vo);
}
}
