package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.BuyerDao;
import kila.dao.MemberDao;
import kila.vo.BuyerVo;
import kila.vo.MemberVo;

@WebServlet("/header/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession s=req.getSession();
		String id=(String)s.getAttribute("id");
		BuyerDao dao=new BuyerDao();
		BuyerVo vo=dao.getInfo(id);
		req.setAttribute("info",vo);
		req.setAttribute("cpage", "/header/update.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String phone=req.getParameter("phone");
		String addr=req.getParameter("addr");
		String email=req.getParameter("email");
		String bname=req.getParameter("bname");
		MemberVo vo=new MemberVo(id,pwd,null);
		MemberDao dao=new MemberDao();
		int n=dao.update(vo);
		BuyerVo vo2=new BuyerVo(id,phone,addr,email,null,0,0,bname);
		BuyerDao dao2=new BuyerDao();
		int n2=dao2.update(vo2);
		if(n>0 && n2>0) {
			req.setAttribute("code","회원수정성공");
			req.setAttribute("cpage", "/header/updateSuccess.jsp");
		}else {
			req.setAttribute("code","회원수정실패");
			req.setAttribute("cpage", "/header/updateFail.jsp");
		}
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
