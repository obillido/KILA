package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ItemInfoColorVo;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;

public class ItemInfoDao {
	private static ItemInfoDao instance=new ItemInfoDao();
	private ItemInfoDao() {}
	public static ItemInfoDao getInstance() {
		return instance;
	}

	public ItemInfoVo productInfos(String pcode,int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_name p,color c where p.pcode=c.pcode and p.pcode=? and c.colnum=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			pstmt.setInt(2, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ItemInfoVo vo=new ItemInfoVo(rs.getString("pcode"), 
						rs.getString("pname"),rs.getInt("price"),rs.getString("savefilename"));
				return vo;
			}
			return null;
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:productInfos:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public ArrayList<ItemInfoColorVo> productInfoColor(String pcode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from color where pcode=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			ArrayList<ItemInfoColorVo> list=new ArrayList<ItemInfoColorVo>();
			while(rs.next()) {
				ItemInfoColorVo vo=new ItemInfoColorVo(rs.getString("color"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:productInfos:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	public ArrayList<ItemInfoSizeVo> productInfoSize(int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_name p,color c where p.pcode=c.pcode and p.pcode=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			rs=pstmt.executeQuery();
			ArrayList<ItemInfoSizeVo> list=new ArrayList<ItemInfoSizeVo>();
			while(rs.next()) {
				ItemInfoSizeVo vo = new ItemInfoSizeVo(rs.getString("psize"), rs.getInt("icnt"));
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductNameDAO:productInfos:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
}
