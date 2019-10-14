package kila.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.ProductDao;
import kila.dao.ProductInfoDao;
import kila.vo.ProductInfoVo;
import kila.vo.ProductVo;

@WebServlet("/product/list")
public class ProductList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type=req.getParameter("type");
		if(type==null) {
			//구매자일때
			String category=req.getParameter("category");
			ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getListC(category);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/layout.jsp?cpage=/content/productList/productListLayout.jsp").forward(req, resp);
		}else {
			//관리자 일때
			ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getList();
			req.setAttribute("list", list);
			req.getRequestDispatcher("/layout.jsp?cpage=/admin/productList.jsp").forward(req, resp);
		}
	}
}
