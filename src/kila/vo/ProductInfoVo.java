package kila.vo;

public class ProductInfoVo {
	private String pcode;
	private String cname;
	private String pname;
	private int price;
	private int colnum;
	private String color;
	private String savefilename;
	private String psize;
	private int icnt;
	private int soldout;
	
	public ProductInfoVo() {}

	public ProductInfoVo(String pcode, String cname, String pname, int price, int colnum, String color, String savefilename, int soldout) {
		this.pcode = pcode;
		this.cname = cname;
		this.pname = pname;
		this.price = price;
		this.colnum = colnum;
		this.color = color;
		this.savefilename = savefilename;
		this.soldout = soldout;
	}
	
	public ProductInfoVo(String pcode, String cname, String pname, int price, 
			String color, String savefilename, String psize, int icnt) {
		this.pcode = pcode;
		this.cname = cname;
		this.pname = pname;
		this.price = price;
		this.color = color;
		this.savefilename = savefilename;
		this.psize = psize;
		this.icnt = icnt;
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
	
	public int getColnum() {
		return colnum;
	}

	public void setColnum(int colnum) {
		this.colnum = colnum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public String getPsize() {
		return psize;
	}

	public void setPsize(String psize) {
		this.psize = psize;
	}

	public int getIcnt() {
		return icnt;
	}

	public void setIcnt(int icnt) {
		this.icnt = icnt;
	}

	public int getSoldout() {
		return soldout;
	}

	public void setSoldout(int soldout) {
		this.soldout = soldout;
	}
	
}
