<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result.jsp</title>
</head>
<body>
<c:choose>
   <c:when test="${requestScope.code=='success' }">
      <h1>가입에 성공했습니다.</h1>
   </c:when>
   <c:otherwise>
      <h1>가입에 실패했습니다.</h1>
   </c:otherwise>
</c:choose>
</body>
</html>