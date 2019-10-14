<%@page import="kila.vo.EventVo"%>
<%@page import="java.io.File"%>
<%@page import="kila.dao.EventDao"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registerEventOk.jsp</title>
</head>
<body>
<%
   String path=application.getRealPath("/eventUploaded");
   MultipartRequest mr=new MultipartRequest(
		   request,
		   path,
		   1024*1024*5,
		   "utf-8",
		   new DefaultFileRenamePolicy()
  	   );
   String title=mr.getParameter("title");
   String content=mr.getParameter("content");
   String orgFileName=mr.getOriginalFileName("file");
   String saveFileName=mr.getFilesystemName("file");
   
   EventDao dao=new EventDao();
   File file=mr.getFile("file"); 
   long fileSize=file.length(); 
   EventVo vo=new EventVo(0,title,content,orgFileName,saveFileName,fileSize);
   dao.insert(vo);
%>
<h1>이벤트 작성 완료</h1>
업로드 경로: <%=path %><br>
<ul>
   <li>제목: <%=title %><br>
   <li>내용: <%=content %><br>
   <li>전송된 파일명: <%=orgFileName %><br>
   <li>저장된 파일명: <%=saveFileName %><br>
</ul>
</body>
</html>