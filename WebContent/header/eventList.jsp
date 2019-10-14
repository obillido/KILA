<%@page import="kila.vo.EventVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kila.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>eventList.jsp</title>
</head>
<body>
<table border="1" width="1700">
<tr>
   <th>제목</th>
   <th>내용</th>
   <th>파일명</th>
<%
   EventDao dao=new EventDao();
   ArrayList<EventVo> list=dao.list();
   if(list==null){
	   System.out.println("aaa");
   }else{
	   for(EventVo ev:list){
%>
      <tr>
         <td><%=ev.getTitle() %></td>
         <td><%=ev.getContent() %></td>
         <td><%=ev.getOrgfilename() %></td>
      </tr>	 
<%  
	   }
   }
%>
</tr>
</table>
</body>
</html>