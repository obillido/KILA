<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	#inquiry{
		margin:0px;
		margin-top:100px;
		width:100%;
		float:left;
	}

</style>

<div id="inquiry">
	<h1>문의하기</h1>
	<br><br>
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
		
		<div style="float:right;">
			<input type="button" value="문의하기" name="inquiryButton">
		</div>
	</div>
</div>



