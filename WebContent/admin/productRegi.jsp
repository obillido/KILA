<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록</title>
</head>
<body>

<style type="text/css">
	label{
		display:inline-block;
		width:100px;
		height:30px;
	}
	select{height:25px;}
	#cnt{width:60px;}
</style>


<h1>상품등록</h1>
<form method="post" action="${pageContext.request.contextPath}/product/productRegi.jsp">
	<label for="category">카테고리</label>
	<select id="cname">
		<option value="down_jacket">다운자켓</option>
		<option value="jacket">자켓</option>
		<option value="tshirts">티셔츠</option>
		<option value="pants">바지</option>
	</select>
	<br>
	<label for="product name">상품코드</label>
	<input type="text" id="pname">
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
	<input type="text" id="cnt">
	<br>
	<input type="submit" value="등록">
</form>
</body>
</html>