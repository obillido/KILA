<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
	*{margin:0px;padding:0px;}
	#homelogo{
		width:100%;
		height:100px;
		position:relative;
		text-align:center;
		min-width:1000px;
	}
	#event{
		float:left;
		margin-left:20px;
		margin-top:15px;
		width:350px; height:20px;
	}
	#homelogo img{
		width:150px; 
		margin:30px 80px 10px 30px;
	}
	#right{
		float:right;
		margin:40px 15px;
		display:inline-block;
	}
	#right ul li{
		list-style:none;
		display:inline-block;
		margin-right:10px;
	}
	#right ul li a{
		text-decoration:none;
		color:black;
	}
	
	
		#category{
		width:100%;
		text-align:center;
		margin-top:20px;
		margin-left:10px;
		min-width:1000px;
	}
	#category ul li{
		list-style:none;
		display:inline-block;
		margin-right:50px;
		text-align:center;
		font-weight:bold;
	}
	#category ul li a{
		text-decoration:none;
	}
	#product{
		width:150px; height:200px;
		text-align:center;
		display:none;
	}
	#product ul li{padding:10px;}
	#product ul li a{
		text-decoration:none;
		color:black;
	}
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
   
   
   function showProduct(){
	   var product=document.getElementById("product");
	   product.style.display="inline";
   }
   function noProduct(){
	   var product=document.getElementById("product");
	   product.style.display="none";
   }
</script>

<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="cpl" value="${pageContext.request.contextPath}/layout.jsp?cpage="/>


<div id="header">

	<div id="homelogo">
		<div id="event"></div>
		<img src="${cp}/header/KilaLogo.png">
		<div id="right">
		<ul>
		   <li><a href="${cp}/header/join">회원가입</a></li>
		   <c:choose>
		      <c:when test="${not empty sessionScope.id}">
		         <li><a href="${cp}/header/logout">로그아웃</a></li>
		         <c:choose>
		            <c:when test="${sessionScope.id=='admin'}">
		               <li><a href="${cpl}/header/adminpage.jsp">관리자페이지</a></li>
		            </c:when>
		            <c:otherwise>
		               <li><a href="${cpl}/header/mypage.jsp">마이페이지</a></li>
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
	</div>
	
	
	
	<div id="category">
	   <ul>
	      <li onmouseover="showProduct()" onmouseup="noProduct()">PRODUCT</li>
	      <li><a href="" style="color:red;">NEW</a></li>
	      <li><a href="" style="color:red;">BEST</a></li>
	      <li><a href="${cpl}/header/eventList.jsp" style="color:blue;">EVENT</a></li>
	   </ul>
	</div>
	<div id="product">
	<ul>
	   <li><a href="${cpl}/product/list?category=all">전체보기</a></li>
	   <li><a href="${cpl}/product/list?category=down_jacket">다운자켓</a></li>
	   <li><a href="${cpl}/product/list?category=jacket">자켓</a></li>
	   <li><a href="${cpl}/product/list?category=longsleeves">긴팔티셔츠</a></li>
	   <li><a href="${cpl}/product/list?category=shortsleeves">반팔티셔츠</a></li>
	   <li><a href="${cpl}/product/list?category=pants">바지</a></li>
	</ul>
	</div>

</div>