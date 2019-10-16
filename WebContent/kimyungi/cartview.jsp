<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script type="text/javascript">
	function deletecart(paynum){
		location.href="cart?cmd=delete&paynum="+paynum;
	}
</script>
<div style="text-align: center">
<h2>SHOPPING BAG</h2>
<h5>* 카트에 담긴 상품은 품절시 관리자에 의해 삭제될 수 있습니다. 주의하세요!</h5>
<table style="display: inline-block;">
	<tr style="background-color: skyblue;">
		<th width="40"></th><th width="300">상품정보</th><th width="120">수량</th>
		<th width="150">상품금액</th><th width="150">주문금액</th><th width=150></th>
	</tr>
	<c:forEach var="li" items="${list }">
	<tr>
		<th><input type="checkbox"></th><th><div><img width="100px" style="float: left;" src="${pageContext.request.contextPath }/upload/${li.savefilename }"></div><br>KILA | ${li.pcode }<br>${li.pname }<br>${li.color }/${li.psize }</th>
		<th>${li.cnt }</th><th>${li.price }</th><th>${li.price * li.cnt }</th><th><input type="button" style="width: 120px; height: 35px; background-color: black; color: white; margin-bottom: 10px;" value="주문하기"><br><input type="button" style="width: 120px; height: 35px; background-color: white;" onclick="deletecart(${li.paynum})" value="삭제하기"></th>
	</tr>
	</c:forEach>
	<tr>
		<th height="100" colspan="7" style="background-color: skyblue;">상품합계 : ${vo.price * cnt } + 배송비 : <span style="color: red;">0</span>
		<c:forEach begin="1" end="80">&nbsp</c:forEach>
		<h1 style="display: inline-block;">총 결제금액: <span style="color: red;">${vo.price * cnt }</span></h1></th>
	</tr>
</table>
</div>