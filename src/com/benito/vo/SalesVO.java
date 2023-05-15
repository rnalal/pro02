package com.benito.vo;

public class SalesVO {
	private String ocode;
	private String id;
	private String pcode;
	private String pname;
	private String username;
	private int amount;
	private int price;
	private String odate;
	private String ostate;
	private String tel;
	private String dname;
	private String addr;
	private String dcode;
	private String yn;
	private String ypay;
	private String pnumber;
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
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
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
	@Override
	public String toString() {
		return "SalesVO [ocode=" + ocode + ", id=" + id + ", pcode=" + pcode
				+ ", pname=" + pname + ", username=" + username + ", amount="
				+ amount + ", price=" + price + ", odate=" + odate
				+ ", ostate=" + ostate + ", tel=" + tel + ", dname=" + dname
				+ ", addr=" + addr + ", dcode=" + dcode + ", yn=" + yn
				+ ", ypay=" + ypay + ", pnumber=" + pnumber + "]";
	}

}
