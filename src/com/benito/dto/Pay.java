package com.benito.dto;

import java.util.Date;

public class Pay {
	private String yn;
	private String id;
	private String ocode;
	private String ypay;
	private String pnumber;
	private String yprice;
	private String ydate;
	
	public Pay(){
		Date now = new Date();
		this.ydate = now.toString();
	}
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOcode() {
		return ocode;
	}
	public void setOcode(String ocode) {
		this.ocode = ocode;
	}
	public String getYpay() {
		return ypay;
	}
	public void setYpay(String ypay) {
		this.ypay = ypay;
	}
	public String getPnumber() {
		return pnumber;
	}
	public void setPnumber(String pnumber) {
		this.pnumber = pnumber;
	}
	public String getYprice() {
		return yprice;
	}
	public void setYprice(String yprice) {
		this.yprice = yprice;
	}
	public String getYdate() {
		return ydate;
	}
	public void setYdate(String ydate) {
		this.ydate = ydate;
	}
	@Override
	public String toString() {
		return "Pay [yn=" + yn + ", id=" + id + ", ocode=" + ocode + ", ypay="
				+ ypay + ", pnumber=" + pnumber + ", yprice=" + yprice
				+ ", ydate=" + ydate + "]";
	}

}
