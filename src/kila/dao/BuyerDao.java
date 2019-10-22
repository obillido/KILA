package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.BuyerVo;

public class BuyerDao {
	public int insert(BuyerVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into buyer values(?,?,?,?,'Welcome',1,0,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getCid());
			pstmt.setString(2,vo.getPhone());
			pstmt.setString(3,vo.getAddr());
			pstmt.setString(4,vo.getEmail());
			pstmt.setString(5,vo.getBname());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public int update(BuyerVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update buyer set phone=?,addr=?,email=?,bname=? where cid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getPhone());
			pstmt.setString(2,vo.getAddr());
			pstmt.setString(3,vo.getEmail());
			pstmt.setString(4, vo.getBname());
			pstmt.setString(5,vo.getCid());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public BuyerVo getInfo(String cid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from buyer where cid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,cid);
			rs=pstmt.executeQuery();
			BuyerVo vo=null;
			if(rs.next()) {
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				String email=rs.getString("email");
				String bname=rs.getString("bname");
				vo=new BuyerVo(cid,phone,addr,email,null,0,0,bname);
			}
			return vo;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<BuyerVo> getAllMembers(int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BuyerVo> list=new ArrayList<BuyerVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select * from(" + 
				    "   select aa.*,rownum rnum from(" + 
					"      select * from buyer" + 
					"   ) aa" + 
					")where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				String cid=rs.getString("cid");
				String phone=rs.getString("phone");
				String addr=rs.getString("addr");
				String email=rs.getString("email");
				String rank=rs.getString("rank");
				int status=rs.getInt("status");
				int coin=rs.getInt("coin");
				String bname=rs.getString("bname");
				BuyerVo vo=new BuyerVo(cid, phone, addr, email, rank, status, coin, bname);
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
	
	public int getStatus(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select status from buyer where cid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			int status=0;
			if(rs.next()) {
				status=rs.getInt("status");
			}
			return status;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public String findPwd(String id,String phone) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select m.pwd " + 
					   "from member m,buyer b " + 
					   "where m.id=b.cid and m.id=? and b.phone=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,phone);
			rs=pstmt.executeQuery();
			String pwd=null;
			if(rs.next()) {
				pwd=rs.getString("pwd");
			}
			return pwd;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}	
	}
	public int getMembersCnt() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select nvl(count(*),0) cnt from buyer";
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
	public int updateRank(String cid,String rank) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update buyer set rank=? where cid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,rank);
			pstmt.setString(2,cid);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
}
