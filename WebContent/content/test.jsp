<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--
*	UI 개발
*	Tab Menu 제작
*	제작자 : 박경두
*	최종 수정일 : 2016-07-21
*
*
*
*
-->
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>tab example</title>
	<style>
		#container {
			width:960px;
			margin:0 auto;
			text-align:center;
		}
		.tab {
			list-style: none;
			margin: 0;
			padding: 0;
			overflow: hidden;
		}
		/* Float the list items side by side */
		.tab li {
			float: left;
		}
		/* Style the links inside the list items */
		.tab li a {
			display: inline-block;
			color: #000;
			text-align: center;
			text-decoration: none;
			padding: 14px 16px;
			font-size: 17px;
			transition:0.3s;
		}
		/* Style the tab content */
		.tabcontent {
			display: none;
			background-color:rgb(0,154,200);
			padding: 6px 12px;
			color:#fff;
		}
		ul.tab li.current{
			background-color: rgb(0,154,200);
			color: #222;
		}
		.tabcontent.current {
			display: block;
		}
	</style>
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<div id="container">
		
		<ul class="tab">
			<li class="current" data-tab="tab1"><a href="#">About</a></li>
			<li data-tab="tab2"><a href="#">Portfolio</a></li>
		</ul>

		<div id="tab1" class="tabcontent current">
			<h3>About</h3>
			<p>탭1</p>
		</div>

		<div id="tab2" class="tabcontent">
			<h3>Portfolio</h3>
			<p>탭2</p>
		</div>


	</div>

	<script>
		$(function() {
			$('ul.tab li').click(function() {
				var activeTab = $(this).attr('data-tab');
				$('ul.tab li').removeClass('current');
				$('.tabcontent').removeClass('current');
				$(this).addClass('current');
				$('#' + activeTab).addClass('current');
			})
		});
	</script>
</body>
</html>