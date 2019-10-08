package kila.vo;

import java.sql.Date;

public class ProductRegVo {
	private int regnum;
	private int pnum;
	private int cnt;
	private Date regdate;
	
	public ProductRegVo() {}
	public ProductRegVo(int regnum, int pnum, int cnt, Date regdate) {
		this.regnum = regnum;
		this.pnum = pnum;
		this.cnt = cnt;
		this.regdate = regdate;
	}

	public int getRegnum() {
		return regnum;
	}

	public void setRegnum(int regnum) {
		this.regnum = regnum;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
