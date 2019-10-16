<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%request.setCharacterEncoding("utf-8"); %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.colnum }
${param.cnt }
${id }
${param.paymethod }
<c:forEach var="li" items="${list }">
	${li.pcode }
</c:forEach>
</body>
</html>