package com.benito.vo;

public class BasketVO {
	private String bn;
	private String id;
	private String name;
	private String pcode;
	private String pname;
	private int bcount;
	private int price;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "BasketVO [bn=" + bn + ", id=" + id + ", name=" + name
				+ ", pcode=" + pcode + ", pname=" + pname + ", bcount="
				+ bcount + ", price=" + price + "]";
	}
}
