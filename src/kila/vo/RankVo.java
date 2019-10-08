package kila.vo;

public class RankVo {
	private String rank;
	private int minamount;
	private int maxamount;
	
	public RankVo() {}
	public RankVo(String rank, int minamount, int maxamount) {
		this.rank = rank;
		this.minamount = minamount;
		this.maxamount = maxamount;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getMinamount() {
		return minamount;
	}

	public void setMinamount(int minamount) {
		this.minamount = minamount;
	}

	public int getMaxamount() {
		return maxamount;
	}

	public void setMaxamount(int maxamount) {
		this.maxamount = maxamount;
	}
	
}
