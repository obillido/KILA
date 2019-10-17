package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.CartVo;

public class CartDao {
	private static CartDao instance=new CartDao();
	private CartDao() {}
	public static CartDao getInstance() {
		return instance;
	}
	public ArrayList<CartVo> list(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment pm,product pd,color c,product_name pn where pm.pnum=pd.pnum and pd.colnum=c.colnum and c.pcode=pn.pcode and bid=? and pm.status=8";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ArrayList<CartVo> list=new ArrayList<CartVo>(); 
			while(rs.next()) {
				list.add(new CartVo(rs.getInt("paynum"),rs.getString("savefilename"),rs.getString("pcode"), rs.getString("pname"), rs.getString("color"), rs.getString("psize"), rs.getInt("cnt"), rs.getInt("price")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int delete(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="delete from payment where paynum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public CartVo list2(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment pm,product pd,color c,product_name pn where pm.pnum=pd.pnum and pd.colnum=c.colnum and c.pcode=pn.pcode and paynum=? and status=8";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, paynum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return new CartVo(rs.getInt("paynum"),rs.getString("savefilename"),rs.getString("pcode"), rs.getString("pname"), rs.getString("color"), rs.getString("psize"), rs.getInt("cnt"), rs.getInt("price"));
			}
			return null;
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int cpayment(int paynum,String paymethod) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update payment set status=1,paymethod=? where paynum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, paymethod);
			pstmt.setInt(2, paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public int cpayment2(String[] paynum,String paymethod) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			if(paynum==null) {
				return -1;
			}else {
				sql="update payment set status=1,paymethod=? where paynum=" + paynum[0];
				if(paynum.length>1) {
					for(int i=1;i<paynum.length;i++)
					sql += " or paynum=" + paynum[i];
				}
			}
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, paymethod);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public int delete2(String[] paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			if(paynum==null) {
				return -1;
			}else {
				sql="delete from payment where paynum=" + paynum[0];
				if(paynum.length>1) {
					for(int i=1;i<paynum.length;i++)
					sql += " or paynum=" + paynum[i];
				}
			}
			pstmt=con.prepareStatement(sql);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public ArrayList<CartVo> list3(String[] paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			sql="select * from (select pm.paynum,c.savefilename,c.pcode,pn.pname,c.color,pd.psize,pm.cnt,pn.price from payment pm,product pd,color c,product_name pn where pm.pnum=pd.pnum and pd.colnum=c.colnum and c.pcode=pn.pcode and status=8) where paynum=" + paynum[0];
			if(paynum.length>1) {
				for(int i=1;i<paynum.length;i++)
				sql += " or paynum=" + paynum[i];
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<CartVo> list=new ArrayList<CartVo>();
			while(rs.next()) {
				list.add(new CartVo(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8)));
			}
			for(CartVo vo:list) {
				System.out.println(vo.getColor());
			}
			return list;
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int totprice(String[] paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int tot=0;
		try {
			con=JdbcUtil.getConn();
			String sql="";
			sql="select * from (select pm.paynum,pm.cnt,pn.price from payment pm,product pd,color c,product_name pn where pm.pnum=pd.pnum and pd.colnum=c.colnum and c.pcode=pn.pcode and status=8) where paynum=" + paynum[0];
			if(paynum.length>1) {
				for(int i=1;i<paynum.length;i++)
				sql += " or paynum=" + paynum[i];
			}
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<CartVo> list=new ArrayList<CartVo>();
			while(rs.next()) {
				tot+=rs.getInt(2)*rs.getInt(3);
			}
			return tot;
		}catch(SQLException se) {
			System.out.println("CartDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
}
