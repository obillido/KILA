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
import kila.dao.ProductDao;
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
		String ch=req.getParameter("ch");
		if(ch==null) {
			ch="rnum";
		}
		System.out.println(ch);
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		String type=(String)session.getAttribute("type");
		ItemInfoDao dao=ItemInfoDao.getInstance();
		ItemInfoVo vo=dao.productInfos(colnum);
		ArrayList<ItemInfoSizeVo> list=dao.productInfoSize(colnum);
		ArrayList<ReviewIndexVo> review=ReviewDao.getInstance().getIndex(id, colnum);
		ArrayList<ReviewListVo> rlist=ReviewDao.getInstance().list(colnum,ch);
		req.setAttribute("vo", vo);
		req.setAttribute("list",list);
		req.setAttribute("review", review);
		req.setAttribute("rlist", rlist);
		req.setAttribute("ch", ch);
		
		
		boolean bs=ProductDao.getInstance().isSoldout(colnum);
		if(bs) req.setAttribute("soldout", "soldout");
		String att=req.getParameter("at");
		int at=0,it=0;
		String cid=req.getParameter("cid");
		if(att!=null && !att.equals("")) {
			at=Integer.parseInt(att);
			it=Integer.parseInt(req.getParameter("it"));
			req.setAttribute("at", at);
			req.setAttribute("it", it);
		}
		
		InquiryDao idao=InquiryDao.getInstance();
		String spageNumInq=req.getParameter("pageNumInq");
		int pageNumInq=1;
		if(spageNumInq!=null) pageNumInq=Integer.parseInt(spageNumInq);
		int endRowInq=pageNumInq*10;
		int startRowInq=endRowInq-9;
		int pageCountInq=(int)Math.ceil(idao.getCount(colnum)/10.);
		int startPageNumInq=(pageNumInq-1)/5*5+1;
		int endPageNumInq=startPageNumInq+4;
		if(endPageNumInq>pageCountInq) {
			endPageNumInq=pageCountInq;
		}
		ArrayList<InquiryVo> inqList=idao.getList(startRowInq, endRowInq, colnum, at, it, cid);
		req.setAttribute("pageNumInq", pageNumInq);
		req.setAttribute("startPageNumInq", startPageNumInq);
		req.setAttribute("endPageNumInq", endPageNumInq);
		req.setAttribute("pageCountInq", pageCountInq);
		if(spageNumInq!=null || att!=null) {
			req.setAttribute("inqPage", "inquiry");
		}
		
		req.setAttribute("type", type);
		req.setAttribute("cid", cid);
		req.setAttribute("inqList", inqList);
		req.setAttribute("cpage", "/kimyungi/ItemInfo.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
