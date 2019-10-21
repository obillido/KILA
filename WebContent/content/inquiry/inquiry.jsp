<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<style type="text/css">
	#inquiry{
		text-align:center;
		margin-top:100px;
		float:left;
		min-width:800px;
	}
	#selectOption{
		text-align:left;
	}
	#selectOption select{
		height:30px;
		margin-bottom:5px;
	}
	#selectOption input[type=checkbox]{
		display:inline-block;
		width:25px; height:25px;
	}
	#selectOption input[type=button]{
		width:100px; height:40px;
		font-size:20px;
		margin-bottom:10px;
	}
	
	
	#inquiryList{
		text-align:left;
		width:100%;
		min-width:1000px;
	}
	#inquiryList span{
		font-size:20px;
		margin:15px 2px;
	}

	.state{
		display:inline-block;
		width:100px;
	}
	.inqtype{
		display:inline-block;
		width:130px;
	}
	.title{
		display:inline-block;
		width:440px; 
	}
	.name{
		display:inline-block;
		width:130px;
	}
	.regdate{
		display:inline-block;
		width:150px;
		text-align:right;
		maring-right:0px;
		padding-right:0px;
	}


	.cList{
		width:100%;
	}
	
	.inqContent{
		width:1000px;
		min-height:80px;
		background-color:#f0f0f0;
		border:1px solid #d3d3d3;
		overflow:auto;
	}
	.inqContent img{
		display:inline-block;
		width:40px;
		margin:20px 50px 20px 20px;
		float:left;
	}
	.content{
		float:left;
		display:inline-block;
		width:850px;
		margin:10px;
		line-height:30px;
	}
	.inqContent input[type=button]{
		width:100px; height:35px;
		font-size:15px;
	}

	.replyForm{
		text-align:center;
	}
	.replyForm textarea{
		margin-top:30px;
		width:600px;
		height:250px;
		font-size:15px;
	}
	.replyForm input[type=submit]{
		width:100px; height:35px;
		font-size:15px;
		margin-right:30px;
		margin-bottom:10px;
	}
</style>


<div id="inquiry">
	<h1>문의하기</h1>
	<br><br>
	<div style="text-align:left;">
		<div id="selectOption">
			<select name="answerType" onchange="inqOption(${vo.colnum})">
				<option value="0" <c:if test="${at==0}">selected</c:if>>전체답변</option>
				<option value="1" <c:if test="${at==1}">selected</c:if>>답변대기</option>
				<option value="2" <c:if test="${at==2}">selected</c:if>>답변완료</option>
			</select>
			<select name="inquiryType" onchange="inqOption(${vo.colnum})">
				<option value="0" <c:if test="${it==0}">selected</c:if>>전체</option>
				<option value="1" <c:if test="${it==1}">selected</c:if>>사이즈</option>
				<option value="2" <c:if test="${it==2}">selected</c:if>>배송</option>
				<option value="3" <c:if test="${it==3}">selected</c:if>>재입고</option>
				<option value="4" <c:if test="${it==4}">selected</c:if>>기타</option>
			</select>
			<input type="checkbox" id="cmi" onclick="checkMyInq('${id}',${vo.colnum})" <c:if test="${not empty cid}">checked</c:if>> 내 문의글만 보기
			<input type="button" value="문의하기" name="inquiryButton" onclick="showInquiryReg('${id}',${vo.colnum})">
		</div>
		<hr>
		
		<div id="inquiryList">
		<c:choose>
			<c:when test="${not empty inqList}">
				<c:forEach var="vo" items="${inqList}">
					<div id="contentList${vo.inum}" class="cList">
						<div  onclick="showInqContent(${vo.inum},'${type}')">
							<c:choose>
								<c:when test="${vo.lev=='1'}"><span class="state" style="color:blue;">대기</span></c:when>
								<c:when test="${vo.lev=='2'}"><span class="state" style="color:red;">완료</span></c:when>
							</c:choose>
							<span class="inqtype">
								<c:choose>
									<c:when test="${vo.inqtype=='1'}">사이즈</c:when>
									<c:when test="${vo.inqtype=='2'}">배송</c:when>
									<c:when test="${vo.inqtype=='3'}">재입고</c:when>
									<c:when test="${vo.inqtype=='4'}">기타</c:when>
								</c:choose>
							</span>
							<span class="title">${vo.title}</span>
							<span class="name">${vo.id}</span>
							<span class="regdate">${vo.regdate}</span>
						</div>
					</div>
					<hr>
					<div style="clear:both;"></div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>작성된 상품 문의글이 없습니다.</p>
			</c:otherwise>	
		</c:choose>
	</div>
	
	<c:choose>
	<c:when test="${vo.soldout>0}"><c:set var="cpcol" value="${pageContext.request.contextPath}/iteminfo?colnum=${vo.colnum}"/></c:when>
	<c:otherwise><c:set var="cpcol" value="${pageContext.request.contextPath}/iteminfo?colnum=${vo.colnum}&soldout=soldout"/></c:otherwise>
	</c:choose>
	<div id="paging">
		<c:if test="${not empty pageCountInq}">
			<c:if test="${startPageNumInq!=1}">
				<a href="${cpcol}&pageNumInq=${startPageNumInq-1}">이전</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPageNumInq}" end="${endPageNumInq}">
				<c:choose>
					<c:when test="${pageNumInq==i}">
						<a href="${cpcol}&pageNumInq=${i}" style="color:red;">[${i}]</a>
					</c:when>
					<c:otherwise>
						<a href="${cpcol}&pageNumInq=${i}">[${i}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		
			<c:if test="${endPageNumInq!=pageCountInq}">
				<a href="${cpcol}&pageNumInq=${endPageNumInq+1}">다음</a>
			</c:if>
		</c:if>
	</div>
</div>





<script type="text/javascript">
	function showInquiryReg(id,colnum){
		if(id==null){
			alert("로그인 후 이용가능합니다.");
		}else{
			location.href="/KILA/inquiry/registration?colnum="+colnum;
		}
	}
	

	
	
	var inqxhr=null;
	var iNum, mtype;
	function showInqContent(inum,type){
		iNum=inum;
		mtype=type;
		inqxhr=new XMLHttpRequest();
		inqxhr.onreadystatechange=showInqContentOk;
		inqxhr.open('get','productInfo/inquiry/content?inum='+inum,true);
		inqxhr.send();
	}
	function showInqContentOk(){
		if(inqxhr.readyState==4 && inqxhr.status==200){
			var data=inqxhr.responseText;
			var contentList=document.getElementById("contentList"+iNum);
			var json=JSON.parse(data)[0];
			var child=contentList.childNodes;
			if(child.length<4){
				for(var i=0; i<json.length; i++){
					var div=document.createElement("div");
					var str="";
					if(i==0){
						str="<img src='/KILA/images/questionmark.png'>"
							+"<div class='content'>"+json[i].content+"<br>";
						if(mtype=="A" && json.length==1){
							str+="<div style='text-align:right'><input type='button' value='답글달기' onclick='showReplyForm("+iNum+")'></div>";
						}
						str+="</div>";
					}else{
						str+="<img src='/KILA/images/exclamationmark.png'>"
							+"<div class='content'>"
							+ json[i].regdate +"<br>"
							+ json[i].content +"</div>"
					}
					div.innerHTML=str;
					div.className="inqContent";
					contentList.appendChild(div);
				}
			}else{
				while(contentList.childNodes.length>3){
					contentList.removeChild(contentList.lastChild);
				}
			}
		}
	}
	
	
	
	function Request(){
		var requestParam ="";
		this.getParameter = function(param){
			var url = unescape(location.href);
			var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&");
			for(var i = 0 ; i < paramArr.length ; i++){
				var temp = paramArr[i].split("="); //파라미터 변수명을 담음
				if(temp[0].toUpperCase() == param.toUpperCase()){
					requestParam = paramArr[i].split("=")[1];
					break;
				}
			}
		return requestParam;
		}
	}
	
	
	function showReplyForm(inum){
		var contentList=document.getElementById("contentList"+inum);
		var child=contentList.childNodes;
		if(child.length<5){
			var request=new Request();
			var colnum=request.getParameter("colnum");
			var div=document.createElement("div");
			div.innerHTML="<form method='post' action='/KILA/inquiry/registration'><textarea name='content'></textarea><br>"
						+"<input type='hidden' name='inum' value='"+inum+"'>"
						+"<input type='hidden' name='colnum' value='"+colnum+"'>"
						+"<div style='text-align:right;'><input type='submit' value='답변완료'></div></form>";
			div.className="replyForm";
			contentList.appendChild(div);
		}
	}
	
	
	function checkMyInq(id,colnum){
		var cmi=document.getElementById("cmi");
		if(id==null){
			alert("로그인 후 이용가능합니다.");
			cmi.checked=false;
		}else{
			var at=document.getElementsByName("answerType")[0].value;
			var it=document.getElementsByName("inquiryType")[0].value;
			if(cmi.checked==true){
				location.href="/KILA/iteminfo?colnum="+colnum+"&at="+at+"&it="+it+"&cid="+id;
			}else{
				location.href="/KILA/iteminfo?colnum="+colnum+"&at="+at+"&it="+it;
			}
		}
	}
	
	function inqOption(colnum){
		var at=document.getElementsByName("answerType")[0].value;
		var it=document.getElementsByName("inquiryType")[0].value;
		location.href="/KILA/iteminfo?colnum="+colnum+"&at="+at+"&it="+it;
	}
	
	
	window.onload=function(){
		var request=new Request();
		var at=request.getParameter("at");
		if(at!=null && at!=""){
			document.getElementById("inquiry").scrollIntoView();
		}
		var cid=request.getParameter("cid");
		if(cid!=null && cid!=""){
			document.getElementById("inquiry").scrollIntoView();
		}
		
	}
	
</script>


