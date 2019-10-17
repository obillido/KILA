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

@WebServlet("/header/salesRevenue2")
public class SalesRevenueController2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PaymentDao dao=PaymentDao.getInstance();
		int pnum=Integer.parseInt(req.getParameter("pnum"));
		int cnt=dao.getCnt(pnum);
		int price=dao.getPrice(pnum);
		int tot3=0;
		if(cnt>0 && price>0) {
			tot3=price*cnt;
			req.setAttribute("tot3",tot3);
			req.setAttribute("cpage", "/header/salesRevenue.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req,resp);
		}
	
	}
}
