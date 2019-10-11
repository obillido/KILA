<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   Connection con=null;
   PreparedStatement pstmt=null;
   
   HttpSession s=request.getSession();
   String id=(String)s.getAttribute("id");
   int n=0;
   String str="";
   
   try {
		con=JdbcUtil.getConn();
		String sql="update buyer set status=3 where cid=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		n=pstmt.executeUpdate();
		if(n>0){
			str="success";
		}
	}catch(SQLException se) {
		System.out.println(se.getMessage());
	}finally {
		JdbcUtil.close(con,pstmt,null);
	}
   
   JSONObject json=new JSONObject();
   json.put("str",str);
   
   response.setContentType("text/plain;charset=utf-8");
   PrintWriter pw=response.getWriter();
   pw.print(json.toString());
%>