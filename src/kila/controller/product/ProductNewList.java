package kila.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/new")
public class ProductNewList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sql="select pc.*, mr from " + 
				"(select pnum, cname, pname, color.colnum, color  from product, color, product_name pn where product.colnum=color.colnum and color.pcode=pn.pcode) pc, " + 
				"(select pnum, min(regdate) mr from product_reg group by pnum) pr " + 
				"where pc.pnum=pr.pnum order by mr ";
		
		req.setAttribute("cpage", "/content/productList/newList.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
