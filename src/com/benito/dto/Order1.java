package com.benito.dto;

import java.util.Date;

public class Order1 {
	private String ocode;
	private String id;
	private String pcode;
	private int amount = 1;
	private String oprice;
	private String odate;
	private String ostate="배송중";
	private String tel;
	private String dname;
	private String addr;
	private String dcode;
	
	public Order1(){
		Date now = new Date();
		this.odate = now.toString();
	}

	public String getOcode() {
		return ocode;
	}

	public void setOcode(String ocode) {
		this.ocode = ocode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getOprice() {
		return oprice;
	}

	public void setOprice(String oprice) {
		this.oprice = oprice;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public String getOstate() {
		return ostate;
	}

	public void setOstate(String ostate) {
		this.ostate = ostate;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	@Override
	public String toString() {
		return "Order1 [ocode=" + ocode + ", id=" + id + ", pcode=" + pcode
				+ ", amount=" + amount + ", oprice=" + oprice + ", odate="
				+ odate + ", ostate=" + ostate + ", tel=" + tel + ", dname="
				+ dname + ", addr=" + addr + ", dcode=" + dcode + "]";
	}
	
	
}

