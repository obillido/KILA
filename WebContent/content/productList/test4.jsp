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

 
form  {
    margin: 60px 0 0 0 !important;
 }
 
.ui-slider .ui-btn-inner {
    padding: 4px 0 0 0 !important;
}
 
.ui-slider-popup {
    position: absolute !important;
    width: 64px;
    height: 64px;
    text-align: center;
    font-size: 36px;
    padding-top: 14px;
    z-index: 100;
    opacity: 0.8;
}
</style>



<head>
<link rel="stylesheet" href="https://demos.jquerymobile.com/1.4.2/css/themes/default/jquery.mobile-1.4.2.min.css">  
<script src="https://demos.jquerymobile.com/1.4.2/js/jquery.js"></script>
<script src="https://demos.jquerymobile.com/1.4.2/js/jquery.mobile-1.4.2.min.js"></script>
</head>

<form>
    <div data-role="rangeslider">
        <input type="range" name="range-1a" id="range-1a" min="0" max="100" value="40" data-show-value="true">
        <input type="range" name="range-1b" id="range-1b" min="0" max="100" value="80" data-show-value="true">
    </div>
</form>
  
  
  

</body>
</html>