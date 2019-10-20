<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<style type="text/css">

 
.ui-slider-popup {
    width: 64px;
    height: 64px;
    text-align: center;
    font-size: 36px;
    z-index: 100;
    opacity: 0.8;
}

</style>



<link rel="stylesheet" href="https://demos.jquerymobile.com/1.4.2/css/themes/default/jquery.mobile-1.4.2.min.css">  
<script src="https://demos.jquerymobile.com/1.4.2/js/jquery.js"></script>
<script src="https://demos.jquerymobile.com/1.4.2/js/jquery.mobile-1.4.2.min.js"></script>


<div data-role="rangeslider">
	<input type="range" name="range" id="range-1a" step="1000" min="0" max="1000000" value="0" data-popup-enabled="true" data-show-value="true">
	<input type="range" name="range" id="range-1b" step="1000" min="0" max="1000000" value="1000000" data-popup-enabled="true" data-show-value="true">
</div>


</body>
</html>