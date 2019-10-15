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
import kila.dao.ItemInfoDao;
import kila.vo.BuyerVo;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;

@WebServlet("/kila/payment")
public class PaymentController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
	}
}
