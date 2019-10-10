<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myinfo.jsp</title>
</head>
<body>
<table border="1" width="600">
   <tr>
      <th>아이디</th>
      <th>비밀번호</th>
      <th>비밀번호수정</th>
      <th>회원탈퇴</th>
   </tr>
   <tr>
      <td>${sessionScope.id}</td>
      <td>${sessionScope.pwd}</td>
      <td><a href="${pageContext.request.contextPath }/header/update.jsp">수정하기</a></td>
      <td><a href="">탈퇴하기</a></td>
   </tr>
</table>
</body>
</html>