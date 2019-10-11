package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.BuyerVo;

public class BuyerDao {
	public int insert(BuyerVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into buyer values(?,?,?,?,'Welcome',1,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getCid());
			pstmt.setString(2,vo.getPhone());
			pstmt.setString(3,vo.getAddr());
			pstmt.setString(4,vo.getEmail());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int update(BuyerVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update buyer set phone=?,addr=?,email=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getPhone());
			pstmt.setString(2,vo.getAddr());
			pstmt.setString(3,vo.getEmail());
			pstmt.setString(4,vo.getCid());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public BuyerVo getInfo(String cid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from buyer where cid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,cid);
			rs=pstmt.executeQuery();
			BuyerVo vo=null;
			if(rs.next()) {
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				String email=rs.getString("email");
				vo=new BuyerVo(cid,phone,addr,email,null,0,0);
			}
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}