<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<style type="text/css">
   *{margin:0px;padding:0px;}
   #homelogo{text-align:center;}
   #right{position:absolute;top:30px;left:1500px;}
   #right ul li{list-style:none;display:inline-block;margin-right:10px;}
   #right ul li a{text-decoration:none;}
</style>
</head>
<body>
<div id="homelogo">
   <h1>KILA 로고 이미지</h1>
</div>
<div id="right">
<ul>
   <li><a href="${pageContext.request.contextPath }/header/join">회원가입</a></li>
   <c:choose>
      <c:when test="${not empty sessionScope.id}">
         <li><a href="${pageContext.request.contextPath }/header/logout">로그아웃</a></li>
         <li><a href="${pageContext.request.contextPath }/header/mypage.jsp">마이페이지</a></li>
      </c:when>
      <c:otherwise>
         <li><a href="${pageContext.request.contextPath }/header/login">로그인</a></li>
      </c:otherwise>
   </c:choose>
   <li><a href="${pageContext.request.contextPath }/header/center">고객센터</a></li>
   <li><a href="${pageContext.request.contextPath }/header/shopping">주문배송</a></li>
</ul>
</div>
</body>
</html>