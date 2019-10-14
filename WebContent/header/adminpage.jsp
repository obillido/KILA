<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminpage.jsp</title>
</head>
<body>
<div>
   <ul>
      <li><a href="">상품등록하기</a></li>
      <li><a href="">상품목록보기</a></li>
      <li><a href="${pageContext.request.contextPath }/header/registerEvent.jsp">이벤트작성하기</a></li>
      <li><a href="${pageContext.request.contextPath }/header/eventList.jsp">이벤트목록보기</a></li>
   </ul>
</div>
</body>
</html>