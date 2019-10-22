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
    #t2{width:40px;height:30px;}
    #t3{width:50px;height:30px;}
    #t4{width:200px;height:30px;}
    #t5{width:50px;height:30px;}
    #t6{width:30px;height:30px;}
    #t7{width:30px;height:30px;}
    #t8{width:30px;height:30px;}
    #t8 a{text-decoration:none;color:black;}
    #firstline{background-color:#D2FFD2}
    table{text-align:center;}
    a{margin-top:10px;}
</style>


<div id="div">
   <br><br><br>
   <hr>
   <h2>[ 전체 회원 목록 ]</h2>
   <hr><br>
   <table border="3" width="80%">
      <tr id="firstline">
         <th id="t1">아이디</th>
         <th id="t2">이름</th>
         <th id="t3">전화번호</th>
         <th id="t4">주소</th>
         <th id="t5">이메일</th>
         <th id="t6">등급</th>
         <th id="t7">상태</th>
         <th id="t8">적립금</th>
      </tr>
      <c:forEach var="list" items="${info }">
         <tr>
            <td id="t1"><a href="${pageContext.request.contextPath }/header/purchased2?id=${list.cid}">${list.cid }</a></td>
            <td id="t2">${list.bname }</td>
            <td id="t3">${list.phone }</td>
            <td id="t4">${list.addr }</td>
            <td id="t5">${list.email }</td>
            <td id="t6">${list.rank }</td>
            <td id="t7">${list.status }</td>
            <td id="t8">${list.coin }</td>
         </tr>
      </c:forEach>
   </table>
</div>

<br>
<div id="div2">
   <c:choose>
	   <c:when test="${startPageNum>5 }">
		   <a href="${pageContext.request.contextPath }/header/allMembers?pageNum=${startPageNum-1 }">[이전]</a>
	   </c:when>
	   <c:otherwise>
		    이전
	   </c:otherwise>
   </c:choose>
   <c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
      <c:choose>
         <c:when test="${pageNum==i }">
            <a href="${pageContext.request.contextPath }/header/allMembers?pageNum=${i}"><span style="color:red;">[${i }]</span></a>
         </c:when>
         <c:otherwise>
            <a href="${pageContext.request.contextPath }/header/allMembers?pageNum=${i}"><span style="color:black;">[${i }]</span></a>
         </c:otherwise>
      </c:choose>
   </c:forEach>
   <c:choose>
	   <c:when test="${endPageNum<pageCount}">
		   <a href="${pageContext.request.contextPath }/header/allMembers?pageNum=${endPageNum+1 }">[다음]</a>
	   </c:when>
	   <c:otherwise>
		   다음
	   </c:otherwise>
   </c:choose>
</div>