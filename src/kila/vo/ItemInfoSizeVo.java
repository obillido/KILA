package kila.vo;

public class ItemInfoSizeVo {
	private String psize;
	private int icnt;
	
	public ItemInfoSizeVo() {}
	public ItemInfoSizeVo(String psize, int icnt) {
		super();
		this.psize = psize;
		this.icnt = icnt;
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
