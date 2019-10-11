package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JdbcUtil;
import kila.vo.ColorVo;
import kila.vo.ProductInfoVo;
import kila.vo.ProductNameVo;
import kila.vo.ProductRegVo;
import kila.vo.ProductVo;

public class ProductDao {
	private static ProductDao instance=new ProductDao();
	private ProductDao() {}
	public static ProductDao getInstance() {
		return instance;
	}
	
	public int isExist(int colnum, String size) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select * from product where colnum=? and psize=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			pstmt.setString(2, size);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("pnum");
			}
			return 0;
		}catch(SQLException se) {
			System.out.println("ProductDAO:isExist:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int insert(String cname, String pcode, String pname, int price, 
			String[] color, String[] orgfilename, String[] savefilename, 
			Long[] filesize, String[] psize, int cnt) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			ProductNameDao pndao=ProductNameDao.getInstance();
			int pn1=pndao.isExist(pcode);
			if(pn1==0) {
				// 상품명 테이블 삽입
				if(pndao.insert(new ProductNameVo(pcode, cname, pname, price))<=0) {
					return -1;
				}
			}else if(pn1<0) {
				return -1;
			}
			
			ColorDao cdao=ColorDao.getInstance();
			for(int i=0; i<color.length; i++) {
				int colnum=cdao.isExist(pcode, color[i]);
				if(colnum==0) {
					// 색상 테이블 삽입
					if(cdao.insert(new ColorVo(0, pcode, color[i], orgfilename[i], savefilename[i], filesize[i]))<=0) {
						return -1;
					}
					colnum=cdao.isExist(pcode,color[i]);
				}else if(colnum<0) {
					return -1;
				}

				for(int j=0; j<psize.length; j++) {
					int pnum=isExist(colnum,psize[j]);
					if(pnum==0) {
						// Product Table Insert
						int nn=insert(new ProductVo(0, colnum, psize[j], cnt));
						System.out.println(nn);
						if(nn<=0) {
							return -1;
						}
						pnum=isExist(colnum,psize[j]);
					}else if(pnum>0){
						// Product Table Update (icnt)
						if(update(colnum,psize[j],cnt)<=0) {
							return -1;
						}
					}else {
						return -1;
					}
					// 상품등록테이블 insert
					if(ProductRegDao.getInstance().insert(new ProductRegVo(0, pnum, cnt, null))<=0){
						return -1;
					}
				}
			}
			return 1;
		}catch(SQLException se) {
			System.out.println("ProductDAO:insert:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
	public int insert(ProductVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="insert into product values(product_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getColnum());
			pstmt.setString(2, vo.getPsize());
			pstmt.setInt(3, vo.getIcnt());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("ProductDAO:insert:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
	public int update(int colnum, String size, int cnt) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=JdbcUtil.getConn();
			String sql="update product set icnt=icnt+? where colnum=? and psize=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, cnt);
			pstmt.setInt(2, colnum);
			pstmt.setString(3, size);
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("ProductDAO:update:"+se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
	public ArrayList<ProductInfoVo> getList(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=JdbcUtil.getConn();
			String sql="select pn.pcode, savefilename, cname, pname, price, color, psize, icnt from product_name pn, color, product " + 
						"where pn.pcode=color.pcode and color.colnum=product.colnum " + 
						"order by pcode";
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
