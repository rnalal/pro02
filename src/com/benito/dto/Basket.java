package com.benito.dto;

public class Basket {
	private String bn;
	private String id;
	private String pcode;
	private int bcount;
	public String getBn() {
		return bn;
	}
	public void setBn(String bn) {
		this.bn = bn;
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
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	@Override
	public String toString() {
		return "Basket [bn=" + bn + ", id=" + id + ", pcode=" + pcode
				+ ", bcount=" + bcount + "]";
	}
	
	
}
