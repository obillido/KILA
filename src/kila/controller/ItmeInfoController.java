package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.ProductInfoDao;
import kila.vo.ProductInfoVo;

@WebServlet("/iteminfo")
public class ItmeInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pcode=req.getParameter("pcode");
		ProductInfoDao dao=ProductInfoDao.getInstance();
		ProductInfoVo vo=dao.productInfos(pcode);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/layout.jsp?cpage=/kimyungi/ItemInfo.jsp").forward(req, resp);
	}
}
