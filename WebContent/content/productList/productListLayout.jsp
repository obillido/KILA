<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<style type="text/css">
	#category{
		float:left;
		width:300px;
		padding:10px 30px;
		line-height:30px;
	}
	#category a{padding-left:10px;}
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
	
	#list div[name=item]{float:left;}
	#list div[name=info]{
		float:left;
		padding-left:20px;
		line-height:30px;
	}
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