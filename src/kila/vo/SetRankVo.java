package kila.vo;

public class SetRankVo {
	private String bid;
	private int pnum;
	private int cnt;
	public SetRankVo() {}
	public SetRankVo(String bid, int pnum, int cnt) {
		super();
		this.bid = bid;
		this.pnum = pnum;
		this.cnt = cnt;
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
}
