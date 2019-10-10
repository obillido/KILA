<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/header/join">
   아이디 <input type="text" name="id"><br>
   비밀번호 <input type="password" name="pwd"><br>
   타입 <input type="text" name="type"><br>
   <input type="submit" value="회원가입">
</form>
</body>
</html>