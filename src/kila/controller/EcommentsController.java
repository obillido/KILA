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

import kila.dao.EcommentsDao;
import kila.vo.EcommentsVo;

@WebServlet("/ecomments")
public class EcommentsController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd!=null && cmd.equals("list")) {
			list(req,resp);
		}
		if(cmd!=null && cmd.equals("insert")) {
			insert(req,resp);
		}
	}
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int evnum=Integer.parseInt(req.getParameter("evnum"));
		EcommentsDao dao=EcommentsDao.getEcommentsDao();
		ArrayList<EcommentsVo> list=dao.list(evnum);
		
		JSONArray arr=new JSONArray();
		arr.put(list);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr);	 
	}
	protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	int evnum=Integer.parseInt(req.getParameter("evnum"));
		String ecid=req.getParameter("ecid");
		String ecomments=req.getParameter("ecomments");
		EcommentsVo vo=new EcommentsVo(0,evnum,ecid,ecomments);
		EcommentsDao dao=EcommentsDao.getEcommentsDao();
		int n=dao.insert(vo);
		JSONObject json=new JSONObject();
		
		if(n>0) {
			json.put("code","success");
			json.put("ecid",ecid);
			json.put("ecomments",ecomments);
		}else {
			json.put("code","fail");
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json.toString());
	}
}
