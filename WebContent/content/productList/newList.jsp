<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>


<style type="text/css">

	#newList{
		width:100%;
		text-align:center;
	}
	#newList #inner{
		display:inline-block;
		width:1200px;
		margin-top:50px;
	}

	
	.item{
		float:left;
		width:370px; height:400px;
		margin-left:20px;
		margin-top:30px;
		margin-bottom:20px;
	}
	.item a{
		text-decoration:none;
		color:black;
	}
	.item p{
		color:black;
		margin-left:20px;
		text-align:left;
	}
	.item img:hover{
		filter:brightness(50%);
	}
	.text{
		border:1px solid black;
	}
</style>


<div id="newList">
	<div id="inner">
	<h1>New</h1>
	<c:forEach var="vo" items="${list}">	
		<div class="item">
		<a href="${cp}/iteminfo?colnum=${vo.colnum}">
			<img src="${cp}/upload/${vo.savefilename}" width="350">
			<p>${vo.pname}</p>
			<p>${vo.color}</p>
		</div>
		</a>
	</c:forEach>
	</div>
</div>

    