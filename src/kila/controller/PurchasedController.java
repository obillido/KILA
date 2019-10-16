package kila.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kila.dao.PaymentDao;
import kila.vo.MyPaymentVo;
import kila.vo.PaymentVo;

@WebServlet("/header/purchased")
public class PurchasedController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession s=req.getSession();
		String id=(String)s.getAttribute("id");
		
		PaymentDao dao=PaymentDao.getInstance();
		
		MyPaymentVo mpv=new MyPaymentVo();
		ArrayList<MyPaymentVo> list=new ArrayList<MyPaymentVo>();
		
		ArrayList<PaymentVo> al=dao.getInfo(id);
		for(int i=0;i<al.size();i++) {
			int paynum=al.get(i).getPaynum();
			String pname=dao.getPname(al.get(i).getPnum());
			int cnt=al.get(i).getCnt();
			Date paydate=al.get(i).getPaydate();
			String paymethod=al.get(i).getPaymethod();
			String str=null;
			int sts=al.get(i).getStatus();
			if(sts==1) {
				str="배송준비";
			}else if(sts==2) {
				str="배송중";
			}else if(sts==3){
				str="배송완료";
			}else if(sts==4){
				str="구매확정";
			}else if(sts==5){
				str="배송전취소";
			}else if(sts==11 || sts==12 || sts==13){
				str="환불요청";
			}else if(sts==7){
				str="환불완료";
			}else if(sts==8){
				str="장바구니";
			}
			mpv=new MyPaymentVo(paynum, pname, cnt, paydate, paymethod, str);
			list.add(mpv);
		}
		req.setAttribute("info",list);
		req.getRequestDispatcher("/header/purchased.jsp").forward(req,resp);
	}
}
