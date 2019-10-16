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

<div id="div">
   <h1 id="h">My Page</h1>
   <ul>
      <li><a href="${pageContext.request.contextPath }/header/update">회원정보수정</a></li>
      <li><a href="${pageContext.request.contextPath }/header/beforeWithdrawal.jsp">회원탈퇴요청</a></li>
      <li><a href="${pageContext.request.contextPath }/header/purchased">구매내역조회</a></li>
   </ul>
</div>