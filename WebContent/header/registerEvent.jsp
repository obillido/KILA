<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="post" action="${pageContext.request.contextPath }/header/registerEventOk.jsp" enctype="multipart/form-data">
   제목 <input type="text" name="title"><br>
   내용<br>
   <textarea rows="5" cols="50" name="content"></textarea><br>
   첨부파일 <input type="file" name="file"><br>
   <input type="submit" value="등록">
   <input type="reset" value="취소">
</form>