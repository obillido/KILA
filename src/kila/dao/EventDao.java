package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
