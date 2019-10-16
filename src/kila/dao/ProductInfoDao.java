package kila.dao;

import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ItemInfoSizeVo;
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
	
	

	
	public ArrayList<ProductInfoVo> getListC(int startRow, int endRow, String category, int order, String colorVal, String sizeVal, String priceVal, String div){
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs1=null;
		ResultSet rs2=null;
		ArrayList<ProductInfoVo> list=new ArrayList<ProductInfoVo>();
		try {
			con=JdbcUtil.getConn();
			String cwhere="";
			if(!category.equals("all")) cwhere="and cname='"+category+"' ";
			
			String otable=" ";
			String owhere=" ";
			String orderby=" ";
			String oselect=" ";
			switch(order) {
			case 1: orderby=" pcode "; break; // 나중에 수정히기 : 판매순
			case 2: otable=", (select pnum, min(regdate) mr from product_reg group by pnum) pr ";
					owhere=" and product.colnum=color.colnum and pr.pnum=product.pnum ";
					oselect=", mr ";
					orderby="mr desc"; 
					break;
			case 3: orderby=" price asc "; break;
			case 4: orderby=" price desc "; break;
			default: System.out.println("ProductInfoDao:getListC:???");break;
			}
			
			String colorwhere="";
			if(colorVal!=null && !colorVal.equals("")) {
				String[] colors=colorVal.split(div);
				for(int i=0; i<colors.length; i++) {
					if(i==0) colorwhere=" and (";
					else colorwhere+=" or ";
					colorwhere+=" color.color='"+colors[i]+"' ";
					if(i==colors.length-1) colorwhere+=") ";
				}
			}
			String sizewhere="";
			if(sizeVal!=null && !sizeVal.equals("")) {
				String[] sizes=sizeVal.split(div);
				for(int i=0; i<sizes.length; i++) {
					if(i==0) sizewhere=" and (";
					else sizewhere+=" or ";
					sizewhere+=" product.psize="+sizes[i]+" ";
					if(i==sizes.length-1) sizewhere+=") ";
				}
			}
			String pricewhere="";
			String[] price=priceVal.split(div);
			if(!price[0].equals("null") && !price[0].equals("")) {
				pricewhere+=" and price>"+price[0]+" ";
			}
			if(!price[1].equals("null") && !price[1].equals("")) {
				pricewhere+=" and price<"+price[1]+" ";
			}
			
			
			
			if(category.equals("all") || category==null || category.equals("")) cwhere="";
			String sql= "select * from "+
						"(select aa.*,rownum rnum " +
						"from (select distinct pn.pcode, cname, pname, price, color.colnum, color, savefilename, scnt " + oselect +
						"from product_name pn, color, product, (select colnum cl, sum(icnt) scnt from product group by colnum) cc" + otable +
						"where scnt>0 and pn.pcode=color.pcode and cc.cl=color.colnum "+cwhere+owhere+colorwhere+sizewhere+pricewhere+
						"order by "+orderby+", color) aa " +
						") where rnum between ? and ?";
			pstmt1=con.prepareStatement(sql);
			pstmt1.setInt(1, startRow);
			pstmt1.setInt(2, endRow);
			rs1=pstmt1.executeQuery();
			while(rs1.next()) {
				list.add(new ProductInfoVo(
						rs1.getString("pcode"), 
						rs1.getString("cname"), 
						rs1.getString("pname"), 
						rs1.getInt("price"), 
						rs1.getInt("colnum"),
						rs1.getString("color"),
						rs1.getString("savefilename")));
				startRow++;
			}
			if(startRow!=endRow) {
				sql="select * from "+
					"(select aa.*,rownum rnum " +
					"from (select distinct pn.pcode, cname, pname, price, color.colnum, color, savefilename, scnt " + oselect +
					"from product_name pn, color, product, (select colnum cl, sum(icnt) scnt from product group by colnum) cc" + otable +
					"where scnt=0 and pn.pcode=color.pcode and cc.cl=color.colnum "+cwhere+owhere+colorwhere+sizewhere+pricewhere+
					"order by "+orderby+", color) aa " +
					") where rnum between ? and ?";
				pstmt2=con.prepareStatement(sql);
				pstmt2.setInt(1, startRow);
				pstmt2.setInt(2, endRow);
				rs2=pstmt2.executeQuery();
				while(rs2.next()) {
					list.add(new ProductInfoVo(
							rs2.getString("pcode"), 
							rs2.getString("cname"), 
							rs2.getString("pname"), 
							rs2.getInt("price"), 
							rs2.getInt("colnum"),
							rs2.getString("color"),
							rs2.getString("savefilename")));
				}
			}
			return list;
		}catch(SQLException se) {
			System.out.println("ProductInfoDAO:getListC:"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt2);
			JdbcUtil.close(con,pstmt1,rs1);
		}
	}
	
	
	public int getCount(String category, String colorVal, String sizeVal, String priceVal, String div) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String cwhere="";
			if(!category.equals("all")) cwhere="and cname='"+category+"' ";

			String colorwhere="";
			if(colorVal!=null && !colorVal.equals("")) {
				String[] colors=colorVal.split(div);
				for(int i=0; i<colors.length; i++) {
					if(i==0) colorwhere=" and (";
					else colorwhere+=" or ";
					colorwhere+=" color.color='"+colors[i]+"' ";
					if(i==colors.length-1) colorwhere+=") ";
				}
			}
			String sizewhere="";
			if(sizeVal!=null && !sizeVal.equals("")) {
				String[] sizes=sizeVal.split(div);
				for(int i=0; i<sizes.length; i++) {
					if(i==0) sizewhere=" and (";
					else sizewhere+=" or ";
					sizewhere+=" product.psize="+sizes[i]+" ";
					if(i==sizes.length-1) sizewhere+=") ";
				}
			}
			String pricewhere="";
			String[] price=priceVal.split(div);
			if(!price[0].equals("null") && !price[0].equals("")) {
				pricewhere+=" and price>"+price[0]+" ";
			}
			if(!price[1].equals("null") && !price[1].equals("")) {
				pricewhere+=" and price<"+price[1]+" ";
			}
			
			
			String sql="select count(*) cnt from\r\n" + 
					"(select distinct pn.pcode, pname, color\r\n" + 
					"from product_name pn, color, product\r\n" + 
					"where pn.pcode=color.pcode "+cwhere+colorwhere+sizewhere+pricewhere+")";
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
	
	
	public Point getPriceRange(String category) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String cwhere="";
			if(!category.equals("all")) cwhere=" where cname='"+category+"' ";
			String sql="select min(price) minP, max(price) maxP from product_name" + cwhere;
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			Point pp=null;
			if(rs.next()) {
				pp=new Point(rs.getInt("minP"), rs.getInt("maxP"));
			}
			return pp;
		}catch(SQLException se) {
			System.out.println("ProductInfoDAO:getPriceRange"+se.getMessage());
			return null;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
}
