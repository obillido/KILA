package kila.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kila.dao.ProductDao;

@WebServlet("/product/productRegi")
public class ProductRegi extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/admin/productRegi.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String cname=req.getParameter("cname");
		String pcode=req.getParameter("pcode");
		String pname=req.getParameter("pname");
		int price=Integer.parseInt(req.getParameter("price"));
		String[] color=req.getParameterValues("color");
		String[] size=req.getParameterValues("size");
		int cnt=Integer.parseInt(req.getParameter("cnt"));
		
		int n=ProductDao.getInstance().insert(cname, pcode, pname, price, color, size, cnt);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/product/list");
		}else {
			req.setAttribute("msg", "등록실패");
			req.getRequestDispatcher("/admin/productRegi.jsp").forward(req, resp);
		}
	}
}
