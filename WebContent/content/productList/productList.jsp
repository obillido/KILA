<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

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


<c:set var="cp" value="${pageContext.request.contextPath}/product/list"/>

<div id="categoryList">
	<h1>카테고리</h1>
	<br>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
</div>


<div id="productList">
	<div id="order">
		<h1>상품목록</h1>
		<%
			String category=request.getParameter("category");
		%>
		<a href="${cp}?category=<%=category%>&order=1">판매순</a>
		<a href="${cp}?category=<%=category%>&order=2">신상품순</a>
		<a href="${cp}?category=<%=category%>&order=3">낮은가격순</a>
		<a href="${cp}?category=<%=category%>&order=4">높은가격순</a>
	</div>
	
	<div id="search"></div>
	<br><br>
	
	<div id="list">
		<c:choose>
		<c:when test="${not empty list}">
			<c:forEach var="vo" items="${list}">
				<a href="${pageContext.request.contextPath}/iteminfo?colnum=${vo.colnum}">
				<div name="item">
					<img src="${pageContext.request.contextPath}/upload/${vo.savefilename}" width="220">
					<h4>${vo.pname}</h4>
					<h4>${vo.color}</h4>
					<h4>${vo.price}</h4>
				</div>
				</a>
			</c:forEach>
			<div style="clear: both;"></div>
		</c:when>
		<c:otherwise>
			<h3>등록된 제품이 없습니다.</h3>
		</c:otherwise>
		</c:choose>
	</div>
</div>
