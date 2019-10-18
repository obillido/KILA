<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
   *{margin:0px;padding:0px;}
   #div{clear:both;margin-top:100px;text-align:center;}
</style>

<div id="div">
<c:choose>
   <c:when test="${requestScope.code=='success' }">
      <h1>가입에 성공했습니다.</h1>
   </c:when>
   <c:otherwise>
      <h1>가입에 실패했습니다.</h1>
   </c:otherwise>
</c:choose>
</div>
