<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="cp" value="${pageContext.request.contextPath}/layout.jsp?cpage="/>

<div>
   <ul>
      <li><a href="${cp}/product/productRegi">상품등록</a></li>
      <li><a href="${cp}/product/list?type=A">상품목록</a></li>
      <li><a href="">결제목록</a></li>
      <li><a href="${cp}/header/registerEvent.jsp">이벤트작성하기</a></li>
      <li><a href="${cp}/home.jsp">홈으로</a></li>
   </ul>
</div>