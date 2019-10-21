package kila.vo;

public class FinalSearchVo {
	private String pname;
	private String savefilename;
	private int colnum;
	public FinalSearchVo() {}
	public FinalSearchVo(String pname, String savefilename, int colnum) {
		super();
		this.pname = pname;
		this.savefilename = savefilename;
		this.colnum = colnum;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	public int getColnum() {
		return colnum;
	}
	public void setColnum(int colnum) {
		this.colnum = colnum;
	}
}
