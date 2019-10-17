<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
	#content{
		width:700px;
		margin-top:50px;
	}
	#content form{
		margin-top:30px;
		text-align:left;
		font-size:20px;
	}
	#content label{
		display:inline-block;
		width:100px; height:40px;
	}
	#content select{
		width:100px; height:30px;
		font-size:20px;
	}
	#content input[type=text]{
		width:250px; height:25px;
		font-size:20px;
	}
	#content textarea{
		display:inline-block;
		width:550px; height:200px;
		font-size:20px;
	}
	#content input[type=submit]{
		display:inline-block;
		width:100px; height:40px;
		font-size:20px;
		margin-right:50px;
	}
	
</style>

<div stype="text-align:center; width:100%;">
<div id="content">
	<h1>문의하기</h1>
	<form method="post" action="${pageContext.request.contextPath}/inquiry/registration">
		<label for="product_name">상품명</label>
		<span>${pname}</span>
		<br>
		<label for="color">색상</label>
		<span>${color}</span>
		<br>
		<label>분류</label>
		<select name="inqtype">
			<option value="1">사이즈</option>
			<option value="2">배송</option>
			<option value="3">재입고</option>
			<option value="4" selected>기타</option>
		</select>
		<br>
		<label for="title">제목</label>
		<input type="text" name="title">
		<br>
		<label for="content">내용</label>
		<textarea rows="5" cols="50" name="content" onkeyup="checkByte(this,1000)"></textarea>
		<div style="text-align:right; margin-right:50px;">
			<span id="showInqBytes"></span>/1000 Bytes
		</div>
		<input type="hidden" name="colnum" value="${colnum}">
		<br>
		<div style="text-align:right;">
			<input type="submit" value="등록">
		</div>
	</form>

</div>
</div>



<script type="text/javascript">
	window.onload=function(){
		var bb=document.getElementById("showInqBytes")
		bb.innerText=0;
	}
	function checkByte(obj,maxByte){
		var str=obj.value
		var len=str.length;
		var vb=0;
		var vbLen=0;
		var str2;
		for(var i=0; i<len; i++){
			var oneChar=str.charAt(i);
			if(escape(oneChar).length>4) vb+=3;
			else vb++;
			if(vb<=maxByte) vbLen=i+1;
		}
		if(vb>maxByte){
			alert("허용치 초과!");
			obj.value=str.substr(0,vbLen);
			checkByte(obj,maxByte);
		}else{
			var bb=document.getElementById("showInqBytes")
			bb.innerText=vb;
		}
	}

</script>

