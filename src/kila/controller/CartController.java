package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.BuyerDao;
import kila.dao.ItemInfoDao;
import kila.dao.PaymentDao;
import kila.vo.BuyerVo;
import kila.vo.ItemInfoVo;
import kila.vo.PaymentVo;

@WebServlet("/kila/cart")
public class CartController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		int colnum=Integer.parseInt(req.getParameter("scolnum"));
		int psize=Integer.parseInt(req.getParameter("spsize"));
		int cnt = Integer.parseInt(req.getParameter("pcnt"));
		PaymentDao dao=PaymentDao.getInstance();
		ItemInfoDao dao2=ItemInfoDao.getInstance();
		int pnum=dao.getProductnum(colnum);
		PaymentVo vo=new PaymentVo(0, id, pnum, cnt, null, 8, null);
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("colnum", colnum);
			req.setAttribute("cnt", cnt);
			req.setAttribute("psize", psize);
			ItemInfoVo vo2=dao2.productInfos(colnum);
			req.setAttribute("vo", vo2);
			req.getRequestDispatcher("/kimyungi/result2.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
