<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<script type="text/javascript">
	var hapi=0;
	function addComma(num) {
	  var regexp = /\B(?=(\d{3})+(?!\d))/g;
	  return num.toString().replace(regexp, ',');
	}
	function deletecart(paynum){
		location.href="cart?cmd=delete&paynum="+paynum;
	}
	function paymentcart(paynum){
		location.href="cart?cmd=spayment&paynum="+paynum;
	}
	function changeprice(btn,price){
		if(btn.checked){
			var hap=document.getElementById("hap");
			hapi=hapi+price;
			hap.value=addComma(hapi);
		}else if(!btn.checked){
			var hap=document.getElementById("hap");
			hapi=hapi-price;
			hap.value=addComma(hapi);
		}
	}
	function gohome(){
		location.href="/KILA/home";
	}
</script>
<div style="text-align: center">
<h2>SHOPPING BAG</h2>
<h5>* 카트에 담긴 상품은 품절시 관리자에 의해 삭제될 수 있습니다. 주의하세요!!</h5>
<form method="post" action="${pageContext.request.contextPath }/kila/cart?cmd=cartaction">
<table style="display: inline-block;">
	<tr style="background-color: skyblue;">
		<th width="40"></th><th width="300">상품정보</th><th width="120">수량</th>
		<th width="150">상품금액</th><th width="150">주문금액</th><th width=150></th>
	</tr>
	<c:forEach var="li" items="${list }">
	<tr>
		<th><input type="checkbox" name="paynum" onclick="changeprice(this,${li.price * li.cnt})" value="${li.paynum }"></th><th><div><img width="100px" style="float: left;" src="${pageContext.request.contextPath }/upload/${li.savefilename }"></div><br>KILA | ${li.pcode }<br>${li.pname }<br>${li.color }/${li.psize }</th>
		<th>${li.cnt }</th><th>${fmf.format(li.price) }</th><th>${fmf.format(li.price * li.cnt) }</th><th><input type="button" style="width: 120px; height: 35px; background-color: black; color: white; margin-bottom: 10px;" onclick="paymentcart(${li.paynum})" value="주문하기"><br><input type="button" style="width: 120px; height: 35px; background-color: white;" onclick="deletecart(${li.paynum})" value="삭제하기"></th>
	</tr>
	</c:forEach>
	<tr>
		<th height="100" colspan="7" style="background-color: skyblue;">
		<c:forEach begin="1" end="100">&nbsp</c:forEach>
		<h1 style="display: inline-block;">총 결제금액:</h1> <input type="text" id="hap" value="${fmf.format(li.price * li.cnt) }" readonly="readonly" style="font-size: 30px; width: 120px; color: red; border: hidden; background-color: skyblue;"></th>
	</tr>
</table><br><br>
	<input type="submit" style="width: 150px; height: 50px; background-color: red; color: white; margin-right: 20px" value="선택상품삭제" ><input type="submit" style="width: 150px; height: 50px; background-color: black; color: white; margin-right: 20px" value="선택상품구매" formaction="${pageContext.request.contextPath }/kila/cart?cmd=cartaction2"><input type="button" style="width: 150px; height: 50px; background-color: white; color: black;" onclick="gohome()" value="쇼핑계속하기">
	</form>
</div>