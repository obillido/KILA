package kila.vo;

public class ProductVo {
	private int pnum;
	private int colnum;
	private String psize;
	private int icnt;
	
	public ProductVo() {}
	public ProductVo(int pnum, int colnum, String psize, int icnt) {
		this.pnum = pnum;
		this.colnum = colnum;
		this.psize = psize;
		this.icnt = icnt;
	}
	
	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getColnum() {
		return colnum;
	}

	public void setColnum(int colnum) {
		this.colnum = colnum;
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
	
}
