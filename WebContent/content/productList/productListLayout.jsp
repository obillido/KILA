<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	String category=request.getParameter("category");
%>

<div>
	<div>
		<jsp:include page="category.jsp"/>	
	</div>
	<div>
		<jsp:include page="product/productList?category=<%=category %>"/>
	</div>
</div>

</body>
</html>