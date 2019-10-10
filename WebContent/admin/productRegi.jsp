<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>
</head>
<body>
<%
	String msg=request.getParameter("msg");
%>


<style type="text/css">
	label{
		display:inline-block;
		width:100px;
		height:30px;
	}
	select{height:25px;}
	#cnt{width:60px;}
</style>

<h1>상품등록 <%=msg %></h1>
<form method="post" onsubmit="return check();"
	action="${pageContext.request.contextPath}/product/productRegi">
	<label for="category">카테고리</label>
	<select name="cname">
		<option value="select">선택</option>
		<option value="down_jacket">다운자켓</option>
		<option value="jacket">자켓</option>
		<option value="tshirts">티셔츠</option>
		<option value="pants">바지</option>
	</select>
	<br>
	<label for="pcode">상품코드</label>
	<input type="text" name="pcode">
	<br>
	<label for="pcode">상품명</label>
	<input type="text" name="pname">
	<br>
	<label for="price">개당 가격</label>
	<input type="text" name="price">
	<br>
	<label for="color">색상</label>
	<input type="checkbox" name="color" value="BLACK"> BLACK 
	<input type="checkbox" name="color" value="WHITE"> WHITE  
	<input type="checkbox" name="color" value="NAVY"> NAVY  
	<input type="checkbox" name="color" value="GREY"> GREY  
	<br>
	<label for="size">사이즈</label>
	<input type="checkbox" name="size" value="S"> S 
	<input type="checkbox" name="size" value="M"> M
	<input type="checkbox" name="size" value="L"> L
	<input type="checkbox" name="size" value="XL"> XL
	<br>
	<label for="count">수량</label>
	<input type="text" name="cnt">
	<br>
	<input type="submit" value="등록">
</form>

<script type="text/javascript">
	msg();
	function msg(){
		if(<%=msg%>!=null){
			alert(<%=msg%>);
		}
	}

	function check(){
		var cc;
		
		var cname=document.getElementsByName("cname")[0];
		if(cname.value=="select"){
			alert("카테고리를 선택하시오.");
			cname.focus();
			return false;
		}
		
		var pcode=document.getElementsByName("pcode")[0];
		if(pcode.value==null || pcode.value==""){
			alert("상품코드를 입력하시오.");
			pcode.focus();
			return false;
		}
		
		var pname=document.getElementsByName("pname")[0];
		if(pname.value==null || pname.value==""){
			alert("상품명을 입력하시오.");
			pname.focus();
			return false;
		}
		
		var price=document.getElementsByName("price")[0];
		if(price.value==null || price.value==""){
			alert("가격을 입력하시오.");
			price.focus();
			return false;
		}
		if(price.value<'1'){
			alert("올바른 가격을 입력하시오.");
			price.focus();
			return false;
		}
		
		cc=0;
		var color=document.getElementsByName("color");
		for(var i=0; i<color.length; i++){
			if(color[i].checked==true) cc++;
		}
		if(cc==0){
			alert("색상을 선택하시오.");
			return false;
		}
		
		cc=0;
		var size=document.getElementsByName("size");
		for(var i=0; i<size.length; i++){
			if(size[i].checked==true) cc++;
		}
		if(cc==0){
			alert("사이즈를 선택하시오.");
			return false;
		}
		
		cc=0;
		var cnt=document.getElementsByName("cnt")[0];
		if(cnt.value==null || cnt.value==""){
			alert("수량을 입력하시오.");
			cnt.focus();
			return false;
		}
		if(cnt.value<'1' || cnt.value>'999'){
			alert("올바른 수량을 입력하시오.");
			cnt.focus();
			return false;
		}
		return true;
	}
</script>
</body>
</html>