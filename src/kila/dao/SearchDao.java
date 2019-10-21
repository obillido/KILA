package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.SearchProductVo;

public class SearchDao {
	public ArrayList<SearchProductVo> searchProduct(String search) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select p.pcode,p.pname,c.colnum " + 
					   "from product_name p,color c " + 
					   "where p.pcode=c.pcode " + 
					   "      and p.pname like '%" + search + "%'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<SearchProductVo> list=new ArrayList<SearchProductVo>();
			while(rs.next()) {
				String pcode=rs.getString("pcode");
				String pname=rs.getString("pname");
				int colnum=rs.getInt("colnum");
				SearchProductVo vo=new SearchProductVo(pcode,pname,colnum);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public String getImage(int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select savefilename from color where colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,colnum);
			rs=pstmt.executeQuery();
			String savefilename=null;
			if(rs.next()) {
				savefilename=rs.getString("savefilename");
			}
			return savefilename;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
