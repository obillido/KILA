<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
var xhr=null;
function checkId(){
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=success;
	var id=document.getElementById("id").value;
	xhr.open('get','/KILA/header/checkId.jsp?id=' + id,true);
	xhr.send();
	
	if(id.length<6){
		alert("아이디는 6자 이상이어야 합니다.");
		return false;
	}
	for(var i=0;i<id.length;i++){
		var ch=id.charAt(i);
		if(!(ch>='a' && ch<='z')){
			alert("아이디는 영어 소문자로만 입력해 주세요.");
			return false;
		}	
	}
	return true;
}
function success(){
	if(xhr.readyState==4 && xhr.status==200){
		var data=xhr.responseText;
		var user=JSON.parse(data);
		var span=document.getElementById("checkId");
		if(user.using==true){
			span.innerHTML="사용 중인 아이디입니다.";
		}else{
			span.innerHTML="중복된 아이디는 아닙니다.";
		}   
	}
}  
var pwd;
var pwdChk;
var span;
var span2;
function checkPwd(){
	pwd=document.getElementById("pwd").value;
	span=document.getElementById("checkPwd");
	if(pwd==""){
		span.innerHTML="";
		return false;
	}
	if(pwd.length<6){
		span.innerHTML="비밀번호는 6자 이상이어야 합니다.";
		return false;
	}else{
		span.innerHTML="";
		return false;
	}
	for(var i=0;i<pwd.length;i++){
		var ch=pwd.charAt(i);
		if(!((ch>='a' && ch<='z')||(ch>='0' && ch<='9'))){
			span.innerHTML="비밀번호는 영어 소문자 및 숫자로만 이루어져야 합니다.";
			return false;
		}else{
			span2.innerHTML="";
			return false;
		}
	}
	return true;
}
function checkPwd2(){
	pwd=document.getElementById("pwd").value;
	chkPwd=document.getElementById("chkPwd").value;
	span2=document.getElementById("checkPwd2");
	if(chkPwd==""){
		span2.innerHTML="";
		return false;
	}
	if(pwd!=chkPwd){
		span2.innerHTML="입력한 비밀번호가 서로 일치하지 않습니다.";
		return false;
	}else{
		span2.innerHTML="";
		return false;
	}
	return true;
}
function checkPhone(){
	var phone=document.getElementById("phone").value;
	var span=document.getElementById("checkPhone");
	for(var i=0;i<phone.length;i++){
		var ch=phone.charAt(i);
		if(!(ch>='0' && ch<='9')){
			span.innerHTML="전화번호를 정확히 입력해 주세요.";
			return false;
		}
	}
	if(phone==null || phone==""){
		span.innerHTML="전화번호를 정확히 입력해 주세요.";
		return false;
	}
	if(phone.length<11){
		span.innerHTML="전화번호를 정확히 입력해 주세요.";
		return false;
	}
	span.innerHTML="";
	return true;
}
function checkAddr(){
	var addr=document.getElementById("addr").value;
	var span=document.getElementById("checkAddr");
	if(addr==null || addr==""){
		span.innerHTML="주소를 정확히 입력해 주세요.";
		return false;
	}
	span.innerHTML="";
	return true;
}
function checkEmail(){
   var email=document.getElementById("email").value;
   var span=document.getElementById("checkEmail");
   if(email==null || email==""){
	   span.innerHTML="이메일을 정확히 입력해 주세요.";
	   return false;
   }
   span.innerHTML="";
   return true;
}
function checkName(){
	var name=document.getElementById("name").value;
	var span=document.getElementById("checkName");
	if(name==null || name==""){
		span.innerHTML="이름을 정확히 입력해 주세요.";
		return false;
	}
	span.innerHTML="";
	return true;
}
function signup(){
	if(!checkId()) return false;
	if(!checkPwd()) return false;
	if(!checkPwd2()) return false;
	if(!checkPhone()) return false;
	if(!checkAddr()) return false;
	if(!checkEmail()) return false;
	if(!checkName()) return false;
	return true;
	alert("회원가입을 축하합니다.");
}
</script>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{clear:both;margin-top:100px;}
    #btn1{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;height:30px;width:70px;}
    #btn2{background-color:#5A5AFF;color:white;margin-left:1090px;margin-top:20px;border:2px solid #CBFF75;height:30px;width:70px;}
    input[type=text]{width:200px;height:20px;background-color:#C8FFFF;}
    input[type=password]{width:200px;height:20px;background-color:#C8FFFF;}
    span[name=align]{width:150px;display:inline-block;margin-left:800px;}
</style>

<div id="div">
<form method="post" action="${pageContext.request.contextPath }/header/join" onsubmit="signup()">
   <span name="align">아이디 </span><input type="text" name="id" id="id">
   <input type="button" value="중복검사" onclick="checkId()" id="btn1">
   <span id="checkId" style="color:red;font-size:12px"></span><br>
   <span name="align">비밀번호 </span><input type="password" name="pwd" id="pwd" onkeyup="checkPwd()">
   <span id="checkPwd" style="color:red;font-size:12px"></span><br>
   <span name="align">비밀번호 확인 </span><input type="password" id="chkPwd" onkeyup="checkPwd2()">
   <span id="checkPwd2" style="color:red;font-size:12px"></span><br>
   <span name="align">이름 </span><input type="text" name="name" id="name" onkeyup="checkName()">
   <span id="checkName" style="color:red;font-size:12px"></span><br>
   <span name="align">전화번호 </span><input type="text" name="phone" id="phone" onkeyup="checkPhone()">
   <span id="checkPhone" style="color:red;font-size:12px"></span><br>
   <span name="align">주소 </span><input type="text" name="addr" id="addr" onkeyup="checkAddr()">
   <span id="checkAddr" style="color:red;font-size:12px"></span><br>
   <span name="align">이메일 </span><input type="text" name="email" id="email" onkeyup="checkEmail()">
   <span id="checkEmail" style="color:red;font-size:12px"></span><br>
   <input type="submit" value="회원가입" id="btn2">
</form>
</div>