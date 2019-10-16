package kila.vo;

import java.sql.Date;

public class MyPaymentVo {
	private int paynum;
	private String pname;
	private int cnt;
	private Date paydate;
	private String paymethod;
	private String status;
	public MyPaymentVo() {}
	public MyPaymentVo(int paynum, String pname, int cnt, Date paydate, String paymethod, String status) {
		super();
		this.paynum = paynum;
		this.pname = pname;
		this.cnt = cnt;
		this.paydate = paydate;
		this.paymethod = paymethod;
		this.status = status;
	}
	public int getPaynum() {
		return paynum;
	}
	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Date getPaydate() {
		return paydate;
	}
	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
