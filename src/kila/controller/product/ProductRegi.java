package kila.controller.product;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kila.dao.ProductDao;

@WebServlet("/product/productRegi")
public class ProductRegi extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		if(cmd.equals("old")) {
			req.setAttribute("cpage", "/admin/productRegiAdd.jsp");
		}else {
			req.setAttribute("cpage", "/admin/productRegi.jsp");
		}
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String saveDirectory=req.getSession().getServletContext().getRealPath("/upload");
		MultipartRequest mr=new MultipartRequest(
				req, 
				saveDirectory,
				1024*1024*20, 
				"utf-8",
				new DefaultFileRenamePolicy());
		String cname=mr.getParameter("cname");
		String pcode=mr.getParameter("pcode");
		String pname=mr.getParameter("pname");
		int price=Integer.parseInt(mr.getParameter("price"));
		String[] color=mr.getParameterValues("color");

		int clen=color.length;
		String[] orgfilename=new String[clen];
		String[] savefilename=new String[clen];
		Long[] filesize=new Long[clen];
		for(int i=0; i<clen; i++) {
			String fn="file_"+color[i];
			orgfilename[i]=mr.getOriginalFileName(fn);
			savefilename[i]=mr.getFilesystemName(fn);
			filesize[i]=mr.getFile(fn).length();
		}
		String[] size=mr.getParameterValues("size");
		int[] psize=new int[size.length];
		for(int i=0; i<size.length; i++) {
			psize[i]=Integer.parseInt(size[i]);
		}
		int cnt=Integer.parseInt(mr.getParameter("cnt"));
		
		int n=ProductDao.getInstance().insert(
				cname, pcode, pname, price, 
				color, orgfilename, savefilename, filesize, 
				psize, cnt);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/product/list");
		}else {
			req.setAttribute("msg", "등록실패");
			req.setAttribute("cpage", "/admin/productRegi.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
