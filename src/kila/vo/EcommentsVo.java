package kila.vo;

public class EcommentsVo {
	private int ecnum;
	private int evnum;
	private String ecid;
	private String ecomments;
	public EcommentsVo() {}
	public EcommentsVo(int ecnum, int evnum, String ecid, String ecomments) {
		super();
		this.ecnum = ecnum;
		this.evnum = evnum;
		this.ecid = ecid;
		this.ecomments = ecomments;
	}
	public int getEcnum() {
		return ecnum;
	}
	public void setEcnum(int ecnum) {
		this.ecnum = ecnum;
	}
	public int getEvnum() {
		return evnum;
	}
	public void setEvnum(int evnum) {
		this.evnum = evnum;
	}
	public String getEcid() {
		return ecid;
	}
	public void setEcid(String ecid) {
		this.ecid = ecid;
	}
	public String getEcomments() {
		return ecomments;
	}
	public void setEcomments(String ecomments) {
		this.ecomments = ecomments;
	}
}
