package kila.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.ProductInfoDao;
import kila.vo.ProductInfoVo;

@WebServlet("/product/best")
public class ProductBestList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getList(1, 10, "all", 1, null, null, null, null);
		req.setAttribute("list", list);
		req.setAttribute("cpage", "/content/productList/bestList.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
