<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {box-sizing: border-box;}

.img-zoom-container {
  position: relative;
}

.img-zoom-lens {
  position: absolute;
  border: 1px solid #d4d4d4;
  /*set the size of the lens:*/
  width: 150px;
  height: 150px;
}

.img-zoom-result {
  border: 1px solid #d4d4d4;
  /*set the size of the result div:*/
  width: 300px;
  height: 300px;
  float: right;
} 
</style>
<script type="text/javascript">
function imageZoom(imgID, resultID) {
	  var img, lens, result, cx, cy;
	   img = document.getElementById(imgID);
	  result =  document.getElementById(resultID);
	  /* Create lens: */
	  lens =  document.createElement("DIV");
	  lens.setAttribute("class", "img-zoom-lens");
	   /* Insert lens: */
	  img.parentElement.insertBefore(lens, img);
	   /* Calculate the ratio between result DIV and lens: */
	  cx =  result.offsetWidth / lens.offsetWidth;
	  cy = result.offsetHeight /  lens.offsetHeight;
	  /* Set background properties for the result DIV */
	   result.style.backgroundImage = "url('" + img.src + "')";
	   result.style.backgroundSize = (img.width * cx) + "px " + (img.height * cy) + "px";
	   /* Execute a function when someone moves the cursor over the image, or the lens: */
	  lens.addEventListener("mousemove", moveLens);
	   img.addEventListener("mousemove", moveLens);
	   /* And also for touch screens: */
	  lens.addEventListener("touchmove", moveLens);
	   img.addEventListener("touchmove", moveLens);
	  function moveLens(e) {
	     var pos, x, y;
	    /* Prevent any other actions that may occur when moving over the image */
	    e.preventDefault();
	    /* Get the cursor's x and y positions: */
	     pos = getCursorPos(e);
	    /* Calculate the position of the lens: */
	    x = pos.x - (lens.offsetWidth / 2);
	     y = pos.y - (lens.offsetHeight / 2);
	    /* Prevent the lens from being positioned outside the image: */
	    if (x >  img.width - lens.offsetWidth) {x = img.width - lens.offsetWidth;}
	     if (x < 0) {x = 0;}
	    if (y > img.height -  lens.offsetHeight) {y = img.height - lens.offsetHeight;}
	     if (y < 0) {y = 0;}
	    /* Set the position of the lens: */
	     lens.style.left = x + "px";
	    lens.style.top = y + "px";
	     /* Display what the lens "sees": */
	     result.style.backgroundPosition = "-" + (x * cx) + "px -" + (y * cy) + "px";
	   }
	  function getCursorPos(e) {
	    var a, x = 0, y =  0;
	    e = e || window.event;
	    /* Get the x and y positions of the image: */
	    a =  img.getBoundingClientRect();
	    /* Calculate the cursor's x and y coordinates, relative to the image: */
	    x = e.pageX  - a.left;
	    y = e.pageY - a.top;
	     /* Consider any page scrolling: */
	    x = x -  window.pageXOffset;
	    y = y - window.pageYOffset;
	     return {x : x, y : y};
	  }
	} 
</script>
</head>
<body>
<div class="img-zoom-container">
  <img id="myimage" src="upload/1.jpg" width="600" height="480">
  <div id="myresult" class="img-zoom-result"></div>
</div>
<script>
// Initiate zoom effect:
imageZoom("myimage", "myresult");
</script>
</body>
</html>