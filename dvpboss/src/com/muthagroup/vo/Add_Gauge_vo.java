package com.muthagroup.vo;

import java.sql.Date;

public class Add_Gauge_vo 
{
	String gauge_name=null;
	String cust_apr=null;
	
	int gauge_qty=0;
	int basic_id=0;
	int rel_id=0;
	int avail_qty=0;
			
	Date gauge_po_date=null;
	Date gauge_rec_date=null;
	Date gauge_trg_date=null;
	
	
	
	public int getAvail_qty() {
		return avail_qty;
	}
	public void setAvail_qty(int avail_qty) {
		this.avail_qty = avail_qty;
	}
	public String getGauge_name() {
		return gauge_name;
	}
	public void setGauge_name(String gauge_name) {
		this.gauge_name = gauge_name;
	}
	public String getCust_apr() {
		return cust_apr;
	}
	public void setCust_apr(String cust_apr) {
		this.cust_apr = cust_apr;
	}
	public int getGauge_qty() {
		return gauge_qty;
	}
	public void setGauge_qty(int gauge_qty) {
		this.gauge_qty = gauge_qty;
	}
	public int getBasic_id() {
		return basic_id;
	}
	public void setBasic_id(int basic_id) {
		this.basic_id = basic_id;
	}
	public int getRel_id() {
		return rel_id;
	}
	public void setRel_id(int rel_id) {
		this.rel_id = rel_id;
	}
	public Date getGauge_po_date() {
		return gauge_po_date;
	}
	public void setGauge_po_date(Date gauge_po_date) {
		this.gauge_po_date = gauge_po_date;
	}
	public Date getGauge_rec_date() {
		return gauge_rec_date;
	}
	public void setGauge_rec_date(Date gauge_rec_date) {
		this.gauge_rec_date = gauge_rec_date;
	}
	public Date getGauge_trg_date() {
		return gauge_trg_date;
	}
	public void setGauge_trg_date(Date gauge_trg_date) {
		this.gauge_trg_date = gauge_trg_date;
	}
	
	
}
