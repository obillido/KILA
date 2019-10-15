<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
</style>

<div id="div">
   <ul>
      <li><a href="${pageContext.request.contextPath }/header/update">회원정보수정</a></li>
      <li><a href="${pageContext.request.contextPath }/header/withdrawal.html">회원탈퇴요청</a></li>
      <li><a href="${pageContext.request.contextPath }/header/purchased.jsp">구매내역조회</a></li>
   </ul>
</div>