<%@page import="test.vo.FileinfoVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="test.dao.FileinfoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filelist.jsp</title>
</head>
<body>
<%-- 모든 파일번호,작성자,제목,원본파일명,파일크기를 출력되도록 해보세요.  --%>
<h1>파일목록</h1>
<table border="1" width="700">
	<tr>
		<th>파일번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>원본파일명</th>
		<th>이미지</th>
		<th>파일크기</th>
	</tr>
	<%
		FileinfoDao dao=new FileinfoDao();
		ArrayList<FileinfoVo> list=dao.list();
		for(FileinfoVo vo:list){
	%>
		<tr>
			<td><%=vo.getFilenum() %></td>
			<td><%=vo.getWriter() %></td>
			<td><%=vo.getTitle() %></td>
			<td><%=vo.getOrgfilename() %></td>
			<td><img src="upload/<%=vo.getOrgfilename() %>"
				 width="80" height="100"></td>
			<td><%=vo.getFilesize() %> bytes</td>
		</tr>
	<%
		}
	%>
</table>
</body>
</html>











