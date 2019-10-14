package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.EventVo;

public class EventDao {
	public int insert(EventVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into event values(event_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getTitle());
			pstmt.setString(2,vo.getContent());
			pstmt.setString(3,vo.getOrgfilename());
			pstmt.setString(4,vo.getSavefilename());
			pstmt.setLong(5,vo.getFilesize());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
	public ArrayList<EventVo> list(){
		ArrayList<EventVo> list=new ArrayList<EventVo>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from event";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int num=rs.getInt("enum");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String orgfilename=rs.getString("orgfilename");
				String savefilename=rs.getString("savafilename");
				long filesize=rs.getLong("filesize");
				EventVo vo=new EventVo(num,title,content,orgfilename,savefilename,filesize);
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
}
