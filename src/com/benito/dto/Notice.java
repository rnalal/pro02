package com.benito.dto;

public class Notice {
	private int n_no;
	private String title;
	private String content;
	private String author;
	private String file1;
	private String ndate;
	private int readno;
	
	public int getN_no() {return n_no;}
	public void setN_no(int n_no) {this.n_no = n_no;}
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title;}
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
	public String getFile1() {return file1;}
	public void setFile1(String file1) {this.file1 = file1;}
	public String getNdate() {return ndate;}
	public void setNdate(String ndate) {this.ndate = ndate;}
	public int getReadno() {return readno;}
	public void setReadno(int readno) {this.readno = readno;}
	@Override
	public String toString() {
		return "Notice [n_no=" + n_no + ", title=" + title + ", content="
				+ content + ", author=" + author + ", file1=" + file1
				+ ", ndate=" + ndate + ", readno=" + readno + "]";
	}	
}
