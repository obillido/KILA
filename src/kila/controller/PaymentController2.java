package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.PaymentDao;
import kila.vo.PaymentVo;

@WebServlet("/kila/payment2")
public class PaymentController2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		int colnum = Integer.parseInt(req.getParameter("colnum"));
		int psize= Integer.parseInt(req.getParameter("psize"));
		PaymentDao dao=PaymentDao.getInstance();
		int pnum=dao.getProductnum(colnum,psize);
		int cnt = Integer.parseInt(req.getParameter("cnt"));
		String paymethod = req.getParameter("paymethod");
		PaymentVo vo=new PaymentVo(0, id, pnum, cnt, null, 1, paymethod);
		int n=dao.insert(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/header/purchased");
		}else {
			req.getRequestDispatcher("/kimyungi/result.jsp").forward(req, resp);
		}
	}
}
