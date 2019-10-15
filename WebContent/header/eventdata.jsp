<%@page import="java.io.PrintWriter"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
<%@page import="kila.vo.EventVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kila.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
   EventDao dao=new EventDao();
   ArrayList<EventVo> list=dao.list();
   
   JSONArray arr=new JSONArray();
   for(int i=0;i<list.size();i++){
	   EventVo vo=list.get(i);
	   JSONObject json=new JSONObject();
	   json.put("title",vo.getTitle());
	   json.put("content",vo.getContent());
	   arr.put(json);
   }
   response.setContentType("text/plain;charset=utf-8");
   PrintWriter pw=response.getWriter();
   pw.print(arr.toString());
%>



