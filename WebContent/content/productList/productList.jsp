<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<h1>상품목록</h1>
<%
	String category=request.getParameter("category");
%>
<a href="${cp}?category=<%=category%>&order=1">판매순</a>
<a href="${cp}?category=<%=category%>&order=2">신상품순</a>
<a href="${cp}?category=<%=category%>&order=3">낮은가격순</a>
<a href="${cp}?category=<%=category%>&order=4">높은가격순</a>

<div id="search"></div>

<br><br>


<c:choose>
<c:when test="${not empty list}">
	<c:forEach var="vo" items="${list}">
		<div name="item">
			<img src="${pageContext.request.contextPath}/upload/${vo.savefilename}" width="200">
			<h4>${vo.pname}</h4>
			<h4>${vo.color}</h4>
			<h4>${vo.price}</h4>
		</div>		
		
	</c:forEach>
	<div style="clear: both;"></div>
</c:when>
<c:otherwise>
	<h3>등록된 제품이 없습니다.</h3>
</c:otherwise>
</c:choose>