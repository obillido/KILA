package kila.vo;

public class ItemInfoSizeVo {
	private int psize;
	private int icnt;
	
	public ItemInfoSizeVo() {}

	public ItemInfoSizeVo(int psize, int icnt) {
		super();
		this.psize = psize;
		this.icnt = icnt;
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
