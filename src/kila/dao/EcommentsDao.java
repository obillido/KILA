package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.EcommentsVo;

public class EcommentsDao {
	//싱글톤 객체로 만들기
	private static EcommentsDao ecommentsDao=new EcommentsDao();
	public static EcommentsDao getEcommentsDao() {
		return ecommentsDao;
	}
	private EcommentsDao() {}
	
	public ArrayList<EcommentsVo> list(int evnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from ecomments where evnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,evnum);
			rs=pstmt.executeQuery();
			ArrayList<EcommentsVo> list=new ArrayList<EcommentsVo>();
			while(rs.next()) {
				EcommentsVo vo=new EcommentsVo(rs.getInt("ecnum"),rs.getInt("evnum"),rs.getString("ecid"),rs.getString("ecomments"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int insert(EcommentsVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into ecomments values(ecomments_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getEvnum());
			pstmt.setString(2,vo.getEcid());
			pstmt.setString(3,vo.getEcomments());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,null);
		}
	}
}
