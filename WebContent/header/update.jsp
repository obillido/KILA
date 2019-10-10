<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update.jsp</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath }/header/update">
<table>
   <tr>
      <th>아이디</th>
      <td><input type="text" name="id" value="${sessionScope.id}" readonly></td>
   </tr>
   <tr>
      <th>비밀번호</th>
      <td><input type="text" name="pwd" value="${sessionScope.pwd}"></td>
   </tr>
   <tr>
      <td colspan="2">
         <input type="submit" value="수정완료">
      </td>
   </tr>
</table>
</form>
<a href="${pageContext.request.contextPath }/home">홈으로</a>
</body>
</html>