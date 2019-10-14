package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.SetAllPropertiesRule;

import kila.dao.ItemInfoDao;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;

@WebServlet("/kila/payment")
public class PaymentController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int colnum=Integer.parseInt(req.getParameter("scolnum"));
		int psize=Integer.parseInt(req.getParameter("spsize"));
		int cnt=Integer.parseInt(req.getParameter("pcnt"));
		ItemInfoDao dao=ItemInfoDao.getInstance();
		ItemInfoVo vo=dao.productInfos(colnum);
		req.setAttribute("vo", vo);
		req.setAttribute("psize",psize);
		req.setAttribute("cnt", cnt);
		req.getRequestDispatcher("/kimyungi/result.jsp").forward(req,resp);
	}
}
