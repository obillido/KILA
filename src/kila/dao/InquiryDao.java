package kila.dao;

import java.util.ArrayList;

import kila.vo.InquiryVo;

public class InquiryDao {
	private static InquiryDao instance=new InquiryDao();
	private InquiryDao() {}
	public static InquiryDao getInstance() {
		return instance;
	}
	
	public ArrayList<InquiryVo> getList(int colnum){
		return null;
	}
}
