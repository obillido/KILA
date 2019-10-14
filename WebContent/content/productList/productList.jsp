<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<style type="text/css">
	#categoryList{
		float:left;
		width:200px;
		padding:10px 30px;
		line-height:30px;
	}
	#categoryList a{
		padding-left:10px;
		text-decoration:none;
		color:black;
	}
	#categoryList a:hover{
		color:red;
		text-decoration:underline;
	}
	#categoryList a:active{
		color:red;
		text-decoration:underline;
	}
	
	#productList{
		float:left;
		width:1000px;
	}
	
	#order a{
		color:grey;
		text-decoration:none;
	}
	
	#search{
		float:right;
		width:100px; height:30px;
		margin-right:50px;
		background-color:white;
	}
	
	#list div[name=item]{
		float:left;
		width:220px; height:320px;
		margin-bottom:50px;
		margin-right:30px
	}
	#list div[name=item] p{
		padding:2px 10px;
	}

</style>


<%	String category=request.getParameter("category");	%>
<c:set var="cp" value="${pageContext.request.contextPath}/product/list"/>


<div id="categoryList">
	<h1>카테고리</h1>
	<br>
<%
	System.out.println(category);
	if(category==null || category.equals("all")){
		System.out.println("in:"+category);
%>
	<a href="${cp}?category=all" style="color:red; text-decoration:underline;">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
<%
	}else if(category.equals("down_jacket")){
%>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket" style="color:red; text-decoration:underline;">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
<%
	}else if(category.equals("jacket")){
%>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket" style="color:red; text-decoration:underline;">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
<%
	}else if(category.equals("longsleeves")){
%>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves" style="color:red; text-decoration:underline;">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
<%
	}else if(category.equals("shortsleeves")){
%>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves" style="color:red; text-decoration:underline;">반팔티셔츠</a><br>
	<a href="${cp}?category=pants">바지</a><br>
<%
	}else if(category.equals("pants")){
%>
	<a href="${cp}?category=all">전체보기</a><br>
	<a href="${cp}?category=down_jacket">다운자켓</a><br>
	<a href="${cp}?category=jacket">자켓</a><br>
	<a href="${cp}?category=longsleeves">긴팔티셔츠</a><br>
	<a href="${cp}?category=shortsleeves">반팔티셔츠</a><br>
	<a href="${cp}?category=pants" style="color:red; text-decoration:underline;">바지</a><br>
<%
	}
%>
</div>


<div id="productList">
	<h1>상품목록</h1>
	<br>
	<div id="order">
		<a href="${cp}?category=<%=category%>&order=1">판매순</a>
		<a href="${cp}?category=<%=category%>&order=2">신상품순</a>
		<a href="${cp}?category=<%=category%>&order=3">낮은가격순</a>
		<a href="${cp}?category=<%=category%>&order=4">높은가격순</a>
	</div>
	
	<input type="button" id="search" value="검색조건" onclick="showSearchWindow()">	
	<div id="searchWindow"></div>
	<br><br>
	
	<div id="list">
		<c:choose>
		<c:when test="${not empty list}">
			<c:forEach var="vo" items="${list}">
				<a href="${pageContext.request.contextPath}/iteminfo?colnum=${vo.colnum}">
				<div name="item">
					<img src="${pageContext.request.contextPath}/upload/${vo.savefilename}" width="220">
					<p>${vo.pname}</p>
					<p>${vo.color}</p>
					<p>${vo.price}</p>
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

<br><br>
<c:if test="${startPageNum!=1}">
	<a href="${pageContext.request.contextPath}/product/list?pageNum=${startPageNum-1}&order=${order}"></a>
</c:if>













<script type="text/javascript">
	var xhr=null;
	function showSearchWindow(){
		
	}
	function success(){
		
	}
	
</script>