<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	var sig=false;
	function IncreaseValue(){
		if(sig==true){
			var cnt=document.getElementById("count");
			var vcnt=document.getElementById("vcnt");
			if(cnt.value==vcnt.value){
				alert("주문 수량은 재고를 넘길수 없습니다.");
			}else{
				cnt.value++;
			}
		}else{
			alert("사이즈를 우선 골라주세요.")
		}
	}
	function decreaseValue(){
		if(sig==true){
			var cnt=document.getElementById("count");
			if(cnt.value!=1)
			cnt.value--;
		}else{
			alert("사이즈를 우선 골라주세요.")
		}
	}
	function viewcnt(icnt,psize){
		sig=true;
		var cntsr=document.getElementById("cntsr");
		document.getElementById("btn1").disabled = false;
		btn2=document.getElementById("btn2").disabled = false;;
		cntsr.style.display="block";
		var vcnt=document.getElementById("vcnt");
		var sps=document.getElementById("sps");
		vcnt.value=icnt;
		sps.value=psize;
	}
</script>
<div style="text-align: center;">
<div style="text-align: center; display: inline-block;">
<h1>기본 상품정보페이지</h1><br>
<div>
<div style="width: 400px;height: 500px; float: left;">
<img id="img1" src="${pageContext.request.contextPath }/upload/${vo.savefilename }" style="width: 350px;height: 400px">
</div>
<div id="zoom" style="float: right; width: 300px;height: 300px; text-align: left;">
	<b>KILA|${vo.pcode }</b><br><hr><br>
   <h2>${vo.pname }</h2><br><hr><br>
   가격:<span style='color:red'>${vo.price }</span><br><br><hr><br>
   색상:${vo.color }<br><br><hr><br>
   사이즈: <c:forEach var="si" items="${list }">
   		<a href="javascript:viewcnt(${si.icnt },${si.psize },event)">${si.psize}</a>	
   			</c:forEach>
   		<br><br><hr><br>
   <form method="post" action="${pageContext.request.contextPath }/kila/payment">
   <input type="hidden" name="scolnum" value=${vo.colnum }>
   <div id=cntsr style="display: none;">
   재고:<input type="text" id="vcnt" readonly="readonly" style="border-style: none; font-size: 16px;width:30px">
   (<input type="text" id="sps" name="spsize" readonly="readonly"  style="border-style: none; font-size: 16px;width:30px">)<br><br><hr>
   </div>
   <div style="text-align: center;"><br>
   <input type="button" value="-" id="btnminus" onclick="decreaseValue()"><input type="text" id="count" name="pcnt" value=1 readonly="readonly" style="border-style: none; text-align: center;"><input type="button" id="btnplus" value="+" onclick="IncreaseValue()"></div><br>
   <input type="button" value="CART" id="btn1" style="width: 145px; height: 40px" disabled="disabled">
   <input type="submit" value="BUY" id="btn2" style="width: 145px; height: 40px" disabled="disabled">
   </form>
</div>
</div>
</div>
<div>
여기인가?
</div>
</div>