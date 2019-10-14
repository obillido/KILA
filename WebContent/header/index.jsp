<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
   *{margin:0px;padding:0px;}
   #event{float:left; margin:20px 0px 0px 30px; width:350px; height:20px; color:#B9062F; background-color:#FAFAA0; text-align:center;text-decoration:overline;text-shadow:black;}
   #homelogo{float:left; margin:20px 0px 0px 80px; width:50%; text-align:center;}
   #homelogo img{width:150px;}
   #right{float:right; margin-top:50px; margin-right:50px; text-align:right;}
  
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
		   event.innerHTML=word[0];
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


<c:set var="cp" value="${pageContext.request.contextPath}/layout.jsp?cpage="/>

<div id="event"></div>
<div id="homelogo">
   <img src="${pageContext.request.contextPath}/header/KilaLogo.png">
</div>
<div id="right">
<ul>
   <li><a href="${cp}/header/join">회원가입</a></li>
   <c:choose>
      <c:when test="${not empty sessionScope.id}">
         <li><a href="${cp}/header/logout">로그아웃</a></li>
         <c:choose>
            <c:when test="${sessionScope.id=='admin'}">
               <li><a href="${cp}/header/adminpage.jsp">관리자페이지</a></li>
            </c:when>
            <c:otherwise>
               <li><a href="${cp}/header/mypage.jsp">마이페이지</a></li>
            </c:otherwise>
         </c:choose>
      </c:when>
      <c:otherwise>
         <li><a href="${cp}/header/login">로그인</a></li>
      </c:otherwise>
   </c:choose>
   <li><a href="${cp}/header/center">고객센터</a></li>
   <li><a href="${cp}/header/shopping">주문배송</a></li>
</ul>
</div>