<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.JdbcUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String id=request.getParameter("id");

Connection con=null;
PreparedStatement pstmt=null;
ResultSet rs=null;

boolean using=false; 

try{
	con=JdbcUtil.getConn();
	String sql="select * from member where id=?";
    pstmt=con.prepareStatement(sql);
	pstmt.setString(1,id);
	rs=pstmt.executeQuery();
	if(rs.next()){	   
		using=true;
	}	   
}catch(SQLException se){
	   se.printStackTrace();
}finally{
	   JdbcUtil.close(con,pstmt,rs);
}

JSONObject json=new JSONObject();
json.put("using",using);

response.setContentType("text/plain;charset=utf-8");
PrintWriter pw=response.getWriter();
pw.print(json.toString());
%>