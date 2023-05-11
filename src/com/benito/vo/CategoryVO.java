package com.benito.vo;

public class CategoryVO {
	private String ct;
	private String catagroup;
	private String cate;
	private String catename;
	private String pcode;
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getCatagroup() {
		return catagroup;
	}
	public void setCatagroup(String catagroup) {
		this.catagroup = catagroup;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	@Override
	public String toString() {
		return "CatagoryVO [ct=" + ct + ", catagroup=" + catagroup + ", cate="
				+ cate + ", catename=" + catename + ", pcode=" + pcode + "]";
	}
	
}
