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
<script type="text/javascript">
function bigger(e){
    var option="width=1000, height=800, top=300, left=500, location=no";
    window.open("${pageContext.request.contextPath }/header/eventDetail.html","event",option);    
}
</script>
</head>
<body>
<table border="1" width="1700">
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
         <td><%=ev.getTitle() %></td>
         <td><%=ev.getContent() %></td>
         <td><img src="${pageContext.request.contextPath }/eventUploaded/<%=ev.getOrgfilename() %>" width="400" height="400" onclick="bigger(event)""></td>
      </tr>	 
<%  
	   }
%>
</tr>
</table>
<a href="${pageContext.request.contextPath }/home">홈으로</a>
</body>
</html>