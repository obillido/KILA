package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.catalina.startup.SetAllPropertiesRule;

import kila.dao.BuyerDao;
import kila.dao.CartDao;
import kila.dao.ItemInfoDao;
import kila.vo.BuyerVo;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;

@WebServlet("/kila/payment")
public class PaymentController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd.equals("insert")) {
			int colnum=Integer.parseInt(req.getParameter("scolnum"));
			int psize=Integer.parseInt(req.getParameter("spsize"));
			int cnt=Integer.parseInt(req.getParameter("pcnt"));
			HttpSession session=req.getSession(); 
			String id=(String)session.getAttribute("id");
			ItemInfoDao dao=ItemInfoDao.getInstance();
			BuyerDao dao2=new BuyerDao();
			ItemInfoVo vo=dao.productInfos(colnum);
			BuyerVo vo2=dao2.getInfo(id);
			req.setAttribute("vo", vo);
			req.setAttribute("vo2", vo2);
			req.setAttribute("psize",psize);
			req.setAttribute("cnt", cnt);
			req.getRequestDispatcher("/kimyungi/result.jsp").forward(req,resp);
		}else if(cmd.equals("cart")) {
			int paynum=Integer.parseInt(req.getParameter("paynum"));
			String paymethod = req.getParameter("paymethod");
			System.out.println(paymethod);
			CartDao dao=CartDao.getInstance();
			int n=dao.cpayment(paynum, paymethod);
			if(n>0) {
				resp.sendRedirect(req.getContextPath()+"/layout.jsp");
			}else {
				req.getRequestDispatcher("/kimyungi/result3.jsp").forward(req, resp);
			}
		}else if(cmd.equals("cart2")) {
			String[] paynum=req.getParameterValues("paynum");
			String paymethod = req.getParameter("paymethod");
			System.out.println(paymethod);
			System.out.println(paynum.length);
			CartDao dao=CartDao.getInstance();
			int n=dao.cpayment2(paynum, paymethod);
			if(n>0) {
				resp.sendRedirect(req.getContextPath()+"/layout.jsp");
			}else {
				req.getRequestDispatcher("/kimyungi/result2.jsp").forward(req, resp);
			}
		}
	}
}
