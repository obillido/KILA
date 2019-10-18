package kila.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/kila/review")
public class ReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession(); 
		String cmd=req.getParameter("cmd");
		String id=(String)session.getAttribute("id");
		String colnum=req.getParameter("colnum");
		if(cmd!=null && cmd.equals("check")) {
			check(req,resp);
		}else if(cmd.equals("insert")) {
			
		}
		String content=req.getParameter("content");
		int rpoint=Integer.parseInt(req.getParameter("rpoint"));
		System.out.println(content);
		System.out.println(rpoint);
	}
	protected void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
