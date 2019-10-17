package kila.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kila.dao.PaymentDao;
import kila.vo.PaymentVo;

@WebServlet("/header/salesRevenue")
public class SalesRevenueController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PaymentDao dao=PaymentDao.getInstance();
		
		//총 매출 보기
		ArrayList<PaymentVo> list=dao.getAll();
		int sum=0;
		int tot=0;
		for(int i=0;i<list.size();i++) {
			int paynum=list.get(i).getPaynum();
			String bid=list.get(i).getBid();
			int pnum=list.get(i).getPnum();
			int cnt=list.get(i).getCnt();
			Date paydate=list.get(i).getPaydate();
			int status=list.get(i).getStatus();
			String paymethod=list.get(i).getPaymethod();
		
		    int price=dao.getPrice(pnum);
		    sum=price*cnt;
		    tot+=sum;	
		}
		req.setAttribute("tot",tot);
		req.setAttribute("cpage", "/header/salesRevenue.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PaymentDao dao=PaymentDao.getInstance();
		
		//기간별 매출 보기
		String startdate=req.getParameter("startdate");
	    String enddate=req.getParameter("enddate");
	    ArrayList<PaymentVo> list2=dao.getPeriodRev(startdate,enddate);
	    int sum2=0;
		int tot2=0;
	    
		for(int i=0;i<list2.size();i++) {			
			int paynum=list2.get(i).getPaynum();
			String bid=list2.get(i).getBid();
			int pnum=list2.get(i).getPnum();
			int cnt=list2.get(i).getCnt();
			Date paydate=list2.get(i).getPaydate();
			int status=list2.get(i).getStatus();
			String paymethod=list2.get(i).getPaymethod();
				
			int price=dao.getPrice(pnum);
			sum2=price*cnt;
			tot2+=sum2;	
		}
		
    	req.setAttribute("tot2",tot2);
		req.setAttribute("cpage", "/header/salesRevenue.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	    	   		
	}
}
