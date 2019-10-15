package kila.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;



@WebServlet("/kila/review")
public class ReviewController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd!=null && cmd.equals("list")) {
			list(req,resp);
		}else if(cmd!=null && cmd.equals("insert")) {
			insert(req,resp);
		}else if(cmd!=null && cmd.equals("delete")) {
			delete(req,resp);
		}
	}
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num=Integer.parseInt(req.getParameter("num"));
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		CommentsDao dao=CommentsDao.getCommentsDao();
		int n=dao.delete(num);
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code", "success");
		}else {
			json.put("code", "fail");
		}
		pw.print(json);
	}
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//댓글목록을 json으로 응답하기
		int mnum=Integer.parseInt(req.getParameter("mnum"));
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		CommentsDao dao=CommentsDao.getCommentsDao();
		ArrayList<CommentsVo> list=dao.list(mnum);
		JSONArray arr=new JSONArray();
		arr.put(list);
		pw.print(arr);
		//http://localhost:8081/ajax03_comments/comments?cmd=list&mnum=1
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int mnum=Integer.parseInt(req.getParameter("mnum"));
		String id=req.getParameter("id");
		String comments=req.getParameter("comments");
		CommentsVo vo=new CommentsVo(0, mnum, id, comments);
		CommentsDao dao=CommentsDao.getCommentsDao();
		int n=dao.insert(vo);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		JSONObject json=new JSONObject();
		if(n>0) {
			json.put("code","success");
		}else {
			json.put("code","fail");
		}
		pw.print(json.toString());
	}
}
