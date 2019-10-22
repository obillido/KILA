<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">

.img-zoom-container {
  position: relative;
}

.img-zoom-lens {
  position: absolute;
  width: 150px;
  height: 150px;
}

</style>
<script type="text/javascript">
	var sig=false;
	var xhrList=null;
	function getList(){
		xhrList=new XMLHttpRequest();
		xhrList.onreadystatechange=listOk;
		xhrList.open('get','/kila/review?colnum=${vo.colnum}&cmd=check',true );
		xhrList.send();
	}
	function listOk(){
		if(xhrList.readyState==4 && xhrList.status==200){
			removeComm();
			var data=xhrList.responseText;
			//[[{"comments":"bbbb","num":6,"mnum":1,"id":"bb"}]]	
			var list=JSON.parse(data)[0];
			var commList=document.getElementById("commList");
			for(var i=0;i<list.length;i++){
				var str="아이디:" + list[i].id +"<br>" +
				        "내용:" + list[i].comments +"<br>"+
				"<a href='javascript:delComm("+list[i].num+")'>삭제</a>";
				var div=document.createElement("div");
				div.innerHTML=str;
				div.className="comm";
				commList.appendChild(div);
			}
		}
	}
	function IncreaseValue(){
		if(sig==true){
			var cnt=document.getElementById("count");
			var vcnt=document.getElementById("vcnt");
			if(cnt.value==vcnt.value){
				alert("주문 수량은 재고를 넘길수 없습니다.");
			}else{
				cnt.value++;
			}
		}else{
			alert("사이즈를 우선 골라주세요.")
		}
	}
	function decreaseValue(){
		if(sig==true){
			var cnt=document.getElementById("count");
			if(cnt.value!=1)
			cnt.value--;
		}else{
			alert("사이즈를 우선 골라주세요.")
		}
	}
	function viewcnt(icnt,psize){
		sig=true;
		var cntsr=document.getElementById("cntsr");
		var cnt=document.getElementById("count");
		cnt.value=1;
		document.getElementById("btn1").disabled = false;
		btn2=document.getElementById("btn2").disabled = false;
		cntsr.style.display="block";
		var vcnt=document.getElementById("vcnt");
		var sps=document.getElementById("sps");
		vcnt.value=icnt;
		sps.value=psize;
	}
	function rinsert(id){
		var rev=document.getElementById("rev");
		if(id==""){
			alert("회원만 리뷰가 가능합니다. 그러므로 로그인!");
			location.href="/KILA/header/login";
		}else if(rev==null){
			alert("구매한 상품만 리뷰가 가능합니다. 경고했습니다!");
		}
	}
	function rinsert2(id,colnum){
		var rev=document.getElementById("rev");
		if(id==""){
			alert("회원만 리뷰가 가능합니다. 그러므로 로그인으로 이동");
			location.href="/KILA/header/login";
			return false;
		}else if(rev==null){
			alert("구매한 상품만 리뷰가 가능합니다. 포기하세요!");
			return false;
		}
		return true;
	}
	function logcheck(id){
		if(id==""){
			alert("회원만 구매 및 장바구니 이용이 가능합니다. 그러므로 로그인");
			location.href="/KILA/header/login";
			return false;
		}else{
			return true;
		}
	}
	function imageZoom(imgID, resultID) {
		  var img, lens, result, cx, cy;
		   img = document.getElementById(imgID);
		  result =  document.getElementById(resultID);
		  lens =  document.createElement("DIV");
		  lens.setAttribute("class", "img-zoom-lens");
		  img.parentElement.insertBefore(lens, img);
		  cx =  result.offsetWidth / lens.offsetWidth;
		  cy = result.offsetHeight /  lens.offsetHeight;
		   result.style.backgroundImage = "url('" + img.src + "')";
		   result.style.backgroundSize = (img.width * cx) + "px " + (img.height * cy) + "px";
		  lens.addEventListener("mousemove", moveLens);
		   img.addEventListener("mousemove", moveLens);
		  lens.addEventListener("touchmove", moveLens);
		   img.addEventListener("touchmove", moveLens);
		  function moveLens(e) {
		     var pos, x, y;
		    e.preventDefault();
		     pos = getCursorPos(e);
		    x = pos.x - (lens.offsetWidth / 2);
		     y = pos.y - (lens.offsetHeight / 2);
		    if (x >  img.width - lens.offsetWidth) {x = img.width - lens.offsetWidth;}
		     if (x < 0) {x = 0;}
		    if (y > img.height -  lens.offsetHeight) {y = img.height - lens.offsetHeight;}
		     if (y < 0) {y = 0;}
		     lens.style.left = x + "px";
		    lens.style.top = y + "px";
		     result.style.backgroundPosition = "-" + (x * cx) + "px -" + (y * cy) + "px";
		   }
		  function getCursorPos(e) {
		    var a, x = 0, y =  0;
		    e = e || window.event;
		    a =  img.getBoundingClientRect();
		    x = e.pageX  - a.left;
		    y = e.pageY - a.top;
		    x = x -  window.pageXOffset;
		    y = y - window.pageYOffset;
		     return {x : x, y : y};
		  }
	}
	function zoomup(){
		var div=document.getElementById("zoom");
		div.style.zIndex=3;
	}
	function zoomdown(){
		var div=document.getElementById("zoom");
		div.style.zIndex=1;
	}
	function viewinfo(i){
		var vi=document.getElementsByClassName("vi")[i];
		if(vi.style.display=="none"){
			vi.style.display="block";
			sig=true;
		}else{
			vi.style.display="none";
			sig=false;	
		}
	}
</script>
<div style="text-align: center; margin-top:50px; min-width:1000px;">
<div style="text-align: center; display: inline-block;">
<div style="text-align: center; display: inline-block;">
<br>
<br>
<div style="left:100px">
<div style="width: 450px;height: 550px; float: left;">
<img id="img1" src="${pageContext.request.contextPath }/upload/${vo.savefilename }" style="position:static; width: 400px;height: 450px" onmouseover="zoomup()" onmouseout="zoomdown()">
</div>
<div style="width: 400px;height: 500px; position: relative; float: right;">
<div id="zoom" class="img-zoom-result" style="width: 300px;height: 300px; position: absolute; z-index: 1">
</div>
<div style="width: 300px;height: 300px; position: absolute; background-color:white; z-index:2; text-align: left;">
	<b>KILA|${vo.pcode }</b><br><hr><br>
   <h2>${vo.pname }</h2><br><hr><br>
   가격:<span style='color:red'>${vo.price }</span><br><br><hr><br>
   색상:${vo.color }<br><br><hr><br>
   사이즈: <c:forEach var="si" items="${list }">
   		<a href="javascript:viewcnt(${si.icnt },${si.psize })" style="text-decoration: none;">${si.psize}</a>	
   			</c:forEach>
   		<br><br><hr><br>
   <form method="post" action="${pageContext.request.contextPath }/kila/payment" onsubmit="return logcheck('${id}')">
   <input type="hidden" name="scolnum" value=${vo.colnum }>
   <input type="hidden" name="cmd" value="insert">
   <div id=cntsr style="display: none;">
   재고:<input type="text" id="vcnt" readonly="readonly" style="border-style: none; font-size: 16px;width:30px">
   (<input type="text" id="sps" name="spsize" readonly="readonly"  style="border-style: none; font-size: 16px;width:30px">)<br><br><hr>
   </div>
   <div style="text-align: center;"><br>
   <input type="button" value="-" id="btnminus" onclick="decreaseValue()"><input type="text" id="count" name="pcnt" value=1 readonly="readonly" style="border-style: none; text-align: center;"><input type="button" id="btnplus" value="+" onclick="IncreaseValue()"></div><br>
   <c:if test="${empty soldout}">
	   <input type="submit" value="CART" id="btn1" style="width: 145px; height: 40px" disabled="disabled" formaction="${pageContext.request.contextPath }/kila/cart">
	   <input type="submit" value="BUY" id="btn2" style="width: 145px; height: 40px" disabled="disabled">
   </c:if>
   <c:if test="${type eq 'A'}">
 
		<br><br>
   		<input type="button" value="재고 채우기" style="width:145px; height:40px;"
   			onclick="location.href='${pageContext.request.contextPath}/product/productRegi?cmd=old&colnum=${vo.colnum}'">
   </c:if>
   </form>
</div>
</div>
</div>
</div>
<div style="margin-top:50px;">
<hr><br><br>
<span style="font-size: 15px">REVIEW | 문의글 혹은 악의적인 비방글은 무통보 삭제된다는 점 유의해주세요^^</span>
<form method="post" action="${pageContext.request.contextPath }/kila/reviewReg" onsubmit="return rinsert2('${id},${vo.colnum }')" enctype="multipart/form-data">
<div style=" display: inline-block;">
<c:if test="${not empty review}">
<br>
작성하실 제품: <select name="paynum" id="rev" style="width: 200px; height: 25px; display: inline-block;">
	<c:forEach var="re" items="${review}">
	<option value="${re.paynum }">Color:${re.color }, Size:${re.size }</option>
</c:forEach>
</select><c:forEach begin="1" end="63">&nbsp</c:forEach>
</c:if>
</div><br>
<input type="hidden" name="colnum" value="${vo.colnum }">
<input type="hidden" name="cmd" value="insert">
<textarea name="content" rows="10" cols="93"onKeyUp="javascript:fnChkByte(this,'500')" onkeypress="rinsert('${id}')" onclick="rinsert('${id}')"></textarea>
 <br><input type="file" name="file1"> <c:forEach begin="1" end="57">&nbsp</c:forEach><span id="byteInfo">0</span>/500bytes<br>
 <select name="rpoint" style="width: 450px; height: 35px; font-size: 20px; display: inline-block;">
 	<option value="5">★★★★★아주 좋아요!</option>
 	<option value="4">★★★★☆맘에 들어요.</option>
 	<option value="3">★★★☆☆보통이에요...</option>
 	<option value="2">★★☆☆☆그냥 그래요...</option>
 	<option value="1">★☆☆☆☆별로에요...</option>
 </select>
 <button type="submit" style="background-color:pink; width: 200px"><img src="${pageContext.request.contextPath }/upload/pencil.png" width="30px" height="25px">리뷰등록하기</button>
</form>
<br>
<div style="text-align: left;">
<c:choose><c:when test="${ch == 'rnum'}"><b>등록순</b></c:when><c:when test="${ch =='rpoint' }"><b>평점순</b></c:when></c:choose>
 리뷰<c:if test="${not empty rlist.size()}">(${rlist.size() })</c:if> | <a href="${pageContext.request.contextPath }/iteminfo?colnum=${vo.colnum }&ch=rnum" style="text-decoration: none;">등록순</a>
| <a href="${pageContext.request.contextPath }/iteminfo?colnum=${vo.colnum }&ch=rpoint" style="text-decoration: none;">평점순</a>
<hr>
</div>
<c:choose>
	<c:when test="${not empty rlist}">
	<c:set var="i" value="1"/>
	<c:set var="sum" value="0"/>
	<c:forEach var="rl" items="${rlist }">
	<div>
	<div style="width: 750px; display: inline-block; text-align: left;" onclick="viewinfo(${sum})">
	<c:set var="sum" value="${sum+i }"/>
	<br>
	<div style="float: left; width: 500px">
		<c:choose>
			<c:when test="${rl.rpoint eq 5 }">
				★★★★★ - 아주 좋아요!
			</c:when>
			<c:when test="${rl.rpoint eq 4 }">
				★★★★☆ - 맘에 들어요!
			</c:when>
			<c:when test="${rl.rpoint eq 3 }">
				★★★☆☆ - 보통이에요...
			</c:when>
			<c:when test="${rl.rpoint eq 2 }">
				★★☆☆☆ - 그냥 그래요...
			</c:when>
			<c:when test="${rl.rpoint eq 1 }">
				★☆☆☆☆ - 별로에요...
			</c:when>
		</c:choose><br><br>
		<div style="border: 2px solid grey; width: 500px; height: 80px; padding: 20px; display: none;" class="vi">
			<h3>선택한 옵션</h3><br>
			사이즈:${rl.size }<br>
			컬러:${rl.color }<br>
		</div>
		<br><b>${rl.content }</b><br><br><br>
		<c:choose>
			<c:when test="${not empty rl.savefilename}">
				<img src="${pageContext.request.contextPath }/upload/${rl.savefilename }" style="width: 150px; height: 150px">
			</c:when>
			<c:otherwise>
				이미지가 없는 리뷰입니다.
			</c:otherwise>
		</c:choose>
	</div>
	<div style="float: right; padding: 20px; width: 150px; height: 100%;">
		<br>작성자<br>
		${rl.id }<br>
		<hr>
		작성일<br>
		${rl.regdate }<br>
		<hr>
	</div>
	</div>
	</div>
	<hr style="width: 750px; display: inline-block;">
	<br>
	<br>
	</c:forEach>
	<div>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${pageContext.request.contextPath }/iteminfo?pageNum=${i}&ch=${ch}&colnum=${vo.colnum }" style=" text-decoration: none;">
				<b><span style='color:deepred; font-size: 30px; text-decoration: none;'>[${i }]</span></a></b>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath }/iteminfo?pageNum=${i}&ch=${ch}&colnum=${vo.colnum }" style=" text-decoration: none;">
				<b><span style='color:#999; font-size: 25px;'>[${i }]</span></a></b>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</div>
	</c:when>
	<c:otherwise>
	<br>
		<div style="text-align: left;">작성된 상품 리뷰가 없습니다. 첫번째 리뷰의 주인공이 되어주세요.<br><br></div>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
 function fnChkByte(obj, maxByte)
 {
     var str = obj.value;
     var str_len = str.length;
     var rbyte = 0;
     var rlen = 0;
     var one_char = "";
     var str2 = "";
     for(var i=0; i<str_len; i++)
     {
         one_char = str.charAt(i);
         if(escape(one_char).length > 4)
         {
             rbyte += 3;//한글3Byte
         }
         else
         {
             rbyte++;//영문 등 나머지 1Byte
         }
         if(rbyte <= maxByte)
         {
             rlen = i+1;//return할 문자열 갯수
        }
      }
      if(rbyte > maxByte)
      {
   alert("리뷰의 길이는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
   str2 = str.substr(0,rlen);                                  //문자열 자르기
  obj.value = str2;
   fnChkByte(obj, maxByte);
      }
      else
      {
         document.getElementById('byteInfo').innerText = rbyte;
      }
 }
imageZoom("img1", "zoom");
 </script> 
</div>


<div style="display: inline-block;" id="inquiryPage">
<jsp:include page="/content/inquiry/inquiry.jsp"/>
</div>
</div>
</div>

	