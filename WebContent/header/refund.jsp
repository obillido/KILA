<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">    
    *{margin:0px;padding:0px;}
    #div{clear:both;text-align:center;margin-left:370px;}
    #div2{clear:both;text-align:center;}
    #div h2{text-align:center;margin-right:350px;}
    #div hr{margin-right:325px;}
    #t1{width:30px;height:30px;}
    #t2{width:100px;height:30px;}
    #t3{width:30px;height:30px;}
    #t4{width:30px;height:30px;}
    #t5{width:50px;height:30px;}
    #t6{width:50px;height:30px;}
    #t7{width:30px;height:30px;}
    #t8{width:100px;height:30px;}
    #t8 a{text-decoration:none;color:black;}
    #firstline{background-color:#D2FFD2}
    table{text-align:center;}
    a{margin-top:10px;}
</style>


<div id="div">
   <br><br><br>
   <hr>
   <h2>[ 환불요청건 리스트 ]</h2>
   <hr><br>
   <table border="3" width="80%">
      <tr id="firstline">
         <th id="t1">주문번호</th>
         <th id="t2">주문자 아이디</th>
         <th id="t3">상품번호</th>
         <th id="t4">상품수량</th>
         <th id="t5">결제일</th>
         <th id="t6">결제수단</th>
         <th id="t7">상태</th>
         <th id="t8">환불여부</th>
      </tr>
      <c:forEach var="list" items="${info }">
         <tr>
            <td id="t1">${list.paynum }</td>
            <td id="t2">${list.bid }</td>
            <td id="t3">${list.pnum }</td>
            <td id="t4">${list.cnt }</td>
            <td id="t5">${list.paydate }</td>
            <td id="t6">${list.paymethod }</td>
            <td id="t7">${list.status }</td>
            <c:choose>
         	   <c:when test="${list.status=='7'}">
         	      <td id="t8">환불완료</td>
         	   </c:when>
         	   <c:when test="${list.status!='7'}">
         	      <td id="t8"><a href="${pageContext.request.contextPath }/header/confirmRefund?paynum=${list.paynum}">환불요청 접수하기</a></td>
         	   </c:when>
         	</c:choose>
         </tr>
      </c:forEach>
   </table>
</div>

<br>
<div id="div2">
   <c:choose>
	   <c:when test="${startPageNum>5 }">
		   <a href="${pageContext.request.contextPath }/header/refundList?pageNum=${startPageNum-1 }">[이전]</a>
	   </c:when>
	   <c:otherwise>
		    이전
	   </c:otherwise>
   </c:choose>
   <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="${pageContext.request.contextPath }/header/refundList?pageNum=${i}"><span style="color:red;">[${i }]</span></a>
         </c:when>
         <c:otherwise>
            <a href="${pageContext.request.contextPath }/header/refundList?pageNum=${i}"><span style="color:black;">[${i }]</span></a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   <c:choose>
	   <c:when test="${endPageNum<pageCount}">
		   <a href="${pageContext.request.contextPath }/header/refundList?pageNum=${endPageNum+1 }">[다음]</a>
	   </c:when>
	   <c:otherwise>
		   다음
	   </c:otherwise>
   </c:choose>
</div>