<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="text-align: center">
<h2>CHECKOUT</h2>
<table style="display: inline-block;">
	<tr style="background-color: pink;">
		<th width="300">상품정보</th><th width="120">수량</th>
		<th width="150">상품금액</th><th width="150">쿠폰할인</th>
		<th width="150">할인합계</th><th width="150">결제금액</th>
	</tr>
	<tr>
		<th><div><img width="100px" style="float: left;" src="${pageContext.request.contextPath }/upload/${vo.savefilename }"></div><br>KILA | ${vo.pcode }<br>${vo.pname }<br>${vo.color }/${psize }</th>
		<th>${cnt }</th><th>${vo.price }</th><th>0원</th><th>0원</th><th>${vo.price * cnt }</th>
	</tr>
	<tr>
		<th height="100" colspan="7" style="background-color: pink;">상품합계 : ${vo.price * cnt } + 배송비 : <span style="color: red;">0</span>
		<c:forEach begin="1" end="80">&nbsp</c:forEach>
		<h1 style="display: inline-block;">총 결제금액: <span style="color: red;">${vo.price * cnt }</span></h1></th>
	</tr>
</table>
	<br>
	<h2>주문자 정보</h2>
	이름${vo2.bname }<br>
	휴대폰 번호${vo2.phone }<br>
	E-MAIL${vo2.email }<br>
	<h2>배송지 정보</h2>
	이름${vo2.bname }<br>
	휴대폰 번호${vo2.phone }<br>
	주소${vo2.addr }<br>
</div>