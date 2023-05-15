package com.benito.dto;

import java.util.Date;

public class Latter {
	private String ln;
	private String id;
	private String ocode;
	private String ldate;
	private String llatter;
	private int  lstar;
	
	public Latter(){
		Date now = new Date();
		this.ldate = now.toString();
	}
	
	public String getLn() {
		return ln;
	}
	public void setLn(String ln) {
		this.ln = ln;
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
	public String getLdate() {
		return ldate;
	}
	public void setLdate(String ldate) {
		this.ldate = ldate;
	}
	public String getLlatter() {
		return llatter;
	}
	public void setLlatter(String llatter) {
		this.llatter = llatter;
	}
	public int getLstar() {
		return lstar;
	}
	public void setLstar(int lstar) {
		this.lstar = lstar;
	}
	@Override
	public String toString() {
		return "Latter [ln=" + ln + ", id=" + id + ", ocode=" + ocode
				+ ", ldate=" + ldate + ", llatter=" + llatter + ", lstar="
				+ lstar + "]";
	}
	
	
}
