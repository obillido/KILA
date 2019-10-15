<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
</style>

<c:set var="cp" value="${pageContext.request.contextPath}/layout.jsp?cpage="/>

<div id="div">
   <ul>
      <li><a href="${pageContext.request.contextPath}/product/productRegi">상품등록</a></li>
      <li><a href="${pageContext.request.contextPath}/product/list">상품목록</a></li>
      <li><a href="">결제목록</a></li>
      <li><a href="${cp}/header/registerEvent.jsp">이벤트작성하기</a></li>
      <li><a href="${cp}/home.jsp">홈으로</a></li>
   </ul>
</div>