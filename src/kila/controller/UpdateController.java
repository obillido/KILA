package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.MemberDao;
import kila.vo.MemberVo;

@WebServlet("/header/update")
public class UpdateController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		MemberVo vo=new MemberVo(id,pwd,null);
		MemberDao dao=new MemberDao();
		int n=dao.update(vo);
		if(n>0) {
			HttpSession session=req.getSession(); 
			session.setAttribute("id",id);
			session.setAttribute("pwd",pwd);
			resp.sendRedirect(req.getContextPath() + "/header/myinfo.jsp");
		}else {
			req.setAttribute("code","회원수정실패");
		}
	}
}
