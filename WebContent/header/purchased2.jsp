<%@page import="kila.dao.PaymentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #purchaseList{margin-top:50px; width:100%; text-align:center;}
    #purchaseList #inner{display:inline-block;}
    #purchaseList #inner table{min-width:1000px;}
    #div2{clear:both;text-align:center;}
    #t1{width:100px;height:30px;}
    #t2{width:30px;height:30px;}
    #t3{width:40px;height:30px;}
    #t4{width:40px;height:30px;}
    #t5{width:30px;height:30px;}
    #t6{width:50px;height:30px;}
    #t7{width:50px;height:30px;}
    #t6 a{text-decoration:none;color:black;}
    #t7 a{text-decoration:none;color:black;}
    #firstline{background-color:#D2FFD2;}
    table{text-align:center;}
    a{margin-top:10px;}
    #purchaseList table span{color:grey;}
  
</style>

<div id="purchaseList">
	<div id="inner">
   <br><br>
   <hr>
   <h2>- [ ${rank} ] 회원님의 구매내역 -</h2><br>
   <h4>총 구매금액은 ${tot} 입니다.</h4>
   <hr><br><br>
   <table border="2" >
      <tr id="firstline">
         <th id="t1">주문한 상품명</th>
         <th id="t2">주문수량</th>
         <th id="t3">결제일</th>
         <th id="t4">결제수단</th>
         <th id="t5">상태</th>
         <th id="t6">환불요청/취소</th>
         <th id="t7">구매확정</th>
      </tr>
      <c:forEach var="list" items="${info }">
         <tr>
            <td id="t1">${list.pname }</td>
            <td id="t2">${list.cnt }</td>
            <td id="t3">${list.paydate }</td>
            <td id="t4">${list.paymethod }</td>
            <td id="t5">${list.status }</td>
         		
         	<c:choose>
               <c:when test="${list.status eq '배송준비' or list.status eq '배송중' or list.status eq '배송완료'}">
                  <td id="t6">환불요청</td>
         	   </c:when>
         	   <c:when test="${list.status eq '환불요청'}">
         	      <td id="t6">환불요청취소</td>
         	   </c:when>
         	   <c:otherwise>
         	   	  <td id="t6"><span>환불요청불가</span></td>
         	   </c:otherwise>
         	</c:choose>
         	
         	<c:choose>
         	   <c:when test="${list.status eq '구매확정' or list.status eq '리뷰완료'}">
         	      <td id="t7"><span>구매확정취소불가</span></td>
         	   </c:when>
         	   <c:when test="${list.status eq '환불완료' or list.status eq '환불요청'}">
         	      <td id="t7"><span>구매확정불가</span></td>
         	   </c:when>
         	   <c:otherwise>
         	      <td id="t7">구매확정하기</td>
         	   </c:otherwise>
         	</c:choose>
         
         </tr>
      </c:forEach>
   </table>
   <br>  
   </div>
</div>
<br>
<div id="div2">
   <c:choose>
	   <c:when test="${startPageNum>5 }">
		   <a href="${pageContext.request.contextPath }/header/purchased2?pageNum=${startPageNum-1 }">[이전]</a>
	   </c:when>
	   <c:otherwise>
		    이전
	   </c:otherwise>
   </c:choose>
   <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="${pageContext.request.contextPath }/header/purchased2?pageNum=${i}"><span style="color:red;">[${i }]</span></a>
         </c:when>
         <c:otherwise>
            <a href="${pageContext.request.contextPath }/header/purchased2?pageNum=${i}"><span style="color:black;">[${i }]</span></a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   <c:choose>
	   <c:when test="${endPageNum<pageCount}">
		   <a href="${pageContext.request.contextPath }/header/purchased2?pageNum=${endPageNum+1 }">[다음]</a>
	   </c:when>
	   <c:otherwise>
		   다음
	   </c:otherwise>
   </c:choose>
</div>