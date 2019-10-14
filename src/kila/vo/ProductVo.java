package kila.vo;

public class ProductVo {
	private int pnum;
	private int colnum;
	private int psize;
	private int icnt;
	
	public ProductVo() {}
	public ProductVo(int pnum, int colnum, int psize, int icnt) {
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

	public int getPsize() {
		return psize;
	}

	public void setPsize(int psize) {
		this.psize = psize;
	}

	public int getIcnt() {
		return icnt;
	}

	public void setIcnt(int icnt) {
		this.icnt = icnt;
	}
	
}
