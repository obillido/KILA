<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KILA</title>

<style type="text/css">
    #wrap{width:100%;}
    
    #toTop {
position: fixed;
bottom: 30px;
margin-left: -150px;
}

#toTop a {
width: 108px;
display: block;
text-align: center;
font: 11px/100% Arial, Helvetica, sans-serif;
text-transform: uppercase;
text-decoration: none;
color: #bbb;

/* transition */
-webkit-transition: 1s;
-moz-transition: 1s;
transition: 1s;
}
#back-top a:hover {
color: #000;
}

/* arrow icon (span tag) */
#back-top span {
width: 108px;
height: 108px;
display: block;
margin-bottom: 7px;
background: #ddd url(up-arrow.png) no-repeat center center;

/* rounded corners */
-webkit-border-radius: 15px;
-moz-border-radius: 15px;
border-radius: 15px;

/* transition */
-webkit-transition: 1s;
-moz-transition: 1s;
transition: 1s;
}
#back-top a:hover span {
background-color: #777;
}
    
</style>

<script type="text/javascript">
$(document).ready(function(){

	// 일단 버튼을 숨긴다
	$("#toTop").hide();

	// 스크롤이 되면 버튼이 나타난다. 
	$(function () {
	   $(window).scroll(function () {
	        if ($(this).scrollTop() > 100) {
	              $('#toTop').fadeIn();
	         } else {
	              $('#toTop').fadeOut();
	         }
	});

	// 버튼 클릭하면, 맨 위로 이동!! 
	$('toTop a').click(function () {
	        $('body,html').animate({
	              scrollTop: 0
	         }, 800);
	         return false;
	        });
	});

	});
</script>

</head>
<body>
<%
	String cpage=request.getParameter("cpage");
	if(cpage==null) cpage="main.jsp";
%>
<div id="wrap">
	<jsp:include page="/header/header.jsp"/>

	<jsp:include page="<%=cpage %>"/>
   
	<jsp:include page="footer.jsp"/>
</div>

<p id="toTop">
   <a href="#top">Back To Top</a>
</p>
</body>
</html>