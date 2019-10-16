<%@page import="kila.vo.EventVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kila.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 400px;}
</style>

<div id="div">
<table border="1" width="1200" style="background-color:#EBFBFF;">
<tr>
   <th>제목</th>
   <th>내용</th>
   <th>미리보기</th>
<%
   EventDao dao=new EventDao();
   ArrayList<EventVo> list=dao.list();
	   for(EventVo ev:list){
%>
      <tr>
         <td><a href="${pageContext.request.contextPath }/header/eventDetail?num=<%=ev.getEvnum()%>"><%=ev.getTitle() %></a></td>
         <td><%=ev.getContent() %></td>
         <td><img src="${pageContext.request.contextPath }/eventUploaded/<%=ev.getOrgfilename() %>" width="200" height="200"></td>
      </tr>	 
<%  
	   }
%>
</tr>
</table>
</div>
