package kila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import kila.vo.ColorVo;
import kila.vo.ProductNameVo;
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
			String sql="select * from product where colnum=? and size=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, colnum);
			pstmt.setString(2, size);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt,rs);
		}
	}
	
	public int insert(String cname, String pcode, String pname, int price, String[] color, String[] size, int cnt) {
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
			}else if(pn1>0) {
				ColorDao cdao=ColorDao.getInstance();
				for(int i=0; i<color.length; i++) {
					int cn=cdao.isExist(pcode, color[i]);
					if(cn==0) {
						// 색상 테이블 삽입
						if(cdao.insert(new ColorVo(0, pcode, color[i], null, null, 0))<=0) {
							return -1;
						}
					}else if(cn>0) {
						for(int j=0; j<size.length; j++) {
							int pn=isExist(cn,size[j]);
							if(pn==0) {
								// Product Table Insert
								if(insert(new ProductVo(0, cn, size[j], cnt))<=0) {
									return -1;
								}
							}else if(pn>0){
								// Product Table Update (icnt)
								if(update(cn,size[j],cnt)<=0) {
									return -1;
								}
							}else {
								return -1;
							}
							
							// 상품등록테이블 insert
						}
					}else {
						return -1;
					}
				}
			}else {
				return -1;
			}
			return 1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
			String sql="insert into Product values(product_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,vo.getColnum());
			pstmt.setString(2, vo.getPsize());
			pstmt.setInt(3, vo.getIcnt());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.close(con,pstmt);
		}
	}
	
}