package kila.vo;

import java.sql.Date;

public class PaymentVo {
	private int paynum;
	private String bid;
	private int pnum;
	private int cnt;
	private Date paydate;
	private int status;
	private String paymethod;
	
	public PaymentVo() {}
	public PaymentVo(int paynum, String bid, int pnum, int cnt, Date paydate, int status, String paymethod) {
		this.paynum = paynum;
		this.bid = bid;
		this.pnum = pnum;
		this.cnt = cnt;
		this.paydate = paydate;
		this.status = status;
		this.paymethod = paymethod;
	}

	public int getPaynum() {
		return paynum;
	}

	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
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

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public String getPaymethod() {
		return paymethod;
	}
	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}
	
	
}
