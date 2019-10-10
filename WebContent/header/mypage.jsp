<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage.jsp</title>
</head>
<body>
<div>
   <ul>
      <li><a href="${pageContext.request.contextPath }/header/myinfo.jsp">회원정보보기</a></li>
      <li><a href="${pageContext.request.contextPath }/header/purchased.jsp">구매내역조회</a></li>
   </ul>
</div>
</body>
</html>