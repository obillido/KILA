<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<h1>상품목록</h1>

<style type="text/css">
	table{text-align:center;}
</style>

<c:forEach var="vo" items="${list}">
	<div>
		<img src="${pageContext.request.contextPath}/upload/${vo.savefilename}" width="200">
		<label for="product name">${vo.pname}</label>
		<label for="price">${vo.price}</label>
	</div>
</c:forEach>