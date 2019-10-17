<%@page import="java.sql.Date"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.SQLException"%>
<%@page import="jdbc.JdbcUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setContentType("text/xml;charset=utf-8");
	PrintWriter pw=response.getWriter();
	pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	pw.println("<result>");

	int inum=Integer.parseInt(request.getParameter("inum"));
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try{
		con=JdbcUtil.getConn();
		String sql="select * from inquiry where inum=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,inum);
		rs=pstmt.executeQuery();
		while(rs.next()){
			pw.println("<contents>");
			pw.println("<content>"+rs.getString("content")+"</content>");
			pw.println("<date>"+rs.getDate("regdate")+"<regdate>");
			pw.println("<lev>"+rs.getInt("lev")+"</lev>");
			pw.println("</contents>");
		}
	}catch(SQLException se){
		se.getStackTrace();
	}finally{
		JdbcUtil.close(con, pstmt, rs);
	}
	
	pw.println("</result>");
%>