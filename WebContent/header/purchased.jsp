<%@page import="kila.dao.PaymentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 350px;}
    #div h2{text-align:center;margin-right:350px;}
    #div hr{margin-right:325px;}
    #t1{width:300px;height:30px;}
    #t2{width:50px;height:30px;}
    #t3{width:50px;height:30px;}
    #t4{width:50px;height:30px;}
    #t5{width:100px;height:30px;}
    #t6{width:50px;height:30px;}
    #t6 a{text-decoration:none;color:black;}
    #firstline{background-color:#C0FFFF}
    table{text-align:center;}
    a{margin-top:10px;}
  
</style>

<div id="div">
   <hr>
   <h2>[ 구매내역 ]</h2>
   <hr><br>
   <table border="2" width="80%">
      <tr id="firstline">
         <th id="t1">주문한 상품명</th>
         <th id="t2">주문 수량</th>
         <th id="t3">결제일</th>
         <th id="t4">결제수단</th>
         <th id="t5">상태</th>
         <th id="t6">환불관련</th>
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
                  <td id="t6"><a href="${pageContext.request.contextPath }/header/cancelOrder?paynum=${list.paynum}">환불요청</a></td>
         	   </c:when>
         	   <c:when test="${list.status eq '환불요청'}">
         	      <td id="t6"><a href="${pageContext.request.contextPath }/header/returnOrder?paynum=${list.paynum}">환불요청취소</a></td>
         	   </c:when>
         	</c:choose>
         </tr>
      </c:forEach>
   </table>
   <br>  
   <a href="${pageContext.request.contextPath }/home">홈으로</a>
</div>