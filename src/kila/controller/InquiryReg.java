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
		req.setAttribute("cpage", "/content/inquiryReg.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String type=(String)session.getAttribute("type");
		System.out.println(req.getParameter("colnum"));
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		int inqtype=Integer.parseInt(req.getParameter("inqtype"));
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		int ref=1;
		if(type.equals("A")) ref=2; 
		int n=InquiryDao.getInstance().insert(new InquiryVo(0, id, colnum, inqtype, title, content, ref, null));
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}else {
			resp.sendRedirect(req.getContextPath()+"/layout.jsp");
		}
		
	}
}
