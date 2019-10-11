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

@WebServlet("/header/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/header/login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");		
		String pwd=req.getParameter("pwd");
		
		BuyerDao bd=new BuyerDao();
		int status=bd.getStatus(id);
		if(status==3) {
			req.getRequestDispatcher("/header/loginFail.jsp").forward(req,resp);
			return;
		}
		
		MemberDao dao=new MemberDao();
		boolean result=dao.isMember(id,pwd);
		if(result) { 
			HttpSession session=req.getSession(); 
			session.setAttribute("id",id);
			session.setAttribute("pwd",pwd);
			resp.sendRedirect(req.getContextPath() + "/home");
		}else { 
			req.setAttribute("errMsg","아이디 또는 비밀번호가 맞지 않습니다.");
			req.getRequestDispatcher("/header/login.jsp").forward(req,resp);
		}
	}
}
