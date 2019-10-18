<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:150px 0px 0px 400px;clear:both;text-align:center;}
    td{text-align:center;}
    textarea{background-color:#FFE65A;font-size:2em;}
    #comments{background-color:white;}
    #commReg{margin-right:400px;}
    #ecid{width:90px;height:30px;background-color:#C8FFFF;}
    #ecomments{width:360px;height:30px;background-color:#C8FFFF;}
    input[type=button]{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;height:30px;width:70px;}
</style>

<script type="text/javascript">
/*
   window.onload=function(){
	   getList();
   };
   var xhrList=null;
   function getList(){
	   xhrList=new XMLHttpRequest(); 
	   xhrList.onreadystatechange=listOk;
	   xhrList.open('get','ecomments?cmd=list&evnum=${vo.evnum}',true);
	   xhrList.send();
   }
   function listOk(){
	   if(xhrList.readyState==4 && xhrList.status==200){
		   removeComm();
		   var data=xhrList.responseText;
		   var list=JSON.parse(data)[0];
		   var commList=document.getElementById("commList");
		   for(var i=0;i<list.length;i++){
			   var str=list[i].ecid + "<br>" +
			           list[i].ecomments + "<br>";
			   var div=document.createElement("div");
			   div.innerHTML=str;
			   div.className="comm";
			   commList.appendChild(div);         
		   }
	   }
   }
*/
   var xhrInsert=null;
   function insertComm(){
	   xhrInsert=new XMLHttpRequest();
	   xhrInsert.onreadystatechange=insertOk;
	   xhrInsert.open('post','ecomments?cmd=insert',true);
	   xhrInsert.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
	   var ecid=document.getElementById("ecid").value;
	   var ecomments=document.getElementById("ecomments").value;
	   var param="evnum=${vo.evnum}&ecid=" + ecid + "&ecomments=" + ecomments;
	   xhrInsert.send(param);
   }
   function insertOk(){
	   if(xhrInsert.readyState==4 && xhrInsert.status==200){
		   var data=xhrInsert.responseText;
		   var json=JSON.parse(data);
		   if(json.code=='success'){
			   document.getElementById("id").value="";
			   document.getElementById("comments").value="";
			   getList();
		   }else{
			   alert("댓글 등록 실패");
		   }
	   }
   }
   function removeComm(){
	   var commList=document.getElementById("commList");
	   var children=commList.childNodes; //commList의 모든 자식노드 얻어오기
	   for(var i=children.length-1;i>=0;i--){ //삭제는 뒤에서부터 하기
		   var comm=children.item(i); //i번째 자식댓글 얻어오기
		   commList.removeChild(comm); //commList에서 댓글(1개) 지우기
	   }
   }
</script>


<div id="div">
<table border="2" width="1100" style="background-color:#FFE65A;">
   <tr>
      <td>제목</td>
      <td>${vo.title }</td>
   </tr>
   <tr>
      <td>내용</td>
      <td><textarea rows="5" cols="50" readonly="readonly">${vo.content }</textarea></td>
   </tr>
   <tr>
      <td>이미지</td>
      <td><img src="${pageContext.request.contextPath }/eventUploaded/${vo.orgfilename}" width="600" height="600"></td>
   </tr>
</table>

<div><!-- 댓글 -->
   <div id="commList"></div>
   <div id="commReg">
   <br><br>
        아이디 <input type="text" id="ecid" value="${sessionScope.id}" readonly>
        댓글 <input type="text" id="ecomments">
      <input type="button" value="등록" onclick="insertComm()">
   </div>
</div>
</div>

