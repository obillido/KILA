package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.PaymentDao;
import kila.vo.PaymentVo;

@WebServlet("/header/cancelOrder")
public class CancelOrderController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		PaymentDao dao=PaymentDao.getInstance();
		int n=dao.cancelOrder(paynum);
		if(n>0) {
			PaymentVo vo=dao.getPaymentInfo(paynum);
			if(dao.updateIcnt(vo.getPnum(), -vo.getCnt())>0){
				resp.sendRedirect(req.getContextPath()+"/header/purchased");
			}
		}
		resp.sendRedirect(req.getContextPath()+"/layout.jsp");
	}
}
