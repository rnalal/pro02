package com.benito.dto;

public class Basket {
	private String bn;
	private String id;
	private String pcode;
	private int amount;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Basket [bn=" + bn + ", id=" + id + ", pcode=" + pcode
				+ ", amount=" + amount + "]";
	}

}
