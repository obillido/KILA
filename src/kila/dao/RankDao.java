package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.SetRankVo;

public class RankDao {
	public ArrayList<SetRankVo> getInfo(String bid) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<SetRankVo> list=new ArrayList<SetRankVo>();
		try {
			con=JdbcUtil.getConn();
			String sql="select pnum,cnt from payment where status=4 and bid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,bid);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int pnum=rs.getInt("pnum");
				int cnt=rs.getInt("cnt");
				SetRankVo vo=new SetRankVo(bid,pnum,cnt);
				list.add(vo);
			}
			return list; 
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
}
