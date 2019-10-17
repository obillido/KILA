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
		String cmd=req.getParameter("cmd");
		if(cmd==null) {
			HttpSession session=req.getSession(); 
			String id=(String)session.getAttribute("id");
			ArrayList<CartVo> list=CartDao.getInstance().list(id);
			req.setAttribute("list", list);
			req.setAttribute("cpage", "/kimyungi/cartview.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}else if(cmd.equals("insert")) {
			insert(req,resp);
		}else if(cmd.equals("delete")) {
			delete(req,resp);
		}else if(cmd.equals("spayment")) {
			spayment(req,resp);
		}else if(cmd.equals("cartaction")) {
			cartaction(req,resp);
		}else if(cmd.equals("cartaction2")) {
			cartaction2(req,resp);
		}
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		int colnum=Integer.parseInt(req.getParameter("scolnum"));
		int cnt = Integer.parseInt(req.getParameter("pcnt"));
		int psize = Integer.parseInt(req.getParameter("spsize"));
		PaymentDao dao=PaymentDao.getInstance();
		int pnum=dao.getProductnum(colnum,psize);
		PaymentVo vo=new PaymentVo(0, id, pnum, cnt, null, 8, null);
		int n=dao.insert(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/kila/cart");
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		CartDao dao=CartDao.getInstance();
		int n=dao.delete(paynum);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/kila/cart");
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
	}
	protected void spayment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		session.setAttribute("paynum", paynum);
		resp.sendRedirect(req.getContextPath()+"/kila/cart2");
	}
	protected void cartaction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paynum=(req.getParameterValues("paynum"));
		CartDao dao=CartDao.getInstance();
		int n=dao.delete2(paynum);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/kila/cart");
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
	}
	protected void cartaction2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] paynum=(req.getParameterValues("paynum"));
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		CartDao dao=CartDao.getInstance();
		BuyerDao dao2=new BuyerDao();
		BuyerVo vo2=dao2.getInfo(id);
		ArrayList<CartVo> list=dao.list3(paynum);
		int tot=dao.totprice(paynum);
		if(list!=null) {
			req.setAttribute("list", list);
			req.setAttribute("tot", tot);
			req.setAttribute("vo2", vo2);
			req.getRequestDispatcher("/kimyungi/result2.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
	}
}
