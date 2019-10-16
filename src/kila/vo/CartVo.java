package kila.vo;

public class CartVo {
	private int paynum;
	private String savefilename;
	private String pcode;
	private String pname;
	private String color;
	private String psize;
	private int cnt;
	private int price;
	public CartVo() {}
	public CartVo(int paynum, String savefilename, String pcode, String pname, String color, String psize, int cnt,
			int price) {
		super();
		this.paynum = paynum;
		this.savefilename = savefilename;
		this.pcode = pcode;
		this.pname = pname;
		this.color = color;
		this.psize = psize;
		this.cnt = cnt;
		this.price = price;
	}
	public int getPaynum() {
		return paynum;
	}
	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPsize() {
		return psize;
	}
	public void setPsize(String psize) {
		this.psize = psize;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
