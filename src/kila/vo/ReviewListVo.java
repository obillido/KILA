package kila.vo;

import java.sql.Date;

public class ReviewListVo {
	private int rpoint;
	private String content;
	private String savefilename;
	private String id;
	private Date regdate;
	private String color;
	private int size;
	public ReviewListVo() {}
	public ReviewListVo(int rpoint, String content, String savefilename, String id, Date regdate, String color,
			int size) {
		super();
		this.rpoint = rpoint;
		this.content = content;
		this.savefilename = savefilename;
		this.id = id;
		this.regdate = regdate;
		this.color = color;
		this.size = size;
	}
	public int getRpoint() {
		return rpoint;
	}
	public void setRpoint(int rpoint) {
		this.rpoint = rpoint;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSavefilename() {
		return savefilename;
	}
	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
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
