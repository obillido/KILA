<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>


<style type="text/css">

	#bestList{
		width:100%;
		text-align:center;
	}
	#bestList #inner{
		display:inline-block;
		width:1250px;
		margin-top:50px;
	}
	
	#bestCategory{
		margin-top:30px;
	}
	#bestCategory label{
		display:inline-block;
		width:100px;
	}
	
	.bestCategory{
		color:grey;
		text-decoration:none;
		padding-left:20px;
	}
	.bestCategory.active{
		color:red;
		text-decoration:underline;
		text-underline-position:under;
	}
	
	
	
	.item{
		float:left;
		width:220px; height:350px;
		margin-left:20px;
		margin-top:30px;
		margin-bottom:20px;
	}
	.item .rank{
		font-weight:bold;
		font-size:20px;
	}
	.item a{
		text-decoration:none;
		color:black;
	}
	.item p{
		padding:2px 10px;
		color:black;
		text-align:left;
	}

</style>


<div id="bestList">
	<div id="inner">
	<h1>BEST</h1>
	<div id="bestCategory">
		<label for="period">상품별</label>
		<a href="${cp}/product/best?category=all"  class="bestCategory <c:if test="${category eq 'all'}">active</c:if>">전체</a>
		<a href="${cp}/product/best?category=down_jacket" class="bestCategory <c:if test="${category eq 'down_jacket'}">active</c:if>">다운자켓</a>
		<a href="${cp}/product/best?category=jacket" class="bestCategory <c:if test="${category eq 'jacket'}">active</c:if>">자켓</a>
		<a href="${cp}/product/best?category=longsleeves" class="bestCategory <c:if test="${category eq 'longsleeves'}">active</c:if>">긴팔티셔츠</a>
		<a href="${cp}/product/best?category=shortsleeves" class="bestCategory <c:if test="${category eq 'shortsleeves'}">active</c:if>">반팔티셔츠</a>
		<a href="${cp}/product/best?category=pants" class="bestCategory <c:if test="${category eq 'pants'}">active</c:if>">바지</a>
	</div>
	<c:set var="rank" value="01"/>
	<c:forEach var="vo" items="${list}">	
		<div class="item">
		<a href="${cp}/iteminfo?colnum=${vo.colnum}">
			<p class="rank"><fmt:formatNumber value="${rank}"  pattern="00"/></p>
			<img src="${cp}/upload/${vo.savefilename}" width="220">
			<p>${vo.pname}</p>
			<p>${vo.price}</p>
			<p>${vo.color}</p>
		</div>
		</a>
		<c:set var="rank" value="${rank+1}"/>
	</c:forEach>

	</div>
</div>

