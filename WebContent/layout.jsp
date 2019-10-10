<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>
</head>
<body>
<%
	String hpage=request.getParameter("hpage");
	String cpage=request.getParameter("cpage");
	if(hpage==null) hpage="basicHeader.jsp";
	if(cpage==null) cpage="basicContent.jsp";
%>
<div id="wrap">
	<div id="header">
		<jsp:include page="<%=hpage %>"/>
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