package kila.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.SearchDao;
import kila.vo.FinalSearchVo;
import kila.vo.SearchProductVo;

@WebServlet("/header/search")
public class SearchProductController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String search=req.getParameter("search");
		SearchDao dao=new SearchDao();
		ArrayList<SearchProductVo> list=dao.searchProduct(search);
		
		ArrayList<FinalSearchVo> finallist=new ArrayList<FinalSearchVo>();
		for(int i=0;i<list.size();i++) {
			String pname=list.get(i).getPname();
			int colnum=list.get(i).getColnum();
			String savefilename=dao.getImage(colnum);
			FinalSearchVo vo=new FinalSearchVo(pname, savefilename, colnum);
			finallist.add(vo);
		}
		
		req.setAttribute("info",finallist);
		req.setAttribute("search",search);
		req.setAttribute("cpage", "/header/search.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
		
		Cookie cook1=new Cookie("latest",search);
		cook1.setPath("/");
		cook1.setMaxAge(60*60*24);
		resp.addCookie(cook1);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies=req.getCookies();
		if(cookies!=null) {
			for(int i=0;i<cookies.length;i++) {
				cookies[i].setMaxAge(0);
				resp.addCookie(cookies[i]);
				System.out.println(cookies[i]);
				System.out.println();
			}
		}
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
}
