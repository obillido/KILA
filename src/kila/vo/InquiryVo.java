package kila.vo;

import java.sql.Date;

public class InquiryVo {
	private int inum;
	private String id;
	private int colnum;
	private int inqtype;
	private String title;
	private String content;
	private int ref;
	private Date regdate;
	
	public InquiryVo() {}
	public InquiryVo(int inum, String id, int colnum, int inqtype, String title, String content, int ref, Date regdate) {
		this.inum = inum;
		this.id = id;
		this.colnum = colnum;
		this.inqtype = inqtype;
		this.title = title;
		this.content = content;
		this.ref = ref;
		this.regdate = regdate;
	}

	public int getInum() {
		return inum;
	}

	public void setInum(int inum) {
		this.inum = inum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getColnum() {
		return colnum;
	}

	public void setColnum(int colnum) {
		this.colnum = colnum;
	}
	
	public int getInqtype() {
		return inqtype;
	}
	public void setInqtype(int inqtype) {
		this.inqtype = inqtype;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
