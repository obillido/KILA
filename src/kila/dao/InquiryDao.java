package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.InquiryVo;

public class InquiryDao {
	private static InquiryDao instance=new InquiryDao();
	private InquiryDao() {}
	public static InquiryDao getInstance() {
		return instance;
	}
	
	public ArrayList<InquiryVo> getList(int colnum, int at, int it){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			
			String sql="select inquiry.inum, ml, rpad(substr(id,0,4),length(id),'*') pid, colnum, inqtype, title, content, regdate " 
					+ "from inquiry, (select inum, max(lev) ml from inquiry group by inum) ii "
					+ "where colnum=? and inquiry.inum=ii.inum and inquiry.lev=1 "
					+ "order by inum desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			ArrayList<InquiryVo> list=new ArrayList<InquiryVo>();
			while(rs.next()) {
				list.add(new InquiryVo(
						rs.getInt("inum"), 
						rs.getInt("ml"),
						rs.getString("pid"), 
						rs.getInt("colnum"), 
						rs.getInt("inqtype"), 
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getDate("regdate")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("InquiryDao:getList:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	
	public int getMaxInum() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select max(inum) maxInum from inquiry";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxInum");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println("InquiryDao:getMaxInum:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int insert(InquiryVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into inquiry values(?,?,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getInum());
			pstmt.setInt(2, vo.getLev());
			pstmt.setString(3, vo.getId());
			pstmt.setInt(4, vo.getColnum());
			pstmt.setInt(5, vo.getInqtype());
			pstmt.setString(6, vo.getTitle());
			pstmt.setString(7, vo.getContent());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("InquiryDao:insert:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
	public ArrayList<InquiryVo> getInfo(int inum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=JdbcUtil.getConn();
			String sql="select * from inquiry where inum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,inum);
			rs=pstmt.executeQuery();
			ArrayList<InquiryVo> list=new ArrayList<InquiryVo>();
			while(rs.next()){
				list.add(new InquiryVo(
						inum, 
						rs.getInt("lev"), 
						rs.getString("id"), 
						rs.getInt("colnum"), 
						rs.getInt("inqtype"), 
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getDate("regdate")));
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
}
