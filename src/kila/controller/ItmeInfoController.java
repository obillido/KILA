package kila.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.ItemInfoDao;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;
import kila.vo.ProductInfoVo;

@WebServlet("/iteminfo")
public class ItmeInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String pcode=req.getParameter("pcode");
		String pcode="DJ2POB3103x";
		int colnum=24;
		ItemInfoDao dao=ItemInfoDao.getInstance();
		ItemInfoVo vo=dao.productInfos(pcode, colnum);
		ArrayList<ItemInfoSizeVo> list=dao.productInfoSize(colnum);
		req.setAttribute("vo", vo);
		req.setAttribute("list",list);
		req.getRequestDispatcher("/layout.jsp?cpage=/kimyungi/ItemInfo.jsp").forward(req, resp);
	}
}
