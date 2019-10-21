package kila.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kila.dao.ItemInfoDao;
import kila.dao.ProductDao;
import kila.dao.ReviewDao;
import kila.vo.ItemInfoSizeVo;
import kila.vo.ItemInfoVo;
import kila.vo.ReviewIndexVo;

@WebServlet("/product/productRegi")
public class ProductRegi extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd=req.getParameter("cmd");
		if(cmd.equals("old")) {
			int colnum=Integer.parseInt(req.getParameter("colnum"));
			ItemInfoVo vo=ItemInfoDao.getInstance().productInfos(colnum);
			req.setAttribute("vo", vo);
			req.setAttribute("cpage", "/admin/productRegiAdd.jsp");
		}else {
			req.setAttribute("cpage", "/admin/productRegi.jsp");
		}
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd.equals("old")) {
			int colnum=Integer.parseInt(req.getParameter("colnum"));
			String[] size=req.getParameterValues("size");
			int cnt=Integer.parseInt(req.getParameter("cnt"));
			int nn=0;
			for(int i=0; i<size.length; i++) {
				System.out.println((i+1)+":"+colnum+","+Integer.parseInt(size[i])+","+cnt);
				int n=ProductDao.getInstance().update(colnum, Integer.parseInt(size[i]), cnt);
				if(n<=0) nn++;
			}
			if(nn<=0) {
				resp.sendRedirect(req.getContextPath()+"/iteminfo?colnum="+colnum);
			}else {
				resp.sendRedirect(req.getContextPath()+"/layout.jsp");
			}
		}else {
			String saveDirectory=req.getSession().getServletContext().getRealPath("/upload");
			System.out.println(saveDirectory);
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
}
