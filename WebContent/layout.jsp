<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>

<style type="text/css">
    #wrap{width:100%;}
    
</style>

</head>
<body>
<%
	String cpage=request.getParameter("cpage");
	if(cpage==null) cpage="main.jsp";
%>
<div id="wrap">
	<jsp:include page="/header/header.jsp"/>

	<jsp:include page="<%=cpage %>"/>
   
	<jsp:include page="footer.jsp"/>
</div>

</body>
</html>