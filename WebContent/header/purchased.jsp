<%@page import="kila.dao.PaymentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{clear:both;margin-left:300px;}
    #div h2{text-align:center;margin-right:300px;}
    #div hr{margin-right:330px;}
    #div h4{margin-right:280px;text-align:center;}
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
  
</style>

<div id="div">
   <br><br>
   <hr>
   <h2>- [ ${rank} ] 회원님의 구매내역 -</h2><br>
   <h4>총 구매금액은 ${tot} 입니다.</h4>
   <hr><br><br>
   <table border="2" width="80%">
      <tr id="firstline">
         <th id="t1">주문한 상품명</th>
         <th id="t2">주문수량</th>
         <th id="t3">결제일</th>
         <th id="t4">결제수단</th>
         <th id="t5">상태</th>
         <th id="t7">구매확정</th>
         <th id="t6">환불요청/취소</th>
         
      </tr>
      <c:forEach var="list" items="${info }">
         <tr>
            <td id="t1">${list.pname }</td>
            <td id="t2">${list.cnt }</td>
            <td id="t3">${list.paydate }</td>
            <td id="t4">${list.paymethod }</td>
            <td id="t5">${list.status }</td>
          
         	
         	<c:choose>
         	   <c:when test="${list.status eq '구매확정'}">
         	      <td id="t7">구매확정취소불가</td>
         	      <td id="t6">환불요청불가</td>
         	   </c:when>
         	   <c:when test="${list.status ne '구매확정'}">
         	      <td id="t7"><a href="${pageContext.request.contextPath }/header/confirmOrder?paynum=${list.paynum}">구매확정하기</a></td>
         	   </c:when>
         	</c:choose>
         	
         	<c:choose>
               <c:when test="${list.status eq '배송준비' or list.status eq '배송중' or list.status eq '배송완료'}">
                  <td id="t7"><a href="${pageContext.request.contextPath }/header/cancelOrder?paynum=${list.paynum}">환불요청</a></td>
         	   </c:when>
         	   <c:when test="${list.status eq '환불요청'}">
         	      <td id="t7"><a href="${pageContext.request.contextPath }/header/returnOrder?paynum=${list.paynum}">환불요청취소</a></td>
         	   </c:when>
         	</c:choose>
         	
         </tr>
      </c:forEach>
   </table>
   <br>  
</div>