<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #btn1{background-color:white;color:black;border:1px solid black;margin-top:10px;margin-left:310px;}
    #btn2{background-color:white;color:black;border:1px solid black;margin-top:10px;}
</style>

<div id="div">
<form method="post" action="${pageContext.request.contextPath }/header/registerEventOk.jsp" enctype="multipart/form-data">
   제목 <input type="text" name="title"><br>
   내용<br>
   <textarea rows="5" cols="50" name="content"></textarea><br>
   첨부파일 <input type="file" name="file"><br>
   <input type="submit" value="등록" id="btn1">
   <input type="reset" value="취소" id="btn2">
</form>
</div>