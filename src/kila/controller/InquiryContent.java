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

import kila.dao.InquiryDao;
import kila.vo.InquiryVo;

@WebServlet("/productInfo/inquiry/content")
public class InquiryContent extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int inum=Integer.parseInt(req.getParameter("inum"));
		ArrayList<InquiryVo> list=InquiryDao.getInstance().getInfo(inum);
		JSONArray arr=new JSONArray();
		arr.put(list);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr.toString());
	}
}
