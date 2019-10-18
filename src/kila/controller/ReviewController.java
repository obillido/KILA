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
		String id=(String)session.getAttribute("id");
		String content=req.getParameter("content");
		int rpoint=Integer.parseInt(req.getParameter("rpoint"));
		int paynum=Integer.parseInt(req.getParameter("paynum"));
		String savefilename=req.getParameter("savefilenum");
		System.out.println(id);
		System.out.println(content);
		System.out.println(rpoint);
		System.out.println(paynum);
		System.out.println(savefilename);
	}
	protected void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
