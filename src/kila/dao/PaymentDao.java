package kila.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.PaymentVo;

public class PaymentDao {
	private static PaymentDao instance=new PaymentDao();
	private PaymentDao() {}
	public static PaymentDao getInstance() {
		return instance;
	}
	public int getProductnum(int colnum,int psize) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from product where colnum=? and psize=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,colnum);
			pstmt.setInt(2, psize);
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
			int n = pstmt.executeUpdate();
			JdbcUtil.close(pstmt);
			sql="update product set icnt=icnt-? where pnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCnt());
			pstmt.setInt(2, vo.getPnum());
			n += pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println("PaymentDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public ArrayList<PaymentVo> getInfo(String bid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<PaymentVo> list=new ArrayList<PaymentVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment where bid=? order by paydate desc";
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
					PaymentVo vo=new PaymentVo(paynum,bid,pnum,cnt,paydate,status,paymethod);
					list.add(vo);
				}
			}
			return list;
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
	public int cancelOrder(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();			
			String sql="update payment set status=status+10 where paynum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int returnOrder(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update payment set status=status-10 where paynum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int confirmOrder(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update payment set status=4 where paynum=? and status<=3";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}

	public ArrayList<PaymentVo> getRefundList(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<PaymentVo> list=new ArrayList<PaymentVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select * from(" + 
				    "   select aa.*,rownum rnum from(" + 
					"      select * from payment where status>=11 or status=7 order by paynum desc" + 
					"   ) aa" + 
					")where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int paynum=rs.getInt("paynum");
				String bid=rs.getString("bid");
				int pnum=rs.getInt("pnum");
				int cnt=rs.getInt("cnt");
				Date paydate=rs.getDate("paydate");
				int status=rs.getInt("status");
				String paymethod=rs.getString("paymethod");
				PaymentVo vo=new PaymentVo(paynum, bid, pnum, cnt, paydate, status, paymethod);
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
	
	public int confirmRefund(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update payment set status=7 where paynum=? and status>=11";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,paynum);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int getPrice(int pnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select price " + 
					   "from product_name " + 
					   "where pcode=(select pcode " + 
					   "             from color " + 
					   "             where colnum=(select colnum from product where pnum=?)" + 
					   "             )";
				
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,pnum);
			rs=pstmt.executeQuery();
			int price=0;
			if(rs.next()) {
				price=rs.getInt("price");
			}
			return price;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public ArrayList<PaymentVo> getAll(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<PaymentVo> list=new ArrayList<PaymentVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int paynum=rs.getInt("paynum");
				String bid=rs.getString("bid");
				int pnum=rs.getInt("pnum");
				int cnt=rs.getInt("cnt");
				Date paydate=rs.getDate("paydate");
				int status=rs.getInt("status");
				String paymethod=rs.getString("paymethod");
				if(status==4) {
					PaymentVo vo=new PaymentVo(paynum,bid,pnum,cnt,paydate,status,paymethod);
					list.add(vo);
				}
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public ArrayList<PaymentVo> getPeriodRev(String startdate,String enddate) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<PaymentVo> list=new ArrayList<PaymentVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select * from payment where paydate between to_date(?,'YYMMDD') and to_date(?,'YYMMDD')";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,startdate);
			pstmt.setString(2,enddate);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int paynum=rs.getInt("paynum");
				String bid=rs.getString("bid");
				int pnum=rs.getInt("pnum");
				int cnt=rs.getInt("cnt");
				Date paydate=rs.getDate("paydate");
				int status=rs.getInt("status");
				String paymethod=rs.getString("paymethod");
				if(status==4) {
					PaymentVo vo=new PaymentVo(paynum,bid,pnum,cnt,paydate,status,paymethod);
					list.add(vo);
				}
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public int getCnt(int pnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select cnt from payment where status=4 and pnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,pnum);
			rs=pstmt.executeQuery();
			int cntSum=0;
			while(rs.next()) {
				int cnt=rs.getInt("cnt");
				cntSum+=cnt;
			}
			return cntSum;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return 0;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int getRefundCnt() { //전체 환불요청건의 갯수 구하기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select nvl(count(*),0) cnt from payment where status=7 or status>=11";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt(1);
				return cnt;
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
