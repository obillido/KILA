package kila.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.InquiryDao;
import kila.dao.ItemInfoDao;
import kila.dao.ReviewDao;
import kila.vo.InquiryVo;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;
import kila.vo.ProductInfoVo;
import kila.vo.ReviewIndexVo;
import kila.vo.ReviewListVo;

@WebServlet("/iteminfo")
public class ItmeInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		String type=(String)session.getAttribute("type");
		ItemInfoDao dao=ItemInfoDao.getInstance();
		ItemInfoVo vo=dao.productInfos(colnum);
		ArrayList<ItemInfoSizeVo> list=dao.productInfoSize(colnum);
		ArrayList<ReviewIndexVo> review=ReviewDao.getInstance().getIndex(id, colnum);
		ArrayList<ReviewListVo> rlist=ReviewDao.getInstance().list(colnum);
		String soldout=req.getParameter("soldout");
		if(soldout!=null) req.setAttribute("soldout", soldout);
		req.setAttribute("vo", vo);
		req.setAttribute("list",list);
		req.setAttribute("review", review);
		req.setAttribute("rlist", rlist);
		System.out.println(rlist.size());
		
		String att=req.getParameter("at");
		int at=0,it=0;
		String cid=req.getParameter("cid");
		if(att!=null && !att.equals("")) {
			at=Integer.parseInt(att);
			it=Integer.parseInt(req.getParameter("it"));
			req.setAttribute("at", at);
			req.setAttribute("it", it);
		}
		ArrayList<InquiryVo> inqList=InquiryDao.getInstance().getList(colnum, at, it, cid);
		
		req.setAttribute("type", type);
		req.setAttribute("cid", cid);
		req.setAttribute("inqList", inqList);
		req.setAttribute("cpage", "/kimyungi/ItemInfo.jsp");
		req.getRequestDispatcher("/layout.jsp#inquiryTitle").forward(req, resp);
	}
}
