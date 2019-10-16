package kila.controller;

import java.io.IOException;
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
		PaymentVo vo=dao.getInfo(id);
		String pname=dao.getPname(vo.getPnum());
		int sts=vo.getStatus();
		String str=null;
		if(sts==1) {
			str="배송준비";
		}else(sts==2) {
			str="배송중";
		}else(sts==3){
			str="배송완료";
		}else(sts==4){
			str="구매확정";
		}else(sts==5){
			str="배송전취소";
		}else(){
			
		}
		
		
		-- 1 : 배송준비
		-- 2 : 배송중
		-- 3 : 배송완료
		-- 4 : 구매확정
		-- 5 : 배송전취소
		-- 6 : 환불요청
		-- 7 : 환불완료
		-- 8 : 장바구니
		MyPaymentVo mpv=new MyPaymentVo(pname,vo.getCnt(),vo.getPaydate(),vo.getPaymethod(),str);
		req.setAttribute("info",list);
		req.getRequestDispatcher("/header/purchased.jsp").forward(req,resp);
	}
}
