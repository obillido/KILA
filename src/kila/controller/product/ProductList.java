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

		ProductInfoDao dao=ProductInfoDao.getInstance();
		
		String category=req.getParameter("category");
		String torder=req.getParameter("order");
		int order=1;
		if(torder!=null) {
			order=Integer.parseInt(torder);
		}

		String div="/";
		String colorVal=req.getParameter("colorVal");
		if(colorVal==null) {
			String[] colors=req.getParameterValues("color");
			if(colors==null || colors.length==0) {
				colorVal="";
			}else{
				colorVal=colors[0];
				for(int i=1; i<colors.length; i++) {
					colorVal+=div+colors[i];
				}
			}
		}
		String sizeVal=req.getParameter("sizeVal");
		if(sizeVal==null) {
			String[] psizes=req.getParameterValues("psize");
			if(psizes==null || psizes.length==0) {
				sizeVal="";
			}else {
				sizeVal=psizes[0];
				for(int i=1; i<psizes.length; i++) {
					sizeVal+=div+psizes[i];
				}
			}
		}
		

		Point pr=dao.getPriceRange(category);
		String priceVal=req.getParameter("priceVal");
		if(priceVal==null) {
			String pr1=req.getParameter("price1");
			String pr2=req.getParameter("price2");
			if(pr1==null) pr1=String.valueOf(pr.x);
			if(pr2==null) pr2=String.valueOf(pr.y);
			priceVal=pr1+div+pr2;
		}

		

		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int endRow=pageNum*8;
		int startRow=endRow-7;
		
		int pageCount=(int)Math.ceil(dao.getCount(category,colorVal,sizeVal,priceVal, div)/8.);
		int startPageNum=(pageNum-1)/5*5+1;
		int endPageNum=startPageNum+4;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		
		
		ArrayList<ProductInfoVo> list=dao.getListC(startRow,endRow,category,order,colorVal,sizeVal,priceVal,div);
		
		
		req.setAttribute("category", category);
		req.setAttribute("pageNum",pageNum);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("order",order);
		req.setAttribute("list", list);
		req.setAttribute("colorVal", colorVal);
		req.setAttribute("sizeVal", sizeVal);
		req.setAttribute("priceVal", priceVal);
		req.setAttribute("cpage", "/content/productList/productList.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
