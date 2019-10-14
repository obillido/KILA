<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>

<style type="text/css">
	#wrap{
		width:100%;
	}
	#layHeader{
		width:100%; height:70px;
		margin-top:30px;
	}
</style>


</head>
<body>
<%
	String cpage=request.getParameter("cpage");
	if(cpage==null) cpage="basicContent.jsp";
%>
<div id="wrap">
	<div id="layHeader">
		<jsp:include page="/header/index.jsp"/>
	</div>
	<div style="height:20px;"></div>
	<div id="layCategory">
		<jsp:include page="/header/category.html"/>
	</div>
	<div id="layContent">
		<jsp:include page="<%=cpage %>"/>
	</div>
	<div id="layFooter">
		<jsp:include page="footer.jsp"/>
	</div>
</div>
</body>
</html>