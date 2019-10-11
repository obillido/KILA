<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center; width: 500px;height: 500px">
<h1>기본 상품정보페이지</h1>
<div style="width: 500px;height: 500px;">
<div style="width: 300px;height: 300px; display: inline-block; float: left;">
<img src="/KILA/images/DJ_FS2DJB4003X_OWH.PNG" style="width: 300px;height: 300px">
</div>
<div style="display: inline-block; float: right; width: 300px;height: 300px;">
   <b>KILA|${vo.pcode }</b>
   <h2>${vo.pname }</h2>
   가격:<span style='color:red'>${vo.price }</span><br>
   색상:${vo.color }<br>
   사이즈: <c:forEach var="si" items="${list }">
   		<a href="https://www.naver.com/"> ${si.psize} </a>
   			</c:forEach>
</div>
</div>
</div>