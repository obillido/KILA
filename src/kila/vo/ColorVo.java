package kila.vo;

public class ColorVo {
	private int colnum;
	private int pnnum;
	private String color;
	private String orgfilename;
	private String savefilename;
	private long filesize;
	
	public ColorVo() {}
	public ColorVo(int colnum, int pnnum, String color, String orgfilename, String savefilename, long filesize) {
		this.colnum = colnum;
		this.pnnum = pnnum;
		this.color = color;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.filesize = filesize;
	}

	public int getColnum() {
		return colnum;
	}

	public void setColnum(int colnum) {
		this.colnum = colnum;
	}

	public int getPnnum() {
		return pnnum;
	}

	public void setPnnum(int pnnum) {
		this.pnnum = pnnum;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	
}
