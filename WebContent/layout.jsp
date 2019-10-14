<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #header1{width:100%;height:15%;position:absolute;top:0px;left:0x;float:left;}
    #header2{width:100%;height:10%;position:absolute;top:150x;left:0px;float:left;}
    #content{width:100%;height:70%;position:absolute;top:150px;left:0px;float:left;}
    #footer{width:100%;height:5%;position:absolute;top:150px;left:0px;float:left;}
</style>

</head>
<body>
<%
	String cpage=request.getParameter("cpage");
	if(cpage==null) cpage="home.jsp";
%>
<div id="wrap">
	<div id="header1">
		<jsp:include page="/header/index.jsp"/>
	</div>
	<div id="header2">
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