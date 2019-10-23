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
import org.json.JSONObject;

import kila.dao.ProductInfoDao;
import kila.vo.FinalSearchVo;
import kila.vo.ProductInfoVo;
import kila.vo.SearchProductVo;

@WebServlet("/search")
public class SearchProductController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		String keyword=req.getParameter("keyword");
		if(cmd.equals("search")) {
			Cookie[] cookies=req.getCookies();
			if(cookies!=null) {
				for(Cookie cookie:cookies) {
					String cookieName=cookie.getName();
					String cookieValue=cookie.getValue();
					if(cookieValue.equals(keyword)) {
						Cookie ck=new Cookie(cookieName,"");
						ck.setPath("/");
						ck.setMaxAge(0);
						resp.addCookie(ck);
					}
				}
			}
			Cookie cookie=null;
			if(keyword==null) keyword="";
			if(keyword.equals("")) cookie=new Cookie("all",keyword);
			else cookie=new Cookie(keyword,keyword);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*7);
			resp.addCookie(cookie);
			ArrayList<ProductInfoVo> list=ProductInfoDao.getInstance().getList(keyword);
			DecimalFormat fmt=new DecimalFormat("###,###,###");
			req.setAttribute("fmt", fmt);
			req.setAttribute("list",list);
			req.setAttribute("keyword",keyword);
			req.setAttribute("cpage", "/content/productList/searchList.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req,resp);
		}else {
			if(cmd.equals("deleteAll")) {
				deleteAll(req,resp);
			}else if(cmd.equals("delete")){
				delete(req,resp,keyword);
			}else if(cmd.equals("deleteAlways")){
				deleteAlways(req,resp);
			}else {
				Cookie[] cookies=req.getCookies();
				ArrayList<String> slist=new ArrayList<String>();
				if(cookies!=null) {
					for(Cookie cookie:cookies) {
						String cookieValue=cookie.getValue();
						slist.add(cookieValue);
						String cookieName=cookie.getName();
						if(cookieName.equals("JSESSIONID")) {
							if(!cookie.getValue().equals("off")) {
								Cookie ck=new Cookie(cookieName,"on");
								resp.addCookie(ck);
							}
						}
					}
				}
				JSONArray arr=new JSONArray();
				arr.put(slist);
				resp.setContentType("text/plain;charset=utf-8");
				PrintWriter pw=resp.getWriter();
				pw.print(arr.toString());
			}
		}
	}
	
	public void deleteAlways(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Cookie[] cookies1=req.getCookies();
		if(cookies1!=null) {
			for(Cookie cookie:cookies1) {
				String cookieName=cookie.getName();
				if(!cookieName.equals("JSESSIONID")) {
					Cookie ck=new Cookie(cookieName,"");
					ck.setPath("/");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}else {
					Cookie ck=null;
					if(cookie.getValue().equals("on")) {
						ck=new Cookie(cookieName,"off");
					}else {
						ck=new Cookie(cookieName,"on");
					}
					resp.addCookie(ck);
				}
			}
		}
		JSONArray arr=new JSONArray();
		arr.put(new ArrayList<String>());
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
	
	public void deleteAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
		Cookie[] cookies1=req.getCookies();
		if(cookies1!=null) {
			for(Cookie cookie:cookies1) {
				String cookieName=cookie.getName();
				if(!cookieName.equals("JSESSIONID")) {
					Cookie ck=new Cookie(cookieName,"");
					ck.setPath("/");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}
			}
		}
		JSONArray arr=new JSONArray();
		arr.put(new ArrayList<String>());
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
	
	
	public void delete(HttpServletRequest req, HttpServletResponse resp, String keyword) throws ServletException, IOException  {
		ArrayList<String> slist=new ArrayList<String>();
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				String cookieName=cookie.getName();
				String cookieValue=cookie.getValue();
				if(cookieValue.equals(keyword)) {
					Cookie ck=new Cookie(cookieName,"");
					ck.setPath("/");
					ck.setMaxAge(0);
					resp.addCookie(ck);
				}else {
					slist.add(cookieValue);
				}
			}
		}
		JSONArray arr=new JSONArray();
		arr.put(slist);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());

	}
}
