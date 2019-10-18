<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #btn1{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;margin-top:10px;margin-left:160px;height:30px;width:70px;}
    #btn2{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;margin-top:10px;height:30px;width:70px;}
    input[type=text]{width:200px;height:20px;background-color:#C8FFFF;}
    input[type=password]{width:200px;height:20px;background-color:#C8FFFF;}
    span{width:100px;display:inline-block;}
</style>

<div id="div">
<form method="post" action="${pageContext.request.contextPath }/header/login">
   <span>아이디 </span><input type="text" name="id" value="${param.id }"><br>
   <span>비밀번호 </span><input type="password" name="pwd" value="${param.pwd }"><br>
   <div style="color:red;font-size:12px">${requestScope.errMsg }</div>
   <input type="submit" value="로그인" id="btn1">
   <input type="reset" value="취소" id="btn2">
</form>
</div>