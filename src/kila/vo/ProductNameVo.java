package kila.vo;

public class ProductNameVo {
	private int pnnnum;
	private String cname;
	private String pname;
	private int price;
	
	public ProductNameVo() {}
	public ProductNameVo(int pnnnum, String cname, String pname, int price) {
		this.pnnnum = pnnnum;
		this.cname = cname;
		this.pname = pname;
		this.price = price;
	}

	public int getPnnnum() {
		return pnnnum;
	}

	public void setPnnnum(int pnnnum) {
		this.pnnnum = pnnnum;
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
