<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   


<h1>상품목록</h1>

<style type="text/css">
	table{text-align:center;}
</style>

<table border="1" width="1000">
	<tr>
		<th>상품코드</th>
		<th>카테고리</th>
		<th>상품명</th>
		<th>가격</th>
		<th>색상</th>
		<th>이미지</th>
		<th>사이즈</th>
		<th>재고수량</th>
	</tr>
	<c:forEach var="vo" items="${list}">
	<tr>
		<td>${vo.pcode }</td>
		<td>${vo.cname }</td>
		<td>${vo.pname }</td>
		<td>${vo.price }</td>
		<td>${vo.color }</td>
		<td><img src="${pageContext.request.contextPath}/upload/${vo.savefilename }" width="200"></td>
		<td>${vo.psize }</td>
		<td>${vo.icnt }</td>
	</tr>
	</c:forEach>
</table>
