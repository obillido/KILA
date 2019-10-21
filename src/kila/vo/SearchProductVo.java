package kila.vo;

public class SearchProductVo {
	private String pcode;
	private String pname;
	private int colnum;
	public SearchProductVo() {}
	public SearchProductVo(String pcode, String pname, int colnum) {
		super();
		this.pcode = pcode;
		this.pname = pname;
		this.colnum = colnum;
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
	public int getColnum() {
		return colnum;
	}
	public void setColnum(int colnum) {
		this.colnum = colnum;
	}	
}
