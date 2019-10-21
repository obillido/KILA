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
	
	public ArrayList<InquiryVo> getList(int startRowInq, int endRowInq, int colnum, int at, int it, String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String awhere="", iwhere="";
			switch(at) {
			case 1: awhere=" and ml=1 "; break;
			case 2: awhere=" and ml=2 "; break;
			default: iwhere="";
			}
			switch(it) {
			case 1: iwhere=" and inqtype=1 "; break;
			case 2: iwhere=" and inqtype=2 "; break;
			case 3: iwhere=" and inqtype=3 "; break;
			case 4: iwhere=" and inqtype=4 "; break;
			default: iwhere="";
			}
			String idwhere="";
			if(id!=null && !id.equals("")) {
				idwhere=" and id='"+id+"' ";
			}
			
			String sql="select * from (select aa.*, rownum rnum from ("
					+ "select inquiry.inum, ml, rpad(substr(id,0,4),length(id),'*') pid,id, colnum, inqtype, title, content, regdate " 
					+ "from inquiry, (select inum, max(lev) ml from inquiry group by inum) ii "
					+ "where colnum=? and inquiry.inum=ii.inum and inquiry.lev=1 "+awhere+iwhere+idwhere
					+ "order by inum desc"
					+ ") aa) where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			pstmt.setInt(2, startRowInq);
			pstmt.setInt(3, endRowInq);
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
			System.out.println("InquiryDao:getInfo:"+se.getMessage());
			return null;
		}finally{
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	
	
	public int getCount(int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select count(*) cnt " 
					+ "from inquiry, (select inum, max(lev) ml from inquiry group by inum) ii "
					+ "where colnum=? and inquiry.inum=ii.inum and inquiry.lev=1 ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println("InquiryDao:getCount:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
