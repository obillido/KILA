package kila.vo;

import java.sql.Date;

public class ReviewVo {
	private int rnum;
	private int paynum;
	private String content;
	private int rpoint;
	private Date regdate;
	private String savefilename;
	
	public ReviewVo() {}
	public ReviewVo(int rnum, int paynum, String content, int rpoint, Date regdate, String savefilename) {
		this.rnum = rnum;
		this.paynum = paynum;
		this.content = content;
		this.rpoint = rpoint;
		this.regdate = regdate;
		this.savefilename = savefilename;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getPaynum() {
		return paynum;
	}

	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRpoint() {
		return rpoint;
	}

	public void setRpoint(int rpoint) {
		this.rpoint = rpoint;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	
	
}
