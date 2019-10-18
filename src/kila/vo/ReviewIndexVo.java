package kila.vo;

public class ReviewIndexVo {
	private String color;
	private int size;
	private int paynum;
	public ReviewIndexVo() {}
	public ReviewIndexVo(String color, int size, int paynum) {
		super();
		this.color = color;
		this.size = size;
		this.paynum = paynum;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPaynum() {
		return paynum;
	}
	public void setPaynum(int paynum) {
		this.paynum = paynum;
	}
}
