package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.ProductRegVo;

public class ProductRegDao {
	private static ProductRegDao instance=new ProductRegDao();
	private ProductRegDao() {}
	public static ProductRegDao getInstance() {
		return instance;
	}
	
	public int insert(ProductRegVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into product_reg values(pr_seq.nextval,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPnum());
			pstmt.setInt(2, vo.getCnt());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}
