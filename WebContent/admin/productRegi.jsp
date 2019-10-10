<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>
</head>
<body>
<h1>상품등록</h1>
<form method="post" action="${pageContext.request.contextPath}/product/productRegi.jsp">
	<label for="category">카테고리</label>
	<label for="product name">상품명</label>
	<label for="color">색상</label>
	<label for="size">사이즈</label>
	<label for="count">수량</label>
</form>
</body>
</html>