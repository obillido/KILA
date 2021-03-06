<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String msg=request.getParameter("msg");
	if(msg==null) msg="";
%>


<style type="text/css">
	#ProductRegPage{
		width:100%;
		clear:both;
		text-align:center;
		margin:50px 0px;
	}
	#inner{
		display:inline-block;
		width:500px;
		text-align:left;
	}

	label{
		display:inline-block;
		width:100px;
		height:30px;
	}
	select{height:25px;}
	#cnt{width:60px;}
</style>


<div id="ProductRegPage">
	<div id="inner">
	
	<h1>새상품등록 <%=msg %></h1>
	
	<br><br>
	
	<form method="post" onsubmit="return check();"
		action="${pageContext.request.contextPath}/product/productRegi?cmd=new"
		enctype="multipart/form-data">
		<label for="category">카테고리</label>
		<select name="cname">
			<option value="select">선택</option>
			<option value="down_jacket">다운자켓</option>
			<option value="jacket">자켓</option>
			<option value="longsleeves">긴팔티셔츠</option>
			<option value="shortsleeves">반팔티셔츠</option>
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
		<input type="checkbox" name="color" value="RED"> RED  
		<input type="checkbox" name="color" value="GREEN"> GREEN 
		<div id="fileupload"></div>
		<br>
		<label for="size">사이즈</label>
		<input type="checkbox" name="size" value="90"> 90 
		<input type="checkbox" name="size" value="95"> 95
		<input type="checkbox" name="size" value="100"> 100
		<input type="checkbox" name="size" value="105"> 105
		<input type="checkbox" name="size" value="110"> 110
		<br>
		<label for="count">수량</label>
		<input type="text" name="cnt">
		<br>
		<input type="submit" value="등록">
	</form>

	</div>
</div>




<script type="text/javascript">	
	var clr=document.getElementsByName("color");
	for(var i=0; i<clr.length;i++){
		clr[i].addEventListener('click',function(e){
			var fu=document.getElementById("fileupload");
			if(this.checked==true){
				var div=document.createElement("div");
				div.innerHTML="<label for='fileupload'>"+this.value+"</label>" + 
							  "<input type='file' name='file_"+this.value+"'>";
				div.setAttribute("name","file_"+this.value);
				fu.appendChild(div);
			}else{
				var child=document.getElementsByName("file_"+this.value)[0];
				fu.removeChild(child);
			}
		});
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
