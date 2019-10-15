package kila.vo;

public class BuyerVo {
	private String cid;
	private String phone;
	private String addr;
	private String email;
	private String rank;
	private int status;
	private int coin;
	private String bname;
	
	public BuyerVo() {}
	public BuyerVo(String cid, String phone, String addr, String email, String rank, int status, int coin, String bname) {
		this.cid = cid;
		this.phone = phone;
		this.addr = addr;
		this.email = email;
		this.rank = rank;
		this.status = status;
		this.coin = coin;
		this.bname = bname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	
}
