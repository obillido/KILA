<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="content">

	<h1>BEST</h1>
	<c:set var="rank" value="1"/>
	<c:forEach var="vo" items="${list}">
		<div name="item">
			<a href="${cp}/iteminfo?colnum=${vo.colnum}">
			${rank}
			<div name="item">
				<img src="${cp}/upload/${vo.savefilename}" width="220">
				<p>${vo.pname}</p>
				<p>${vo.price}</p>
				<p>${vo.color}</p>
			</div>
			</a>
		</div>
		<c:set var="rank" value="${rank+1}"/>
	</c:forEach>


</div>

