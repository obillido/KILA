package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.BuyerDao;
import kila.dao.MemberDao;
import kila.vo.BuyerVo;
import kila.vo.MemberVo;

@WebServlet("/header/join")
public class JoinController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/layout.jsp?cpage=/header/join.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String phone=req.getParameter("phone");
		String addr=req.getParameter("addr");
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		MemberVo vo=new MemberVo(id,pwd,"B");
		MemberDao dao=new MemberDao();
		int n=dao.insert(vo);
		BuyerVo vo2=new BuyerVo(id,phone,addr, email,"Welcome",1,0,name);
		BuyerDao dao2=new BuyerDao();
		int n2=dao2.insert(vo2);
		if(n>0 && n2>0) {
			req.setAttribute("code","success");
		}else {
			req.setAttribute("code","fail");
		}
		req.getRequestDispatcher("/header/result.jsp").forward(req,resp);
	}
}
