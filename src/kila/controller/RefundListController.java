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
		PaymentVo vo=new PaymentVo();
		for(int i=0;i<list.size();i++) {
			int paynum=list.get(i).getPaynum();
			String bid=list.get(i).getBid();
			int pnum=list.get(i).getPnum();
			int cnt=list.get(i).getCnt();
			Date paydate=list.get(i).getPaydate();
			int status=list.get(i).getStatus();
			String paymethod=list.get(i).getPaymethod();
			vo=new PaymentVo(paynum, bid, pnum, cnt, paydate, status, paymethod);
			list.add(vo);
			System.out.println("paynum:" + paynum);
		}
		req.setAttribute("info",list);
		req.getRequestDispatcher("/header/refund.jsp").forward(req,resp);
	}
}
