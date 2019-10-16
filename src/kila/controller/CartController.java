package kila.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.BuyerDao;
import kila.dao.CartDao;
import kila.dao.ItemInfoDao;
import kila.dao.PaymentDao;
import kila.vo.BuyerVo;
import kila.vo.CartVo;
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
		CartDao dao2=CartDao.getInstance();
		int pnum=dao.getProductnum(colnum);
		PaymentVo vo=new PaymentVo(0, id, pnum, cnt, null, 8, null);
		int n=dao.insert(vo);
		if(n>0) {
			req.setAttribute("colnum", colnum);
			req.setAttribute("cnt", cnt);
			req.setAttribute("psize", psize);
			ArrayList<CartVo> list=dao2.list(id);
			req.setAttribute("list", list);
			req.getRequestDispatcher("/kimyungi/result2.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
