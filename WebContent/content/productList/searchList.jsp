<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<style type="text/css">
	*{margin:0px;padding:0px;}
	#searchList{
		width:100%;
		text-align:center;
		margin-top:70px;
		border:1px solid black;
	}
	#searchList #inner{
		display:inline-block;
		width:1100px;
	}
	#searchList h1{
		margin-top:15px;
		text-align:center;
		height:60px;
	}
	#searchList .item{
		float:left;
		width:250px; height:350px;
		margin-left:20px;
		margin-top:30px;
		margin-bottom:20px;
	}
	#searchList .item p{
		color:black;
		text-align:center;
	}
</style>


${list.size()}
<div id="searchList">
	<div id="inner">
		<hr> <h1>"${keyword}" 검색결과</h1> <hr>
		<c:forEach var="vo" items="${list}">
			<div class="item">
				<a href="${cp}/iteminfo?colnum=${vo.colnum}">
					<img src="${cp }/upload/${vo.savefilename}" width="250">
				</a>
				<p>${fmt.format(vo.price)}</p>
				<p>${vo.pname}</p>
			</div>
		</c:forEach>
	</div>
</div>