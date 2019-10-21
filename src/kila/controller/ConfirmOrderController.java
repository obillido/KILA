package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.PaymentDao;

@WebServlet("/header/confirmOrder")
public class ConfirmOrderController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		PaymentDao dao=PaymentDao.getInstance();
		int n=dao.confirmOrder(paynum);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/header/purchased");
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
	}
}
