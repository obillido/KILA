package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/kila/cart")
public class CartController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int colnum=Integer.parseInt(req.getParameter("scolnum"));
		int psize=Integer.parseInt(req.getParameter("spsize"));
		int cnt=Integer.parseInt(req.getParameter("pcnt"));
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		
	}
}
