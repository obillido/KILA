package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import jdbc.JdbcUtil;
import kila.vo.CartVo;
import kila.vo.ColorVo;
import kila.vo.PaymentVo;

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
}
