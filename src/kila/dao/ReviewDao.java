package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ReviewIndexVo;

public class ReviewDao {
	private static ReviewDao instance=new ReviewDao();
	private ReviewDao() {}
	public static ReviewDao getInstance() {
		return instance;
	}
	public ArrayList<ReviewIndexVo> getIndex(String id,int colnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select c.color,pd.psize from payment pm,product pd,color c where pm.pnum=pd.pnum and pd.colnum=c.colnum and bid=? and status=4 and c.colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, colnum);
			ArrayList<ReviewIndexVo> list=new ArrayList<ReviewIndexVo>();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getInt(2));
				list.add(new ReviewIndexVo(rs.getString(1), rs.getInt(2)));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductRegDAO:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public boolean check(String id,int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment pm,product pd where pm.pnum=pd.pnum and bid=? and status=4 and colnum=?;";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(SQLException se) {
			System.out.println("ProductRegDAO:"+se.getMessage());
			return false;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}