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
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null) {
			resp.sendRedirect(req.getContextPath()+"/header/login");
		}else {
			int colnum=Integer.parseInt(req.getParameter("colnum"));
			String[] info=ColorDao.getInstance().getProductInfo(colnum);
			req.setAttribute("colnum", colnum);
			req.setAttribute("pname", info[0]);
			req.setAttribute("color", info[1]);
			req.setAttribute("cpage", "/content/inquiry/inquiryReg.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String type=(String)session.getAttribute("type");
		
		int colnum=Integer.parseInt(req.getParameter("colnum"));
		String content=req.getParameter("content");
		InquiryDao idao=InquiryDao.getInstance();
		if(type.equals("A")) {
			System.out.println(colnum);
			int inum=Integer.parseInt(req.getParameter("inum"));
			InquiryVo vo=idao.getInfo(inum).get(0);
			idao.insert(new InquiryVo(inum, 2, id, vo.getInum(), vo.getInqtype(), vo.getTitle(), content, null));
		}else {
			int inqtype=Integer.parseInt(req.getParameter("inqtype"));
			String title=req.getParameter("title");
			idao.insert(new InquiryVo(idao.getMaxInum()+1, 1, id, colnum, inqtype, title, content, null));
		}
		resp.sendRedirect(req.getContextPath()+"/iteminfo?colnum="+colnum);
	}
}
