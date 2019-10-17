package kila.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.InquiryDao;
import kila.dao.ItemInfoDao;
import kila.vo.InquiryVo;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;
import kila.vo.ProductInfoVo;

@WebServlet("/iteminfo")
public class ItmeInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		ItemInfoDao dao=ItemInfoDao.getInstance();
		ItemInfoVo vo=dao.productInfos(colnum);
		ArrayList<ItemInfoSizeVo> list=dao.productInfoSize(colnum);
		ArrayList<InquiryVo> inqList=InquiryDao.getInstance().getList(colnum);
		req.setAttribute("vo", vo);
		req.setAttribute("list",list);
		req.setAttribute("inqList", inqList);
		req.setAttribute("cpage", "/kimyungi/ItemInfo.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		
	}
}
