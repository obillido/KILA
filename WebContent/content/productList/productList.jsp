<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<style type="text/css">
	#content{
		width:100%;
		min-width:1220px;
	}
	
	a{text-underline-position:under;}

	#categoryList{
		float:left;
		width:130px;
		padding:10px 30px;
		line-height:30px;
	}
	#categoryList a{
		padding-left:10px;
		text-decoration:none;
		color:black;
	}
	#categoryList a:hover{
		color:red;
		text-decoration:underline;
	}
	#categoryList a:active{
		color:red;
		text-decoration:underline;
	}
	
	#productList{
		float:left;
		width:970px;
	}
	
	
	#order{
		float:left;
	}
	#order a{
		color:grey;
		text-decoration:none;
	}

	
	
	
	#search{
		float:right;
		width:100px; height:30px;
		margin-right:50px;
		background-color:white;
	}
	#searchWindow{
		width:90%;
		display:inline-block;
		float:left;
		line-height:50px;
		border:2px solid grey;
		margin:10px 20px;
	}
	#searchWindow label{
		display:inline-block;
		width:200px;
		text-align:center;
	}
	#searchWindow span{
		display:inline-block;
		width:90px;
	}
	#searchWindow input[type=checkbox] {
		display:inline-block;
		width:20px; height:20px;
	}
	#searchWindow input[type=text]{
		display:inline-block;
		width:100px; height:25px;
	}
	
	
	
	
	
	
	#list div[name=item]{
		float:left;
		width:220px; height:320px;
		margin-right:20px;
		margin-bottom:20px;
	}
	#list div[name=item] p{
		padding:2px 10px;
		color:black;
	}


	#paging{
		width:95%;
		text-align:center;
		font-size:30px;
	}
	#paging a{
		text-decoration:none;
		color:black;
	}
</style>


<%	String category=request.getParameter("category");	%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="cplist" value="${pageContext.request.contextPath}/product/list"/>


<div id="content">

	<div id="categoryList">
		<h1>카테고리</h1>
		<br>
	<%
		if(category==null || category.equals("all")){
	%>
		<a href="${cplist}?category=all" style="color:red; text-decoration:underline;">전체보기</a><br>
		<a href="${cplist}?category=down_jacket">다운자켓</a><br>
		<a href="${cplist}?category=jacket">자켓</a><br>
		<a href="${cplist}?category=longsleeves">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants">바지</a><br>
	<%
		}else if(category.equals("down_jacket")){
	%>
		<a href="${cplist}?category=all">전체보기</a><br>
		<a href="${cplist}?category=down_jacket" style="color:red; text-decoration:underline;">다운자켓</a><br>
		<a href="${cplist}?category=jacket">자켓</a><br>
		<a href="${cplist}?category=longsleeves">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants">바지</a><br>
	<%
		}else if(category.equals("jacket")){
	%>
		<a href="${cplist}?category=all">전체보기</a><br>
		<a href="${cplist}?category=down_jacket">다운자켓</a><br>
		<a href="${cplist}?category=jacket" style="color:red; text-decoration:underline;">자켓</a><br>
		<a href="${cplist}?category=longsleeves">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants">바지</a><br>
	<%
		}else if(category.equals("longsleeves")){
	%>
		<a href="${cplist}?category=all">전체보기</a><br>
		<a href="${cplist}?category=down_jacket">다운자켓</a><br>
		<a href="${cplist}?category=jacket">자켓</a><br>
		<a href="${cplist}?category=longsleeves" style="color:red; text-decoration:underline;">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants">바지</a><br>
	<%
		}else if(category.equals("shortsleeves")){
	%>
		<a href="${cplist}?category=all">전체보기</a><br>
		<a href="${cplist}?category=down_jacket">다운자켓</a><br>
		<a href="${cplist}?category=jacket">자켓</a><br>
		<a href="${cplist}?category=longsleeves">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves" style="color:red; text-decoration:underline;">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants">바지</a><br>
	<%
		}else if(category.equals("pants")){
	%>
		<a href="${cplist}?category=all">전체보기</a><br>
		<a href="${cplist}?category=down_jacket">다운자켓</a><br>
		<a href="${cplist}?category=jacket">자켓</a><br>
		<a href="${cplist}?category=longsleeves">긴팔티셔츠</a><br>
		<a href="${cplist}?category=shortsleeves">반팔티셔츠</a><br>
		<a href="${cplist}?category=pants" style="color:red; text-decoration:underline;">바지</a><br>
	<%
		}
	%>
	</div>
	




	<div id="productList">
		<h1>상품목록</h1>
		<br>
		<div id="order">
		<c:choose>
			<c:when test="${empty order || order==1}">
				<a href="${cplist}?category=${category}&order=1" style="text-decoration:underline;">판매순</a>
				<a href="${cplist}?category=${category}&order=2">신상품순</a>
				<a href="${cplist}?category=${category}&order=3">낮은가격순</a>
				<a href="${cplist}?category=${category}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==2}">
				<a href="${cplist}?category=${category}&order=1">판매순</a>
				<a href="${cplist}?category=${category}&order=2" style="text-decoration:underline;">신상품순</a>
				<a href="${cplist}?category=${category}&order=3">낮은가격순</a>
				<a href="${cplist}?category=${category}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==3}">
				<a href="${cplist}?category=${category}&order=1">판매순</a>
				<a href="${cplist}?category=${category}&order=2">신상품순</a>
				<a href="${cplist}?category=${category}&order=3" style="text-decoration:underline;">낮은가격순</a>
				<a href="${cplist}?category=${category}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==4}">
				<a href="${cplist}?category=${category}&order=1">판매순</a>
				<a href="${cplist}?category=${category}&order=2">신상품순</a>
				<a href="${cplist}?category=${category}&order=3">낮은가격순</a>
				<a href="${cplist}?category=${category}&order=4" style="text-decoration:underline;">높은가격순</a>
			</c:when>
		</c:choose>
		</div>
		
		
		
		
		<input type="button" id="search" value="검색조건" onclick="displaySearchWindow()">	
		<form method="post" action="">
		<div id="searchWindow" >
			<label for="color">색상</label>
			<span><input type="checkbox" name="color" value="ALL" onclick="selectAll()"> ALL</span>
			<span><input type="checkbox" name="color" value="BLACK" class="black"> BLACK</span>
			<span><input type="checkbox" name="color" value="WHITE" class="white"> WHITE</span>
			<span><input type="checkbox" name="color" value="RED" class="red"> RED</span>
			<span><input type="checkbox" name="color" value="GREEN" class="green"> GREEN</span>
			<hr>
			<label for="psize">사이즈</label>
			<span><input type="checkbox" name="psize" value="ALL" onclick="selectAll()"> ALL</span>
			<span><input type="checkbox" name="psize" value="90"> 90</span>
			<span><input type="checkbox" name="psize" value="95"> 95</span>
			<span><input type="checkbox" name="psize" value="100"> 100</span>
			<span><input type="checkbox" name="psize" value="105"> 105</span>
			<span><input type="checkbox" name="psize" value="110"> 110</span>
			<hr>
			<label for="price range">가격범위</label>
			<input type="text" name="price">
			 ~ 
			<input type="text" name="price">
		</div>
		</form>
		<br><br>
		
		
		
		
		<div id="list">
			<c:choose>
			<c:when test="${not empty list}">
				<c:forEach var="vo" items="${list}">
					<a href="${cp}/iteminfo?colnum=${vo.colnum}">
					<div name="item">
						<img src="${cp}/upload/${vo.savefilename}" width="220">
						<p>${vo.pname}</p>
						<p>${vo.price}</p>
						<p>${vo.color}</p>
					</div>
					</a>
				</c:forEach>
				<div style="clear: both;"></div>
			</c:when>
			<c:otherwise>
				<h3>등록된 제품이 없습니다.</h3>
			</c:otherwise>
			</c:choose>
		</div>
	
	
		<br><br>
		
		
		<c:set var="cpco" value="${cplist}?category=${category}&order=${order}"/>
		
		<div id="paging">
		<c:if test="${not empty pageCount}">
			<c:if test="${startPageNum!=1}">
				<a href="${cpco}&pageNum=${startPageNum-1}">이전</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
				<c:choose>
					<c:when test="${pageNum==i}">
						<a href="${cpco}&pageNum=${i}" style="color:red;">[${i}]</a>
					</c:when>
					<c:otherwise>
						<a href="${cpco}&pageNum=${i}">[${i}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		
			<c:if test="${endPageNum!=pageCount}">
				<a href="${cpco}&pageNum=${endPageNum+1}">다음</a>
			</c:if>
		
		</c:if>
		</div>
	
	</div>

</div>





<script type="text/javascript">
	
	function displaySearchWindow(){
		var sw=document.getElementById("searchWindow");
		if(sw.style.display=="inline"){
			sw.style.display="none";	
		}else{
			sw.style.display="inline";
		}
	}
	
</script>