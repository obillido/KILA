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
import kila.dao.RankDao;
import kila.vo.MyPaymentVo;
import kila.vo.PaymentVo;
import kila.vo.SetRankVo;

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
		
		//등급 설정
		RankDao rdao=new RankDao();
		ArrayList<SetRankVo> rlist=rdao.getInfo(id);
		int cnt=0;
		int price=0;
		int cntByprice=0;
		int tot=0;
		for(int i=0;i<rlist.size();i++) {
			int pnum=rlist.get(i).getPnum();
			cnt=rlist.get(i).getCnt();
			price=dao.getPrice(pnum);
			cntByprice=cnt*price;
			tot+=cntByprice;
		}
		String rank=null;
		if(tot>=0 && tot<200000) {
			rank="Welcome";
		}else if(tot>=200000 && tot<500000) {
			rank="Silver";
		}else if(tot>=500000 && tot<1000000) {
			rank="Gold";
		}else if(tot>=1000000 && tot<2000000) {
			rank="VIP";
		}else if(tot>=2000000 && tot<100000000) {
			rank="VVIP";
		}
		
		req.setAttribute("tot",tot);
		req.setAttribute("info",list);
		req.setAttribute("rank",rank);	
		req.setAttribute("cpage", "/header/purchased.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
}
