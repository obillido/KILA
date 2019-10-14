package kila.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/product/list/best")
public class BestList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// PaymentDao필요
		
		
		req.getRequestDispatcher(req.getContextPath()+"/layout.jsp?cpage=/content/bestList.jsp").forward(req, resp);
	}
}
