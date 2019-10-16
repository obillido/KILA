<%@page import="kila.vo.EventVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kila.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
   *{box-sizing:border-box;}
   *{margin:0px;padding:0px;}
   img{vertical-align:middle;}
   body {font-family: Verdana, sans-serif; margin:0}
   .mySlides {display: none;}

   .slideshow-container{margin-top:30px;border:border:1px solid #dcdcdc;}

   .prev, .next {
       cursor: pointer;
       position: absolute;
       top: 40%;
       width: auto;
       padding: 16px;
       margin-top: -22px;
       color: white;
       font-weight: bold;
       font-size: 18px;
       transition: 0.6s ease;
       border-radius: 0 3px 3px 0;
   }
   
   .next {right: 0;border-radius: 3px 0 0 3px;}
   
   .prev:hover, .next:hover {background-color: rgba(0,0,0,0.8);}
   
   .numbertext {
       color: #f2f2f2;
       font-size: 12px;
       padding: 8px 12px;
       position: absolute;
       top: 0;
   }
   
   .text {
       color: #323232;
       font-size: 15px;
       text-align: center;
       font-weight:900;
   }
   
   .dot {
       cursor: pointer;
       height: 15px;
       width: 15px;
       margin: 0 2px;
       background-color: #bbb;
       border-radius: 50%;
       display: inline-block;
       transition: background-color 0.6s ease;
   }
   
   .active, .dot:hover {background-color: #717171;}
   
   .fade {
       -webkit-animation-name: fade;
       -webkit-animation-duration: 1.5s;
       animation-name: fade;
       animation-duration: 1.5s;
   }

   @-webkit-keyframes fade {
       from {opacity: .4} 
       to {opacity: 1}
   }

   @keyframes fade {
       from {opacity: .4} 
       to {opacity: 1}
   }

   @media only screen and (max-width: 300px) {
       .prev, .next,.text {font-size: 11px}
   }
</style>



<script type="text/javascript">
var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}


var slideIndex = 0;
showSlides();

function showSlides() {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
    for (i = 0; i < slides.length; i++) {
       slides[i].style.display = "none";  
    }
    slideIndex++;
    if (slideIndex > slides.length) {slideIndex = 1}    
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex-1].style.display = "block";  
    dots[slideIndex-1].className += " active";
    setTimeout(showSlides, 3000); 
}
</script>



<div class="slideshow-container">
<%
EventDao dao=new EventDao();
ArrayList<EventVo> list=dao.list();
for(EventVo ev:list){
%>
   <div class="mySlides fade">
      <div class="numbertext"></div>
      <img src="${pageContext.request.contextPath }/eventUploaded/<%=ev.getOrgfilename() %>" style="width:100%;height:500px;">
      <div class="text"><%=ev.getContent() %></div>
   </div>
<%
}
%>
<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
<a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
<br>

<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span> 
  <span class="dot" onclick="currentSlide(2)"></span> 
  <span class="dot" onclick="currentSlide(3)"></span> 
  <span class="dot" onclick="currentSlide(4)"></span> 
</div>
