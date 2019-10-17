<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #div li{width:150px;height:25px;margin-bottom:20px;list-style:none;}
    #div li a{font-size:1.2em;font-family:Verdana,"돋움";border:1px solid black;text-decoration:none;color:black;}
    #h{margin-bottom:20px;}
</style>

<c:set var="cp" value="${pageContext.request.contextPath}"/>

<div id="div">
   <h1 id="h">Admin Page</h1>
   <ul>
      <li><a href="${cp}/product/productRegi">상품등록</a></li>
      <li><a href="${cp}/product/list">상품목록</a></li>
      <li><a href="${cp}/header/registerEvent">이벤트작성하기</a></li>
      <li><a href="${cp}/header/refundList">환불요청목록</a></li>
      <li><a href="${cp}/header/salesRevenue">매출보기</a></li>
      <li><a href="${cp}/home">홈으로</a></li>
   </ul>
</div>