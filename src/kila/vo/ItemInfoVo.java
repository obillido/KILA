package kila.vo;

public class ItemInfoVo {
	private String pcode;
	private String pname;
	private int price;
	private String savefilename;
	private String color;
	
	public ItemInfoVo() {}

	public ItemInfoVo(String pcode, String pname, int price, String savefilename, String color) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.price = price;
		this.savefilename = savefilename;
		this.color = color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	};
}
