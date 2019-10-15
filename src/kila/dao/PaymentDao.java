package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.BuyerVo;
import kila.vo.ColorVo;
import kila.vo.PaymentVo;

public class PaymentDao {
	private static PaymentDao instance=new PaymentDao();
	private PaymentDao() {}
	public static PaymentDao getInstance() {
		return instance;
	}
	public int getProductnum(int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from product where colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("pnum");
			}
			return -1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public int insert(PaymentVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into payment values(payment_seq.nextval,?,?,?,sysdate,1,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getBid());
			pstmt.setInt(2, vo.getPnum());
			pstmt.setInt(3, vo.getCnt());
			pstmt.setString(4, vo.getPaymethod());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("PaymentDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
}
