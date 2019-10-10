package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.ProductNameVo;

public class ProductNameDao {
	private static ProductNameDao instance=new ProductNameDao();
	private ProductNameDao() {}
	public static ProductNameDao getInstance() {
		return instance;
	}
	
	public int isExist(String pcode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from productname where pcode=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int insert(ProductNameVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into productname values(?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPcode());
			pstmt.setString(2, vo.getCname());
			pstmt.setString(3, vo.getPname());
			pstmt.setInt(4, vo.getPrice());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}
