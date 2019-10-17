package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.ColorDao;
import kila.dao.InquiryDao;
import kila.vo.InquiryVo;

@WebServlet("/inquiry/registration")
public class InquiryReg extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		String[] info=ColorDao.getInstance().getProductInfo(colnum);
		req.setAttribute("colnum", colnum);
		req.setAttribute("pname", info[0]);
		req.setAttribute("color", info[1]);
		req.setAttribute("cpage", "/content/inquiry/inquiryReg.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String type=(String)session.getAttribute("type");
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		int inqtype=Integer.parseInt(req.getParameter("inqtype"));
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		InquiryVo vo=new InquiryVo();
		InquiryDao idao=InquiryDao.getInstance();
		int inum=idao.getMaxInum()+1;
		int lev=1;
		if(type.equals("A")) {
			lev=2;
			inum=Integer.parseInt(req.getParameter("inum"));
		}
		int n=idao.insert(new InquiryVo(inum, lev, id, colnum, inqtype, title, content, null));
		resp.sendRedirect(req.getContextPath()+"/iteminfo?colnum="+colnum);
	}
}
