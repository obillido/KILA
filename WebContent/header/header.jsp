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
		text-align:left;
		margin-left:20px;
		margin-top:15px;
		width:350px; height:20px;
	}
	#event a{text-decoration:none;color:black;}
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
		width:300px; height:200px;
		text-align:center;
		display:none;
		float:center;
	}
	#product ul li{padding:30px;display:inline-block;}
	#product ul li a{
		text-decoration:none;
		color:black;
	}
</style>



<script type="text/javascript">
   var xhr=null;
   var word=null;
   var event=null;
   var json=null;
   window.onload=function(){
	   xhr=new XMLHttpRequest();
	   xhr.onreadystatechange=callback;
	   xhr.open('get','${pageContext.request.contextPath}/header/eventdata.jsp',true);
	   xhr.send();
   }
   function callback(){
	   if(xhr.readyState==4 && xhr.status==200){
		   word=new Array();
		   event=document.getElementById("event");
		   var data=xhr.responseText;
		   json=JSON.parse(data);
		   var str="";
		   for(var i=0;i<json.length;i++){
			   str=json[i].title + " " + json[i].content;
			   word[i]=str;
		   }
		   event.innerHTML="<a href='${pageContext.request.contextPath}/header/eventDetail?num=" + json[0].evnum + "'>" + word[0] + "</a>";
	   }
   }
   var i=0;	
   var interval=setInterval(function(){	
	   if(i>=json.length){
		   i=0;		
	   }
	   event.innerHTML="<a href='${pageContext.request.contextPath}/header/eventDetail?num=" + json[i].evnum + "'>" + word[i] + "</a>";
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
   function cartcancle(id){
	   if(id==""){
		   alert("로그인을 하셔야 이용이 가능합니다. 로그인 페이지로 이동합니다.");
		   location.href="/KILA/header/login";
	   }else{
		   location.href="/KILA/kila/cart";
	   }
   }
</script>



<c:set var="cp" value="${pageContext.request.contextPath}"/>

<div id="header">

	<div id="homelogo">
		<div id="event"></div>
		<a href="${cp}/home"><img src="${cp}/header/NewKilaLogo.png"></a>
		<div id="right">
		<ul>
		   <li><a href="${cp}/header/join">회원가입</a></li>
		   <c:choose>
		      <c:when test="${not empty sessionScope.id}">
		         <li><a href="${cp}/header/logout">로그아웃</a></li>
		         <c:choose>
		            <c:when test="${sessionScope.id=='admin'}">
		               <li><a href="${cp}/header/adminpage">관리자페이지</a></li>
		            </c:when>
		            <c:otherwise>
		               <li><a href="${cp}/header/mypage">마이페이지</a></li>
		            </c:otherwise>
		         </c:choose>
		      </c:when>
		      <c:otherwise>
		         <li><a href="${cp}/header/login">로그인</a></li>
		      </c:otherwise>
		   </c:choose>
			<li><a href="javascript:cartcancle('${sessionScope.id }')">장바구니</a></li>
		</ul>
		</div>
	</div>
	
	
	
	<div id="category">
	   <ul>
	      <li onmouseover="showProduct()" onmouseup="noProduct()">PRODUCT</li>
	      <li><a href="${cp}/product/new" style="color:#B90000;">NEW</a></li>
	      <li><a href="${cp}/product/best" style="color:#B90000;">BEST</a></li>
	      <li><a href="${cp}/header/eventlist" style="color:#00008C;">EVENT</a></li>
	   </ul>
	</div>
	<div id="product">
	<br>
	<ul>
	   <li><a href="${cp}/product/list?category=all">전체보기</a></li>
	   <li><a href="${cp}/product/list?category=down_jacket">다운자켓</a></li>
	   <li><a href="${cp}/product/list?category=jacket">자켓</a></li>
	   <li><a href="${cp}/product/list?category=longsleeves">긴팔티셔츠</a></li>
	   <li><a href="${cp}/product/list?category=shortsleeves">반팔티셔츠</a></li>
	   <li><a href="${cp}/product/list?category=pants">바지</a></li>
	</ul>
	</div>

</div>