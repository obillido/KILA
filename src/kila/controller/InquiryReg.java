package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.ColorDao;

@WebServlet("/inquiry/registration")
public class InquiryReg extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		System.out.println(colnum);
		String[] info=ColorDao.getInstance().getProductInfo(colnum);
		System.out.println(info[0]+","+info[1]);
		req.setAttribute("pname", info[0]);
		req.setAttribute("color", info[1]);
		req.setAttribute("cpage", "/content/inquiryReg.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
