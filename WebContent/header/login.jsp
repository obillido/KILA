<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #btn1{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;margin-top:10px;margin-left:160px;height:30px;width:70px;}
    #btn2{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;margin-top:10px;height:30px;width:70px;}
    #btn3{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;margin-top:10px;height:30px;width:100px;}
    input[type=text]{width:200px;height:20px;background-color:#C8FFFF;}
    input[type=password]{width:200px;height:20px;background-color:#C8FFFF;}
    span{width:100px;display:inline-block;}
</style>

<script type="text/javascript">
var xhr=null;
function findPwd(){
	   var id=document.getElementsByName("id")[0].value;
	   var phone=document.getElementsByName("phone")[0].value;
	   xhr=new XMLHttpRequest();
	   xhr.onreadystatechange=success;
	   xhr.open('get','findpwd.jsp?id=' + id + '&phone=' + phone,true);
	   xhr.send();
}
function success(){
	   if(xhr.readyState==4){
		   if(xhr.status==404){
			   alert("서버페이지를 찾지 못 했습니다");
		   }else if(xhr.status==200){
			   var data=xhr.responseXML; 
		       var code=data.getElementsByTagName("code")[0].firstChild.nodeValue;
		       if(code=='success'){
		    	   var pwd=data.getElementsByTagName("pwd")[0].firstChild.nodeValue;
		    	   document.getElementById("result").innerHTML="비밀번호:" + pwd;
		       }else{
		    	   document.getElementById("result").innerHTML="조회된 정보가 없습니다";
		       }
		       
		   }
	   }
}
</script>

<div id="div">
<form method="post" action="${pageContext.request.contextPath }/header/login">
   <span>아이디 </span><input type="text" name="id" value="${param.id }"><br>
   <span>비밀번호 </span><input type="password" name="pwd" value="${param.pwd }"><br>
   <div style="color:red;font-size:12px">${requestScope.errMsg }</div><br>
   <input type="submit" value="로그인" id="btn1">
   <input type="reset" value="취소" id="btn2">
   <br><br><br><br>
   <div style="color:blue;font-size:12px">회원가입시 입력한 전화번호를 입력하세요.</div>
   <input type="text" name="phone">
   <input type="button" value="비밀번호 찾기" onclick="findPwd()" id="btn3">
   <div style="color:blue;font-size:12px" id="result"></div>
</form>
</div>