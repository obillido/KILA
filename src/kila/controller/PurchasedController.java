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

@WebServlet("/header/purchased")
public class PurchasedController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession s=req.getSession();
		String id=(String)s.getAttribute("id");
		PaymentDao dao=PaymentDao.getInstance();
		PaymentVo vo=dao.getInfo(id);
		req.setAttribute("info",vo);
		req.getRequestDispatcher("/header/purchased.jsp").forward(req,resp);
	}
}
