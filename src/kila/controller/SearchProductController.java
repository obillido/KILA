package kila.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import kila.dao.ProductInfoDao;
import kila.vo.FinalSearchVo;
import kila.vo.ProductInfoVo;
import kila.vo.SearchProductVo;

@WebServlet("/header/search")
public class SearchProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		String search=req.getParameter("search");
		if(cmd.equals("search")) {
			Cookie[] cookies=req.getCookies();
			String cn="cookie"+cookies.length;
			Cookie cookie=new Cookie(cn,search);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);
			resp.addCookie(cookie);
			
			ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getList(search);
			DecimalFormat fmt=new DecimalFormat("###,###,###");
			req.setAttribute("fmt", fmt);
			req.setAttribute("list",list);
			req.setAttribute("search",search);
			req.setAttribute("cpage", "/header/search.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req,resp);
		}else if(cmd.equals("deleteAll")) {
			deleteAll(req,resp);
		}else{
			delete(req,resp,search);
		}
	}

	
	public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Cookie[] cookies1=req.getCookies();
		if(cookies1!=null) {
			for(Cookie cookie:cookies1) {
				String cookieName=cookie.getName();
				Cookie ck=new Cookie(cookieName,"");
				ck.setPath("/");
				ck.setMaxAge(0);
				resp.addCookie(ck);
			}
		}
		Cookie[] cookies2=req.getCookies();
		ArrayList<String> slist=new ArrayList<String>();
		if(cookies2!=null) {
			for(Cookie cookie:cookies2) {
				String cookieValue=cookie.getValue();
				slist.add(cookieValue);
			}
		}
		JSONArray arr=new JSONArray();
		arr.put(slist);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
	
	
	public void delete(HttpServletRequest req, HttpServletResponse resp, String search) throws ServletException, IOException  {
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				String cookieName=cookie.getName();
				if(cookieName.equals(search)) {
					Cookie ck=new Cookie(cookieName,"");
					ck.setPath("/");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}
			}
		}
		
		Cookie[] cookies2=req.getCookies();
		ArrayList<String> slist=new ArrayList<String>();
		if(cookies2!=null) {
			for(Cookie cookie:cookies2) {
				String cookieValue=cookie.getValue();
				slist.add(cookieValue);
			}
		}
		JSONArray arr=new JSONArray();
		arr.put(slist);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
}
