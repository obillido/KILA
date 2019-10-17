package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.PaymentDao;

@WebServlet("/header/confirmRefund")
public class ConfirmRefundController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		PaymentDao dao=PaymentDao.getInstance();
		int n=dao.confirmRefund(paynum);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/header/refundList");
		}else {
			
		}
	}
}
