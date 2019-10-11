package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.ProductInfoVo;

public class ProductInfoDao {
	private static ProductInfoDao instance=new ProductInfoDao();
	private ProductInfoDao() {}
	public static ProductInfoDao getInstance() {
		return instance;
	}
	
	public ProductInfoVo productInfos(String pcode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_name p,color c,product d,product_reg r where p.pcode=c.pcode and d.colnum=c.colnum and r.pnum=d.pnum and pcode=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ProductInfoVo vo=new ProductInfoVo(rs.getString("pcode"),rs.getString("cname"), 
						rs.getString("pname"),rs.getInt("price"),rs.getString("color"), 
						rs.getString("psize"),rs.getInt("icnt"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:productInfos:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
