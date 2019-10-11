<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>join.jsp</title>
<script type="text/javascript">
var xhr=null;
function checkId(){
	xhr=new XMLHttpRequest();
	xhr.onreadystatechange=success;
	var id=document.getElementById("id").value;
	xhr.open('get','checkId.jsp?id=' + id,true);
	xhr.send();
}
function success(){
	if(xhr.readyState==4 && xhr.status==200){
		var data=xhr.responseText;
		var user=JSON.parse(data);
		var span=document.getElementById("checkId");
		if(user.using==true){
			span.innerHTML="사용 중인 아이디입니다.";
		}else{
			span.innerHTML="사용 가능한 아이디입니다.";
		}   
	}
}  
</script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/header/join">
   아이디 <input type="text" name="id" id="id">
   <input type="button" value="중복검사" onclick="checkId()">
   <span id="checkId" style="color:red;font-size:12px"></span><br>
   비밀번호 <input type="password" name="pwd"><br>
   전화번호 <input type="text" name="phone"><br>
   주소 <input type="text" name="addr"><br>
   이메일 <input type="text" name="email"><br>
   <input type="submit" value="회원가입">
</form>
</body>
</html>