<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
   *{margin:0px;padding:0px;}
   #event{position:absolute;top:30px;left:30px;}
   #homelogo{text-align:center;}
   #right{position:absolute;top:30px;left:1500px;}
   #right ul li{list-style:none;display:inline-block;margin-right:10px;}
   #right ul li a{text-decoration:none;}
   a{color:black;}
</style>

<script type="text/javascript">
   var xhr=null;
   var word=null;
   var event=null;
   window.onload=function(){
	   word=new Array();
	   event=document.getElementById("event");
	   xhr=new XMLHttpRequest();
	   xhr.onreadystatechange=callback;
	   xhr.open('get','${pageContext.request.contextPath}/header/eventdata.jsp',true);
	   xhr.send();
   }
   function callback(){
	   if(xhr.readyState==4 && xhr.status==200){
		   var obj=JSON.parse(xhr.responseText);
		   var str="";
		   for(var i=0;i<4;i++){
			   str=obj.events[i].name;
			   word[i]=str;
		   }
	   }
   }
   var i=0;	
   var interval=setInterval(function(){	
	   if(i>3){
		   i=0;		
	   }
	   event.innerHTML=word[i];
	   i++;
	   }, 2000);
   setTimeout(function(){
	   clearInterval(interval);
	  }, 20000);
</script>


<div id="event"></div>
<div id="homelogo">
   <img src="${pageContext.request.contextPath }/header/KilaLogo.png">
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