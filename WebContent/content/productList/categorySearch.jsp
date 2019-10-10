<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>categorySeach</title>
</head>
<body>
<style>
	label{
		width:150px;
		display:inline-block;
	}
	label[for=color]{width:70px;}
	label[for=price]{width:70px;}
	label[for=size]{width:70px;}
	label[for*=Select]{width:60px;}
	div[name="list"]{
		display:inline-block;
	}

</style>

<form method="post" action="">
	<div id="categorySearch">
		<div id="color">
			<label for="color">색상</label>
			<input type="checkbox" id="colorAll" value="all" onclick="allSelect('color')">
			<label for="allColorSelect">All</label>
			<div name="list">
				<input type="checkbox" name="color" value="BLACK">
				<label for="BLACK">BLACK</label>
				<input type="checkbox" name="color" value="WHITE">
				<label for="WHITE">WHITE</label>
				<input type="checkbox" name="color" value="GREY">
				<label for="GREY">GREY</label>
				<input type="checkbox" name="color" value="NAVY">
				<label for="NAVY">NAVY</label>	
			</div>
		</div>
		<div id="price">
			<label for="price">가격</label>
			<input type="checkbox" id="priceAll" value="all" onclick="allSelect('price')">
			<label for="allPriceSelect">All</label>
			<div name="list">
				<input type="checkbox" name="price" value="0,100000">
				<label for="0~100000">100,000원 미만</label>
				<input type="checkbox" name="price" value="100000,200000">
				<label for="100000~200000">100,000~200,000원</label>
				<input type="checkbox" name="price" value="200000">
				<label for="200000~">200,000원 이상</label>
			</div>
		</div>
		<div id="size">
			<label for="size">사이즈</label>
			<input type="checkbox" id="sizeAll" value="all" onclick="allSelect('size')">
			<label for="allSizeSelect">All</label>
			<div name="list">
				<input type="checkbox" name="size" value="S">
				<label for="S">S</label>
				<input type="checkbox" name="size" value="M">
				<label for="M">M</label>
				<input type="checkbox" name="size" value="L">
				<label for="L">L</label>
				<input type="checkbox" name="size" value="XL">
				<label for="XL">XL</label>
			</div>
		</div>	
	</div>
	<input type="submit" value="검색하기">
</form>
<div id="select"></div>


<script type="text/javascript">
	function allSelect(ss){
		var cc=document.getElementById(ss+"All");
		var list=document.getElementsByName(ss);
		if(cc.checked==true){
			for(var i=0; i<list.length; i++){
				list[i].checked=true;
			}
		}else{
			for(var i=0; i<list.length; i++){
				list[i].checked=false;
			}
		}
	}
	
	var xhr=null;
	function selectList(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=listOk;
		xhr.open('post','categorySearch.jsp',true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencode');
		var param="";
		xhr.send(param);
	}
	function listOk(){
		if(xhr.readyState==4 && xhr.status==200){
			var data=xhr.responseText;
		}
	}
</script>

</body>
</html>