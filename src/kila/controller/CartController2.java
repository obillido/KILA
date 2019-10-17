package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.BuyerDao;
import kila.dao.CartDao;
import kila.vo.BuyerVo;
import kila.vo.CartVo;

@WebServlet("/kila/cart2")
public class CartController2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		int paynum=Integer.parseInt(String.valueOf(session.getAttribute("paynum")));
		String id=(String)session.getAttribute("id");
		CartDao dao=CartDao.getInstance();
		CartVo vo=dao.list2(paynum);
		BuyerDao dao2=new BuyerDao();
		BuyerVo vo2=dao2.getInfo(id);
		req.setAttribute("vo", vo);
		req.setAttribute("vo2", vo2);
		req.getRequestDispatcher("/kimyungi/result3.jsp").forward(req, resp);
	}
}
