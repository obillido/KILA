package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.controller.ItmeInfoController;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;
import kila.vo.ProductInfoVo;

public class ProductInfoDao {
	private static ProductInfoDao instance=new ProductInfoDao();
	private ProductInfoDao() {}
	public static ProductInfoDao getInstance() {
		return instance;
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
				ItemInfoSizeVo vo = new ItemInfoSizeVo(rs.getInt("psize"), rs.getInt("icnt"));
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
			System.out.println("ProductInfoDAO:list:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public ArrayList<ProductInfoVo> getListC(int startRow, int endRow, String category, int order){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String cwhere="";
			if(!category.equals("all")) cwhere="and cname='"+category+"' ";
			
			String owhere="";
			switch(order) {
			case 1: owhere="pcode"; break; // 나중에 수정히기 : 판매순
			case 2: owhere="pname"; break; // 나중에 수정히기 : 신상순
			case 3: owhere="price asc"; break;
			case 4: owhere="price desc"; break;
			default: System.out.println("ProductInfoDao:getListC:???");break;
			}
			
			if(category.equals("all") || category==null || category.equals("")) cwhere="";
			String sql="select * from "+
						"(select aa.*,rownum rnum " +
						"from (select pn.pcode, cname, pname, price, colnum, color, savefilename " + 
					   "from product_name pn, color " + 
					   "where pn.pcode=color.pcode "+cwhere+ 
					   "order by "+owhere+", color) aa " +
					   ") where rnum between ? and ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<ProductInfoVo> list=new ArrayList<ProductInfoVo>();
			while(rs.next()) {
				list.add(new ProductInfoVo(
						rs.getString("pcode"), 
						rs.getString("cname"), 
						rs.getString("pname"), 
						rs.getInt("price"), 
						rs.getInt("colnum"),
						rs.getString("color"),
						rs.getString("savefilename")));
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductInfoDAO:getListC"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	
	public int getCount(String category) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String cwhere="";
			if(!category.equals("all")) cwhere="and cname='"+category+"' ";
			String sql="select count(*) cnt " + 
					   "from product_name pn, color " + 
					   "where pn.pcode=color.pcode "+cwhere;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
			}else {
				return -1;
			}
		}catch(SQLException se) {
			System.out.println("ProductInfoDAO:getCount:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
}
