<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
    *{margin:0px;padding:0px;}
    #div{padding:150px 0px 0px 400px;clear:both;text-align:center;}
    td{text-align:center;}
    textarea{background-color:#FFE65A;font-size:2em;}
</style>

<div id="div">
<table border="2" width="1100" style="background-color:#FFE65A;">
   <tr>
      <td>제목</td>
      <td>${vo.title }</td>
   </tr>
   <tr>
      <td>내용</td>
      <td><textarea rows="5" cols="50" readonly="readonly">${vo.content }</textarea></td>
   </tr>
   <tr>
      <td>이미지</td>
      <td><img src="${pageContext.request.contextPath }/eventUploaded/${vo.orgfilename}" width="600" height="600"></td>
   </tr>
</table>
</div>

