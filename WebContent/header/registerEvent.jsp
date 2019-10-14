<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerEvent.jsp</title>
</head>
<body>
<%
   request.setCharacterEncoding("utf-8");
   String title=request.getParameter("title");
   String content=request.getParameter("content");
   

%>
<form method="post" action="registerEvent.jsp">
   제목 <input type="text" name="title"><br>
   내용<br>
   <textarea rows="5" cols="50" name="content"></textarea><br>
   첨부파일 <input type="file" name="file"><br>
   <input type="submit" value="등록">
   <input type="reset" value="취소">
</form>
</body>
</html>