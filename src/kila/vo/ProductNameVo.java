package kila.vo;

public class ProductNameVo {
	private String pcode;
	private String cname;
	private String pname;
	private int price;
	
	public ProductNameVo() {}
	public ProductNameVo(String pcode, String cname, String pname, int price) {
		this.pcode = pcode;
		this.cname = cname;
		this.pname = pname;
		this.price = price;
	}

	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
