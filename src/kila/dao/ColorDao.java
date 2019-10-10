package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.ColorVo;

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
				return 1;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}
