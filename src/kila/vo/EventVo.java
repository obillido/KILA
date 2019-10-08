package kila.vo;

public class EventVo {
	private int evnum;
	private String title;
	private String content;
	private String orgfilename;
	private String savefilename;
	private long filesize;
	
	public EventVo() {}
	public EventVo(int evnum, String title, String content, String orgfilename, String savefilename, long filesize) {
		this.evnum = evnum;
		this.title = title;
		this.content = content;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.filesize = filesize;
	}

	public int getEvnum() {
		return evnum;
	}

	public void setEvnum(int evnum) {
		this.evnum = evnum;
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
