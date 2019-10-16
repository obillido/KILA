package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ColorVo;
import kila.vo.ProductInfoVo;

public class ColorDao {
	private static ColorDao instance=new ColorDao();
	private ColorDao() {}
	public static ColorDao getInstance() {
		return instance;
	}
	
	public int isExist(String pcode, String color) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from color where pcode=? and color=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			pstmt.setString(2, color);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("colnum");
			}
			return 0;
		}catch(SQLException se) {
			System.out.println("ColorDAO:isExist"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int insert(ColorVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into color values(color_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getPcode());
			pstmt.setString(2, vo.getColor());
			pstmt.setString(3, vo.getOrgfilename());
			pstmt.setString(4, vo.getSavefilename());
			pstmt.setLong(5, vo.getFilesize());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("ColorDAO:insert"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
	public String[] getProductInfo(int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select pname, color from color, product_name pn "
					+ "where color.pcode=pn.pcode and colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String[] str= {rs.getString("color"),rs.getString("pname")};
				return str;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println("ColorDAO:getProductInfo"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}

}
