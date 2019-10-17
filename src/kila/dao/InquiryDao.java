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
	
	public ArrayList<InquiryVo> getList(int colnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select inum, rpad(substr(id,0,4),length(id),'*') pid, colnum, inqtype, title, content, ref, regdate  from inquiry where colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			ArrayList<InquiryVo> list=new ArrayList<InquiryVo>();
			while(rs.next()) {
				list.add(new InquiryVo(
						rs.getInt("inum"), 
						rs.getString("pid"), 
						rs.getInt("colnum"), 
						rs.getInt("inqtype"), 
						rs.getString("title"), 
						rs.getString("content"), 
						rs.getInt("ref"), 
						rs.getDate("regdate")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	
	public int getMaxRef() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select max(ref) maxRef from inquiry";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("maxRef");
			}else {
				return 0;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
			String sql="insert into inquiry values(inquiry_seq.nextval,?,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setInt(2, vo.getColnum());
			pstmt.setInt(3, vo.getInqtype());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			int ref=vo.getRef();
			if(ref==1) ref=getMaxRef();
			pstmt.setInt(6, ref);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}
