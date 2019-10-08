package kila.vo;

public class MemberVo {
	private String id;
	private String pwd;
	private String type;

	public MemberVo() {}
	public MemberVo(String id, String pwd, String type) {
		this.id = id;
		this.pwd = pwd;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
