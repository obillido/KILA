package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.EventDao;
import kila.vo.EventVo;

@WebServlet("/header/eventDetail")
public class EventDetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num=Integer.parseInt(req.getParameter("num"));
		EventDao dao=new EventDao();
		EventVo vo=dao.detail(num);
		req.setAttribute("vo",vo);
		req.getRequestDispatcher("/header/eventDetail.jsp").forward(req,resp);
	}
}
