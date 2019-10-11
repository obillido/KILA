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
		ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getList();
		req.setAttribute("list", list);
		//관리자 일때
		//req.getRequestDispatcher("/admin/productList.jsp").forward(req, resp);
		
		//구매자일때
		req.getRequestDispatcher("/content/productList/productList.jsp").forward(req, resp);
	}
}
