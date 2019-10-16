package kila.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.PaymentDao;
import kila.vo.PaymentVo;

@WebServlet("/header/refundList")
public class RefundListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PaymentDao dao=PaymentDao.getInstance();
		ArrayList<PaymentVo> list=dao.getRefundList();
	
		req.setAttribute("info",list);
		req.setAttribute("cpage", "/header/refund.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
}
