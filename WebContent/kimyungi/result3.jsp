<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	var sig=false;
	var sig2=false;
	function cancle(target){
		if(!sig){
		alert("개인정보 수집 이용에 대해 동의를 해주셔야 결제진행이 가능합니다.");
		target.checked=false;
		}else{
			sig2=true;
		}
	}
	function cancler(target){
		if(target.checked==true){
			sig=true;
		}else{
			sig=false;
		}
	}
	function checkyo(){
		if(!sig){
			alert("개인정보 수집 이용에 대해 동의를 해주셔야 결제진행이 가능합니다.");
			return false;
		}else if(!sig2){
			alert("결제방식을 선택해주세요.")
			return false;
		}else{
			return true;
		}
	}
</script>
<div style="text-align: center">
<h2>CHECKOUT</h2>
<table style="display: inline-block;">
	<tr style="background-color: pink;">
		<th width="300">상품정보</th><th width="120">수량</th>
		<th width="150">상품금액</th><th width="150">쿠폰할인</th>
		<th width="150">할인합계</th><th width="150">결제금액</th>
	</tr>
	<tr>
		<th><div><img width="100px" style="float: left;" src="${pageContext.request.contextPath }/upload/${vo.savefilename }"></div><br>KILA | ${vo.pcode }<br>${vo.pname }<br>${vo.color }/${vo.psize }</th>
		<th>${vo.cnt }</th><th>${vo.price }</th><th>0원</th><th>0원</th><th>${vo.price * vo.cnt }</th>
	</tr>
	<tr>
		<th height="100" colspan="7" style="background-color: pink;">상품합계 : ${vo.price * vo.cnt } + 배송비 : <span style="color: red;">0</span>
		<c:forEach begin="1" end="80">&nbsp</c:forEach>
		<h1 style="display: inline-block;">총 결제금액: <span style="color: red;">${vo.price * vo.cnt }</span></h1></th>
	</tr>
</table>
	<br>
	<h2>주문자 정보</h2>
	<div style="width: 1000px; border: 1px solid black; margin: auto; text-align: left; padding: 20px;">
	이름: ${vo2.bname }<br><br>
	휴대폰 번호: ${vo2.phone }<br><br>
	E-MAIL: ${vo2.email }<br><br>
	</div><br>
	<h2>배송지 정보</h2>
	<div style="width: 1000px; border: 1px solid black; margin: auto; text-align: left; padding: 20px;">
	이름: ${vo2.bname }<br><br>
	휴대폰 번호: ${vo2.phone }<br><br>
	주소: ${vo2.addr }
	</div><br>
	<div style="width: 1040px; margin: auto;">
	<div style="float: left; border: 1px solid black; padding: 20px;">
	회원 주문 정보수집 동의
	</div>
	<div style="float: left; border: 1px solid black; text-align: left; padding: 20px;">
1. 개인정보 수집 항목 : 이름, 주소, 휴대폰번호, Email<br>
2. 수집 목적 : 회원 가입 유무 확인 및 구매 내용에 대한 정확한 회신<br>
3. 개인정보 보유 이용 기간 : 전자상거래 등에서의 소비자보호에 관한 법률 등에서 정한 보존기간 동안 고객님의 개인 정보를 보유합니다.<br>
- 계약 또는 청약철회 등에 관한 기록 : 5년<br>
- 대금결제 및 재화 등의 공급에 관한 기록 : 5년<br>
- 소비자의 불만 또는 분쟁처리에 관한 기록 : 3년<br>
	</div>
	<div style="float: right; text-align: left; padding: 20px;">
	<input type="checkbox" onclick="cancler(this)"> 회원 개인정보 수집 이용에 대한 내용을 확인 하였으며 이에 동의 합니다.
	</div>
	</div>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<div style="width: 1040px; margin: auto;">
	<h2>결제방법</h2>
	<hr style="border:solid 1px;">
	<form method="post" action="${pageContext.request.contextPath }/kila/payment" onsubmit="return checkyo()">
	<input type="hidden" name="paynum" value=${vo.paynum }>
	<input type="hidden" name="cmd" value="cart">
	<input type="radio" name="paymethod" value="네이버페이" onclick="cancle(this)"><b>네이버페이</b> <input type="radio" name="paymethod" value="신용카드" onclick="cancle(this)"><b>신용카드</b> <input type="radio" name="paymethod" value="무통장입금(가상계좌)" onclick="cancle(this)"><b>무통장입금(가상계좌)</b> <input type="radio" name="paymethod" value="무통장입금(에스크로)" onclick="cancle(this)"><b>무통장입금(에스크로)</b> <input type="radio" name="paymethod" value="실시간계좌이체" onclick="cancle(this)"><b>실시간계좌이체</b> <input type="radio" name="paymethod" value="PAYCO" onclick="cancle(this)"><b>PAYCO</b> <input type="radio" name="paymethod" value="스마일페이" onclick="cancle(this)"><b>스마일페이</b> <input type="radio" name="paymethod" value="카카오페이" onclick="cancle(this)"> <b>카카오페이</b>
	<br><br>
	<div>
	<input type="reset" style="width: 100px; height: 40px" value="취소"><input type="submit" style="width: 300px; height: 40px; margin-right: 100px; color: white; background-color: red" value="결제">
	</div>
	</form>
	</div>
</div>