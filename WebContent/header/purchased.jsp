<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
</style>

<div id="div">
<%
         
%>



   <table width="500px" height="400px">
      <tr>
         <th>주문한 상품명</th>
         <td><input type="text" value="${info.pnum}" readonly></td>
      </tr>
      <tr>
         <th>주문 수량</th>
         <td><input type="text" value="${info.cnt}" readonly></td>
      </tr>
      <tr>
         <th>결제일</th>
         <td><input type="text" value="${info.paydate}" readonly></td>
      </tr>
      <tr>
         <th>결제수단</th>
         <td><input type="text" value="${info.paymethod}" readonly></td>
      </tr>
      <tr>
         <th>상태</th>
         <td><input type="text" value="${info.status}" readonly></td>
      </tr>
   </table>
   <a href="${pageContext.request.contextPath }/home">홈으로</a>
</div>