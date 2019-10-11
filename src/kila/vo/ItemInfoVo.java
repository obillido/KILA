package kila.vo;

public class ItemInfoVo {
	private String pcode;
	private String pname;
	private int price;
	private String savefilename;
	
	public ItemInfoVo() {};
	
	public ItemInfoVo(String pcode, String pname, int price, String savefilename) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.price = price;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	
	
}
