<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<style type="text/css">
	#inquiry{
		text-align:center;
		margin-top:100px;
		float:left;
		width:800px;
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

</style>

<div id="inquiry">
	<h1>문의하기</h1>
	<br><br>
	<div style="text-align:left;">
	<div id="selectOption">
		<select name="answerType">
			<option value="0">전체답변</option>
			<option value="1">답변대기</option>
			<option value="2">답변완료</option>
		</select>
		<select name="inquiryType">
			<option value="0">전체</option>
			<option value="1">사이즈</option>
			<option value="2">배송</option>
			<option value="3">재입고</option>
			<option value="4">기타</option>
		</select>
		<input type="checkbox" name="chkMyInquiry" value="only"> 내 문의글만 보기
		<input type="button" value="문의하기" name="inquiryButton">
	</div>
	<hr>
	
	<div id="content">
	<c:choose>
		<c:when test="${not empty list}">
			<c:forEach var="vo" items="${list}">
				
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>작성된 상품 문의글이 없습니다.</p>
		</c:otherwise>	
	</c:choose>
	</div>
	
</div>



