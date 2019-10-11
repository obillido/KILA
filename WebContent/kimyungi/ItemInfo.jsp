<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>기본 상품정보페이지</h1>
<div class="sample_image">
<img src="/KILA/images/1.png">
</div>
<div class="wrap">
   <b>KILA|${vo.pcode }</b>
   <h2>${vo.pname }</h2>
   가격:<span style='color:red'>${vo.price }</span><br>
   색상:${vo.color }<br>
   사이즈:${vo.psize }<br>
   수량:${vo.icnt }
</div>