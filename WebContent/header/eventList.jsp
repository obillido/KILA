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
<table border="1" width="1000">
<tr>
   <th>제목</th>
   <th>내용</th>
   <th>파일명</th>
   <th>이미지</th>
   <th>다운로드</th>
<%
   EventDao dao=new EventDao();
   ArrayList<EventVo> list=dao.list();
   for(EventVo e:list){
%>
      <tr>
         <td><%=e.getTitle() %></td>
         <td><%=e.getContent() %></td>
         <td><%=e.getOrgfilename() %></td>
         <td><img src="${pageContext.request.contextPath }/eventUploaded/<%=e.getOrgfilename() %>" width="300" height="300"></td>
         <td><a href="">다운로드</a></td>
      </tr>	 
<%  
   }

%>
</tr>
</table>
</body>
</html>