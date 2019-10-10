package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

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
}
