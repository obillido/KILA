<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script type="text/javascript">
 
function getTotRev(){
	var span=document.getElementById("getTotRev");
	span.innerHTML="총 매출은 " + ${tot} + "입니다.";
}


window.onload=function(){
	if(${tot3!=null}){     
	var span=document.getElementById("getProductRev");
	span.innerHTML="설정한 상품별 매출은 " + ${tot3} + "입니다.";
	}
	
	if(${tot2!=null}){
	var span=document.getElementById("getPeriodRev");
	span.innerHTML="설정한 기간별 매출은 " + ${tot2} + "입니다.";
	}
}
</script>


<style type="text/css">
   #div{margin-top:100px;margin-left:700px;margin-right:700px;clear:both;text-align:center;height:400px;border:1px solid black;}
   input[type=button]{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;height:30px;width:120px;}
   input[type=submit]{background-color:#5A5AFF;color:white;border:2px solid #CBFF75;height:30px;width:120px;}
   input[type=text]{width:80px;height:20px;background-color:#C8FFFF;}
</style>


<div id="div">
<div>
   <br><br><br>
   <input type="button" value="총 매출 보기" onclick="getTotRev()">
   <span id="getTotRev" style="color:blue;font-size:14px"></span>
</div>
<br><br><br>

<form method="post" action="${pageContext.request.contextPath }/header/salesRevenue">
     시작일 <input type="text" name="startdate">
    ~ 종료일 <input type="text" name="enddate">
    <input type="submit" value="기간별 매출 확인">
   <br>예) 날짜를 191015와 같은 형태로 입력하시오.
   <span id="getPeriodRev" style="color:blue;font-size:14px"></span>
</form>
<br><br><br>

<form method="post" action="${pageContext.request.contextPath }/header/salesRevenue2">
    상품번호 <input type="text" name="pnum">
   <input type="submit" value="상품별 매출 확인">
   <span id="getProductRev" style="color:blue;font-size:14px"></span>
</form>
</div>
