package kila.vo;

public class ProductNameVo {
	private String productname;
	private String cname;
	private String pname;
	private int price;
	
	public ProductNameVo() {}
	public ProductNameVo(String productname, String cname, String pname, int price) {
		this.productname = productname;
		this.cname = cname;
		this.pname = pname;
		this.price = price;
	}

	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
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
