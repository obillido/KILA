<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	#content{
		width:600px;
		margin-top:50px;
	}
	
</style>

<div stype="text-align:center; width:100%;">
<div id="content">
	<h1>문의하기</h1>
	
	<form method="post" action="${pageContext.request.contextPath}/inquiry/registration">
		<label for="product_name">상품명</label>
		<p>${pname}</p>
		<label for="color">색상</label>
		<p>${color}</p>
		<label for="title">제목</label>
		<input type="text" name="title">
		<label for="content">내용</label>
		<textarea rows="5" cols="50" name="content"></textarea>
		<input type="submit" value="등록">	
	</form>

</div>
</div>