<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:set var="cp" value="${pageContext.request.contextPath}/product/list"/>

<style type="text/css">
	#category{
		float:left;
		width:200px;
		padding-left:10px;
		line-height:30px;
	}
	#list{
		float:left;
		width:600px;
	}
	
	a{text-decoration:none;}
	
	#category a{color:black;}
	#category a:hover{
		color:red;
		text-decoration:underline;
	}
	
	#list a{color:grey;}
	
	#list div[name=item]{flost:left;}
	#list div[name=info]{float:left; color:red;}
</style>

<div>
	<div id="category">
		<jsp:include page="category.jsp"/>	
	</div>
	<div id="list">
		<jsp:include page="productList.jsp"/>
	</div>
</div>

</body>
</html>