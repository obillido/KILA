<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	#category{
		float:left;
		width:200px;
		padding:10px 30px;
		line-height:30px;
	}
	#category a{padding-left:10px;}
	#list{
		float:left;
		width:1000px;
	}
	
	a{text-decoration:none;}
	
	#category a{color:black;}
	#category a:hover{
		color:red;
		text-decoration:underline;
	}
	
	#list a{color:grey;}
	
	#list div[name=item]{
		float:left;
		padding-right:30px;
		padding-bottom:50px;
	}
	#list div[name=item] h4{
		line-height:5px;
		padding-left:10px;
		
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
