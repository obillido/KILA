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
	
	<h1>상품등록 <%=msg %></h1>
	
	<br><br>
	
	<form method="post" action="${pageContext.request.contextPath}/product/productRegi?cmd=old" onsubmit="return check();">
		<label for="pcode">상품명</label>
		<input type="text" value="${vo.pname}" readonly>
		<br>
		<label for="color">색상</label>
		<input type="text" value="${vo.color}" readonly>
		<input type="hidden" name="colnum" value="${vo.colnum}">
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

	function check(){
		var cc;
		
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