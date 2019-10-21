package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kila.dao.ReviewDao;
import kila.vo.ReviewVo;

@WebServlet("/kila/reviewReg")
public class ReviewRegController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String ReviewDirectory="C:\\Users\\JHTA\\git\\KILA\\WebContent\\upload";
		System.out.println(ReviewDirectory);
		MultipartRequest mr=new MultipartRequest(
				req, 
				ReviewDirectory,
				1024*1024*20, 
				"utf-8",
				new DefaultFileRenamePolicy());
		HttpSession session=req.getSession(); 
		String id=(String)session.getAttribute("id");
		int colnum=Integer.parseInt(mr.getParameter("colnum"));
		String content=mr.getParameter("content");
		int rpoint=Integer.parseInt(mr.getParameter("rpoint"));
		int paynum=Integer.parseInt(mr.getParameter("paynum"));
		String savefilename=mr.getFilesystemName("file1");
		System.out.println(id);
		System.out.println(content);
		System.out.println(rpoint);
		System.out.println(paynum);
		System.out.println(savefilename);
		ReviewVo vo=new ReviewVo(0, paynum, content, rpoint, null, savefilename);
		ReviewDao dao=ReviewDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			int a=dao.changeyo(paynum);
			if(n>0) {
				resp.sendRedirect(req.getContextPath()+"/iteminfo?colnum="+colnum);
			}else {
			resp.sendRedirect(req.getContextPath()+"layout.jsp");
			}
		}else {
			resp.sendRedirect(req.getContextPath()+"layout.jsp");
		}
	}
}
