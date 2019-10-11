<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>

<style type="text/css">
	
</style>


</head>
<body>
<%
	String cpage=request.getParameter("cpage");
	if(cpage==null) cpage="basicContent.jsp";
%>
<div id="wrap">
	<div id="header">
		<jsp:include page="/header/index.jsp"/>
	</div>
	<div id="category">
		<jsp:include page="/header/category.html"/>
	</div>
	<div id="content">
		<jsp:include page="<%=cpage %>"/>
	</div>
	<div id="footer">
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
</html>