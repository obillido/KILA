<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	function IncreaseValue(){
		var cnt=document.getElementById("count");
		if(cnt.value<5)
		cnt.value++;
	}
	function decreaseValue(){
		var cnt=document.getElementById("count");
		if(cnt.value!=0)
		cnt.value--;
	}
	function delcomm(a){
		alert(a);
	}
</script>
<div style="text-align: center;">
<div style="text-align: center; width: 500px;height: 500px; display: inline-block;">
<h1>기본 상품정보페이지</h1><br>
<div style="width: 650px;height: 500px;">
<div style="width: 200px;height: 200px; float: left;">
<img id="img1" src="${pageContext.request.contextPath }/upload/${vo.savefilename }" style="width: 300px;height: 350px">
</div>
<div id="zoom" style="float: right; width: 300px;height: 300px; text-align: left;">
	<b>KILA|${vo.pcode }</b><br><hr><br>
   <h2>${vo.pname }</h2><br><hr><br>
   가격:<span style='color:red'>${vo.price }</span><br><br><hr><br>
   색상:${vo.color }<br><br><hr><br>
   사이즈: <c:forEach var="si" items="${list }">
   		<a href="javascript:delcomm(${si.icnt })">${si.psize}</a>	
   			</c:forEach>
   		<br><br><hr><br>
   <div style="text-align: center;">
   <input type="button" value="-" onclick="decreaseValue()"><input type="text" id="count" value=0 readonly="readonly" style="border-style: none; text-align: center;"><input type="button" value="+" onclick="IncreaseValue()"></div><br>
   <input type="button" value="CART" style="width: 145px; height: 40px">
   <input type="submit" value="BUY" style="width: 145px; height: 40px">
</div>
</div>
</div>
</div>