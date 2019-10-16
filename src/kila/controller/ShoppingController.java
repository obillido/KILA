package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/header/shopping")
public class ShoppingController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			req.setAttribute("cpage", "/header/login.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}else {
			
		}
	}
}
