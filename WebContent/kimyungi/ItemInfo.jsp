<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	var sig=false;
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
		document.getElementById("btn1").disabled = false;
		btn2=document.getElementById("btn2").disabled = false;
		cntsr.style.display="block";
		var vcnt=document.getElementById("vcnt");
		var sps=document.getElementById("sps");
		vcnt.value=icnt;
		sps.value=psize;
	}
	function rinsert(id){
		if(id==""){
			alert("회원만 리뷰가 가능합니다.");
		}
	}
	function logcheck(id){
		if(id==""){
			alert("로그인을 해주셔야 결제가 가능합니다.");
			return false;
		}else{
			return true;
		}
	}

</script>
<div style="text-align: center;">
<div style="text-align: center; display: inline-block;">
<h1>기본 상품정보페이지</h1><br>
<div>
<div style="width: 400px;height: 500px; float: left;">
<img id="img1" src="${pageContext.request.contextPath }/upload/${vo.savefilename }" style="width: 350px;height: 400px">
</div>
<div id="zoom" style="float: right; width: 300px;height: 300px; text-align: left;">
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
   <div id=cntsr style="display: none;">
   재고:<input type="text" id="vcnt" readonly="readonly" style="border-style: none; font-size: 16px;width:30px">
   (<input type="text" id="sps" name="spsize" readonly="readonly"  style="border-style: none; font-size: 16px;width:30px">)<br><br><hr>
   </div>
   <div style="text-align: center;"><br>
   <input type="button" value="-" id="btnminus" onclick="decreaseValue()"><input type="text" id="count" name="pcnt" value=1 readonly="readonly" style="border-style: none; text-align: center;"><input type="button" id="btnplus" value="+" onclick="IncreaseValue()"></div><br>
   <input type="submit" value="CART" id="btn1" style="width: 145px; height: 40px" disabled="disabled" formaction="${pageContext.request.contextPath }/home">
   <input type="submit" value="BUY" id="btn2" style="width: 145px; height: 40px" disabled="disabled">
   </form>
</div>
</div>
</div>
<div>
<span style="font-size: 15px">REVIEW | 문의글 혹은 악의적인 비방글은 무통보 삭제된다는 점 유의해주세요^^</span><br>
<textarea rows="10" cols="93"onKeyUp="javascript:fnChkByte(this,'500')" <%if(session.getAttribute("id")==null) {%> disabled="disabled"<%} %>></textarea>
 <br><c:forEach begin="1" end="105">&nbsp</c:forEach><span id="byteInfo">0</span>/500bytes<br>
 <select style="width: 450px; height: 35px; font-size: 20px; display: inline-block;">
 	<option value="5">★★★★★아주 좋아요!</option>
 	<option value="4">★★★★☆맘에 들어요.</option>
 	<option value="3">★★★☆☆보통이에요...</option>
 	<option value="2">★★☆☆☆그냥 그래요...</option>
 	<option value="1">★☆☆☆☆별로에요...</option>
 </select>
 <button type="button" style="background-color:pink; width: 200px" onclick="rinsert('${id}')"><img src="${pageContext.request.contextPath }/upload/pencil.png" width="30px" height="25px">리뷰등록하기</button>
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
 </script> 
</div>
</div>