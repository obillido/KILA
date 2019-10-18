package kila.vo;

public class ReviewIndexVo {
	private String color;
	private int size;
	public ReviewIndexVo() {}
	public ReviewIndexVo(String color, int size) {
		super();
		this.color = color;
		this.size = size;
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
	
}
