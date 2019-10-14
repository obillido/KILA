<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<style type="text/css">
	*{margin:0px;padding:0px;}

</style>

<script type="text/javascript">
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

<div id="category">
   <ul>
      <li onmouseover="showProduct()" onmouseup="noProduct()">PRODUCT</li>
      <li><a href="" style="color:red;">NEW</a></li>
      <li><a href="" style="color:red;">BEST</a></li>
      <li><a href="${cp}/header/eventList.jsp" style="color:blue;">EVENT</a></li>
   </ul>
</div>
<div id="product">
<ul>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=all">전체보기</a></li>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=down_jacket">다운자켓</a></li>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=jacket">자켓</a></li>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=longsleeves">긴팔티셔츠</a></li>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=shortsleeves">반팔티셔츠</a></li>
   <li><a href="${cp}/layout.jsp?cpage=/product/list?category=pants">바지</a></li>
</ul>
</div>