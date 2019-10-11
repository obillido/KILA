<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center;">
<div style="text-align: center; width: 500px;height: 500px; display: inline-block;">
<h1>기본 상품정보페이지</h1><br>
<div style="width: 650px;height: 500px;">
<div style="width: 200px;height: 200px; float: left;">
<img id="img1" src="/KILA/images/DJ_FS2DJB4003X_OWH.PNG" style="width: 300px;height: 350px" onmouseover="imageZoom('img1', 'zoom')">
</div>
<div id="zoom" style="float: right; width: 300px;height: 300px; text-align: left;">
	<b>KILA|${vo.pcode }</b><br><hr><br>
   <h2>${vo.pname }</h2><br><hr><br>
   가격:<span style='color:red'>${vo.price }</span><br><br><hr><br>
   색상:${vo.color }<br><br><hr><br>
   사이즈: <c:forEach var="si" items="${list }">
   		<a href="https://www.naver.com/"> ${si.psize} </a>
   			</c:forEach>
   		<br><br><hr><br>
   <input type="button" value="CART" style="width: 145px; height: 40px">
   <input type="submit" value="BUY" style="width: 145px; height: 40px">
</div>
</div>
</div>
</div>