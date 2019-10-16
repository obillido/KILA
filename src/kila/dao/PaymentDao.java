package kila.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.BuyerVo;
import kila.vo.ColorVo;
import kila.vo.MyPaymentVo;
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
			String sql="insert into payment values(payment_seq.nextval,?,?,?,sysdate,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getBid());
			pstmt.setInt(2, vo.getPnum());
			pstmt.setInt(3, vo.getCnt());
			pstmt.setInt(4, vo.getStatus());
			pstmt.setString(5, vo.getPaymethod());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("PaymentDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public PaymentVo getInfo(String bid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		PaymentVo vo=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment where bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,bid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int paynum=rs.getInt("paynum");
				int pnum=rs.getInt("pnum");
				int cnt=rs.getInt("cnt");
				Date paydate=rs.getDate("paydate");
				int status=rs.getInt("status");
				String paymethod=rs.getString("paymethod");
				if(status!=8) {
					vo=new PaymentVo(paynum,bid,pnum,cnt,paydate,status,paymethod);
				}
			}
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public String getPname(int pnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select pname " + 
					   "from product_name " + 
					   "where pcode=(select pcode " + 
					   "             from color " + 
					   "             where colnum=(select colnum " + 
					   "                           from product " + 
					   "                           where pnum=?)" + 
					   "             )";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,pnum);
			rs=pstmt.executeQuery();
			String pname=null;
			if(rs.next()) {
				pname=rs.getString("pname");
			}
			return pname;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
