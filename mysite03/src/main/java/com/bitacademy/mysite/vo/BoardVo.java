package com.bitacademy.mysite.vo;

public class BoardVo {
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", contents=" + contents + ", writer=" + writer + ", g_no="
				+ g_no + ", depth=" + depth + ", date=" + this.reg_date + "]";
	}
	private long no;
	private String title;
	private String contents;
	private String writer;
	private long g_no;
	private int depth;
	private String reg_date;
	private long g_order;
	private long parent;
	final private String del="delContent123!@";
	public long getNo() {
		return no;
	}
	
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public long getG_no() {
		return g_no;
	}
	public void setG_no(long g_no) {
		this.g_no = g_no;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public long getG_order() {
		return g_order;
	}
	public void setG_order(long gorder) {
		this.g_order = gorder;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public String getDel() {
		return del;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	

}
