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
		req.setCharacterEncoding("utf-8");
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		PaymentDao dao=PaymentDao.getInstance();
		ArrayList<PaymentVo> list=dao.getRefundList(startRow,endRow);
		int pageCount=(int)Math.ceil(dao.getRefundCnt()/5.0);
		int startPageNum=(pageNum-1)/5*5+1;
		int endPageNum=startPageNum+4;
		if(endPageNum>pageCount) {
			endPageNum=pageCount;
		}
		req.setAttribute("info",list);
		req.setAttribute("pageCount",pageCount);
		req.setAttribute("startPageNum",startPageNum);
		req.setAttribute("endPageNum",endPageNum);
		req.setAttribute("pageNum",pageNum);
		req.setAttribute("cpage", "/header/refund.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
}
