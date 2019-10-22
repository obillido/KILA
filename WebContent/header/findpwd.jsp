<%@page import="java.io.PrintWriter"%>
<%@page import="kila.dao.BuyerDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");
String phone=request.getParameter("phone");
BuyerDao dao=new BuyerDao();
String pwd=dao.findPwd(id,phone);

response.setContentType("text/xml;charset=utf-8");
PrintWriter pw=response.getWriter();
pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
pw.println("<result>");
if(pwd==null){
	   pw.println("<code>fail</code>");
}else{
	   pw.println("<code>success</code>");
	   pw.println("<pwd>" + pwd + "</pwd>");
}
pw.println("</result>");
%>