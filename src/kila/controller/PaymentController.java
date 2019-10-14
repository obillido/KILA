package kila.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.SetAllPropertiesRule;

@WebServlet("/kila/payment")
public class PaymentController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int scolnum=Integer.parseInt(req.getParameter("scolnum"));
		int spsize=Integer.parseInt(req.getParameter("spsize"));
		int pcnt=Integer.parseInt(req.getParameter("pcnt"));
		
		req.setAttribute("scolnum", scolnum);
		req.setAttribute("spsize", spsize);
		req.setAttribute("pcnt", pcnt);
		req.getRequestDispatcher("/kimyungi/result.jsp").forward(req,resp);
	}
}
