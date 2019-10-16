<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">    
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 350px;}
    #div h2{text-align:center;margin-right:350px;}
    #div hr{margin-right:325px;}
    #t1{width:200px;height:30px;}
    #t2{width:30px;height:30px;}
    #t3{width:40px;height:30px;}
    #t4{width:40px;height:30px;}
    #t5{width:100px;height:30px;}
    #t6{width:50px;height:30px;}
    #t7{width:50px;height:30px;}
    #t6 a{text-decoration:none;color:black;}
    #t7 a{text-decoration:none;color:black;}
    #firstline{background-color:#C0FFFF}
    table{text-align:center;}
    a{margin-top:10px;}
</style>


<div id="div">
   <hr>
   <h2>[ 환불요청건 리스트 ]</h2>
   <hr><br>
   <table border="2" width="80%">
      <tr id="firstline">
         <th id="t1">주문번호</th>
         <th id="t2">주문자 아이디</th>
         <th id="t3">상품번호</th>
         <th id="t4">상품수량</th>
         <th id="t5">결제일</th>
         <th id="t6">결제수단</th>
         <th id="t7">상태</th>
         
      </tr>
      <c:forEach var="list" items="${info }">
         <tr>
            <td id="t1">${list.paynum }</td>
            <td id="t2">${list.bid }</td>
            <td id="t3">${list.pnum }</td>
            <td id="t4">${list.cnt }</td>
            <td id="t5">${list.paydate }</td>
            <td id="t5">${list.paymethod }</td>
            <td id="t5">${list.status }</td>
         </tr>
      </c:forEach>
   </table>
   <br>  
   <a href="${pageContext.request.contextPath }/home">홈으로</a>
</div>