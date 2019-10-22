package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ReviewIndexVo;
import kila.vo.ReviewListVo;
import kila.vo.ReviewVo;

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
			String sql="select c.color,pd.psize,pm.paynum from payment pm,product pd,color c where pm.pnum=pd.pnum and pd.colnum=c.colnum and bid=? and status=4 and c.colnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, colnum);
			ArrayList<ReviewIndexVo> list=new ArrayList<ReviewIndexVo>();
			rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1));
				System.out.println(rs.getInt(2));
				System.out.println(rs.getInt(3));
				list.add(new ReviewIndexVo(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ReviewDAO:"+se.getMessage());
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
			System.out.println("ReviewDAO:"+se.getMessage());
			return false;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public int insert(ReviewVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into review VALUES(review_seq.nextval,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getPaynum());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getRpoint());
			pstmt.setString(4, vo.getSavefilename());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("ReviewDAO:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public ArrayList<ReviewListVo> list(int colnum,String ch,int startRow,int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from" + 		
					"    (" + 
					"        select aa.*,rownum rnum from" + 
					"        (" + 
					"            select pv.rpoint,pv.content,pv.savefilename,rpad(substr(pm.bid,0,4),length(pm.bid),'*') bid,pv.regdate,c.color,pd.psize from payment pm,product pd,review pv,color c where pm.pnum=pd.pnum and pm.paynum=pv.paynum and c.colnum=pd.colnum and c.colnum=? order by "+ch+" DESC" + 
					"        )aa" + 
					")where rnum>=? and  rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,endRow);
			rs=pstmt.executeQuery();
			ArrayList<ReviewListVo> list=new ArrayList<ReviewListVo>();
			while(rs.next()) {
				list.add(new ReviewListVo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5),rs.getString(6),rs.getInt(7)));
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ReviewDAO:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	public int changeyo(int paynum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update payment set status=6 where paynum=?";
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
	public int getCount(String ch,int colnum) {//전체 글의 갯수 구하기
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select NVL(count(*),0) from review r,payment pm,product p where r.paynum=pm.paynum and pm.pnum=p.pnum and colnum=? order by "+ch;
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int cnt=rs.getInt(1);
				System.out.println(cnt);
				return cnt;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}	
	}
}