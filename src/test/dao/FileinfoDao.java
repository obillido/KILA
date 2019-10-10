package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import test.vo.FileinfoVo;

public class FileinfoDao {
	public int insert(FileinfoVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=
			"insert into fileinfo values(fileinfo_seq.nextval,?,?,?,?,?,?)";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,vo.getWriter());
			pstmt.setString(2,vo.getTitle());
			pstmt.setString(3,vo.getContent());
			pstmt.setString(4,vo.getOrgfilename());
			pstmt.setString(5,vo.getSavefilename());
			pstmt.setLong(6,vo.getFilesize());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt,null);
		}
	}
	public ArrayList<FileinfoVo> list(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from fileinfo";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<FileinfoVo> list=new ArrayList<FileinfoVo>();
			while(rs.next()) {
				int filenum=rs.getInt("filenum");
				String writer=rs.getString("writer");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String orgfilename=rs.getString("orgfilename");					
				String savefilename=rs.getString("savefilename");
				long filesize=rs.getLong("filesize");
				FileinfoVo vo=
					new FileinfoVo(filenum, writer, title, content, orgfilename, savefilename, filesize);
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
	public int delete(int filenum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql=
			"delete from fileinfo where filenum=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,filenum);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt,null);
		}
	}
	public FileinfoVo getinfo(int filenum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from fileinfo where filenum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,filenum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String writer=rs.getString("writer");
				String title=rs.getString("title");
				String content=rs.getString("content");
				String orgfilename=rs.getString("orgfilename");					
				String savefilename=rs.getString("savefilename");
				long filesize=rs.getLong("filesize");
				FileinfoVo vo=
					new FileinfoVo(filenum, writer, title, content, orgfilename, savefilename, filesize);
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con, pstmt, rs);
		}
	}
	public int update(FileinfoVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String sql="update fileinfo set writer=?,title=?,content=?,orgfilename=?,savefilename=?,filesize=? where filenum=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getOrgfilename());
			pstmt.setString(5, vo.getSavefilename());
			pstmt.setLong(6, vo.getFilesize());
			pstmt.setInt(7, vo.getFilenum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con, pstmt, null);
		}
	}
}










