<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #btn1{background-color:white;color:black;border:1px solid black;margin-top:10px;margin-left:170px;}
    #btn2{background-color:white;color:black;border:1px solid black;margin-top:10px;}
</style>

<div id="div">
<form method="post" action="${pageContext.request.contextPath }/header/login">
   아이디 <input type="text" name="id" value="${param.id }"><br>
   비밀번호 <input type="password" name="pwd" value="${param.pwd }"><br>
   <div style="color:red;font-size:12px">${requestScope.errMsg }</div>
   <input type="submit" value="로그인" id="btn1">
   <input type="reset" value="취소" id="btn2">
</form>
</div>