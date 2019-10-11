package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ProductInfoVo;

public class ProductInfoDao {
	private static ProductInfoDao instance=new ProductInfoDao();
	private ProductInfoDao() {}
	public static ProductInfoDao getInstance() {
		return instance;
	}
	
	public ProductInfoVo productInfos(String pcode,int colnum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_name p,color c, product d,product_reg r where p.pcode=c.pcode and d.colnum=c.colnum and r.pnum=d.pnum and p.pcode=? and c.colnum=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			pstmt.setInt(2, colnum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				ProductInfoVo vo=new ProductInfoVo(rs.getString("pcode"),rs.getString("cname"), 
						rs.getString("pname"),rs.getInt("price"),rs.getString("color"), 
						rs.getString("psize"),rs.getInt("icnt"));
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
	public ArrayList<ProductInfoVo> productInfoColor(String pcode) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select * from product_name p,color c where p.pcode=c.pcode and p.pcode=?";
		try {
			con=JdbcUtil.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			ArrayList<ProductInfoVo> list=new ArrayList<ProductInfoVo>();
			while(rs.next()) {
				ProductInfoVo vo=new ProductInfoVo(rs.getString("pcode"),rs.getString("cname"), 
						rs.getString("pname"),rs.getInt("price"),rs.getString("color"), 
						rs.getString("psize"),rs.getInt("icnt"));
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

	

	public ArrayList<ProductInfoVo> getList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select pn.pcode, cname, pname, price, color, savefilename, psize, icnt from product_name pn, color, product " + 
						"where pn.pcode=color.pcode and color.colnum=product.colnum " + 
						"order by pcode, pname, color, psize";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<ProductInfoVo> list=new ArrayList<ProductInfoVo>();
			while(rs.next()) {
				list.add(new ProductInfoVo(
						rs.getString("pcode"), 
						rs.getString("cname"), 
						rs.getString("pname"), 
						rs.getInt("price"), 
						rs.getString("color"),
						rs.getString("savefilename"),
						rs.getString("psize"), 
						rs.getInt("icnt")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductDAO:list:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<ProductInfoVo> getList(String category){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select pn.pcode, cname, pname, price, color, savefilename, psize, icnt from product_name pn, color, product " + 
						"where pn.pcode=color.pcode and color.colnum=product.colnum and cname=" +category+" "+ 
						"order by pcode, pname, color, psize";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ArrayList<ProductInfoVo> list=new ArrayList<ProductInfoVo>();
			while(rs.next()) {
				list.add(new ProductInfoVo(
						rs.getString("pcode"), 
						rs.getString("cname"), 
						rs.getString("pname"), 
						rs.getInt("price"), 
						rs.getString("color"),
						rs.getString("savefilename"),
						rs.getString("psize"), 
						rs.getInt("icnt")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductDAO:list:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	
}
