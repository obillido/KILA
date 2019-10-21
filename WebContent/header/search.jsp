<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<style type="text/css">
   *{margin:0px;padding:0px;}
   .item{
		float:left;
		width:250px; height:350px;
		margin-left:20px;
		margin-top:30px;
		margin-bottom:20px;
	}
   h2{text-align:center;}
   .item p{
		padding:10px;
		color:black;
		text-align:center;
	}
</style>


<br><br><hr><br>
<h2>- 검색어로( ${search} ) 검색한 결과 -</h2>
<br><hr><br><br>


<c:forEach var="finallist" items="${info }">
   <div class="item">
      <p><a href="${cp}/iteminfo?colnum=${finallist.colnum}"><img src="${cp }/upload/${finallist.savefilename}" width="250"></a>
      <br>${finallist.pname }</p>
   </div>
</c:forEach>
