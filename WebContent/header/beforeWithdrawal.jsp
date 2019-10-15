<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>beforeWithdrawal.jsp</title>
</head>
<body>
<script type="text/javascript">
   var xhr=null;
   function yes(){
	   xhr=new XMLHttpRequest();
	   xhr.onreadystatechange=callback;
	   xhr.open('get','withdrawal.jsp',true);
	   xhr.send();
   }
   function callback(){
	   if(xhr.readyState==4 && xhr.status==200){
		   var data=xhr.responseText;
		   var json=JSON.parse(data);
		   if(json.str=='success'){
			   alert("회원탈퇴가 성공적으로 완료되었습니다.");
		   }else{
			   alert("회원탈퇴 작업이 실패하였습니다.");
		   }   
	   }
   }
   function no(){
	   alert("회원탈퇴 취소를 하셨습니다. 감사합니다."); 
   }
</script>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:200px 0px 0px 800px;}
    #btn1{background-color:#B9062F;color:white;border:1px solid black;margin-top:10px;margin-left:120px;
          width:50px;height:30px;}
    #btn2{background-color:#00008C;color:white;border:1px solid black;margin-top:10px;margin-left:30px;
          width:50px;height:30px;}
    #a{margin-left:160px;}
    #h{font-style:italic;}
</style>

<div id="div">
<h1 id="h">정말로 탈퇴하시겠습니까?</h1>
<br>
<br>
<input type="button" value="YES" onclick="yes()" id="btn1">
<input type="button" value="NO" onclick="no()" id="btn2">
<br>
<br>
<br>
<br>
<a href="${pageContext.request.contextPath }/home" id="a">홈으로</a>
</div>
</body>
</html>