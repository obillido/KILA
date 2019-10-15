package kila.controller.product;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.ProductDao;
import kila.dao.ProductInfoDao;
import kila.vo.ProductInfoVo;
import kila.vo.ProductVo;

@WebServlet("/product/list")
public class ProductList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category=req.getParameter("category");
		String torder=req.getParameter("order");
		int order=1;
		if(torder!=null) {
			order=Integer.parseInt(torder);
		}

		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int endRow=pageNum*8;
		int startRow=endRow-7;
		
		ProductInfoDao dao=ProductInfoDao.getInstance();
		int pageCount=(int)Math.ceil(dao.getCount(category)/8.);
		int startPageNum=(pageNum-1)/5*5+1;
		int endPageNum=startPageNum+4;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		
		String[] colors=req.getParameterValues("color");
		String colorVal=req.getParameter("colorVal");
		if(colorVal!=null) {
			colors=colorVal.split("%");
		}
		String[] psizes=req.getParameterValues("psize");
		String sizeVal=req.getParameter("sizeVal");
		if(sizeVal!=null) {
			psizes=sizeVal.split("%");
		}
		String[] priceRange=req.getParameterValues("price");
		String priceVal=req.getParameter("priceVal");
		if(priceVal!=null) {
			priceRange=priceVal.split("%");
		}
		
		
		ArrayList<ProductInfoVo> list=dao.getListC(startRow,endRow,category,order);
		
		Point pr=dao.getPriceRange(category);
		
		req.setAttribute("category", category);
		req.setAttribute("pageNum",pageNum);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("order",order);
		req.setAttribute("list", list);
		req.setAttribute("minPrice", pr.x);
		req.setAttribute("maxPrice", pr.y);
		req.getRequestDispatcher("/layout.jsp?cpage=/content/productList/productList.jsp").forward(req, resp);
	}
}
