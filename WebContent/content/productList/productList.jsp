<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<style type="text/css">
	#content{
		width:100%;
		min-width:1220px;
		text-align:center;
		margin:0 auto;
		align-items:center;
	}
	
	#categoryList{
		float:left;
		width:130px;
		padding:10px 30px;
		line-height:30px;
		text-align:left;
	}
	#categoryList a{
		padding-left:10px;
		text-decoration:none;
		color:black;
	}
	#categoryList a:hover{
		color:red;
		text-decoration:underline;
		text-underline-position:under;
	}


	
	#productList{
		float:left;
		width:970px;
		text-align:left;
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
		margin-right:70px;
		background-color:white;
	}
	#searchWindow{
		width:90%;
		display:inline-block;
		float:left;
		line-height:50px;
		border:2px solid grey;
		margin:10px 25px;
	}
	#searchWindow label{
		display:inline-block;
		width:200px;
		text-align:center;
	}
	#searchWindow span{
		display:inline-block;
		width:100px;
	}
	#searchWindow input[type=checkbox] {
		display:inline-block;
		width:20px; height:20px;
	}
	#searchWindow input[type=text]{
		display:inline-block;
		width:100px; height:25px;
	}
	#searchWindow #button{
		text-align:center;
	}
	#searchWindow #button input{
		width:150px; height:40px;
		font-size:20px;
		margin:30px 20px 20px 20px;
		blackground-color:blue;
	}
	
	#searchWindow div[name=circle]{
		display:inline-block;
		width:30px; height:30px;
		-webkit-border-radius:15px;
		-moz-border-radius:15px;
	}
	.circle1{
		background-color:#FE9A2E;
	}
	.circle2{
		background-color:#01DFA5;
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
	


	<c:set var="cpsearch" value="${pageContext.request.contextPath}/product/list?category=${category}&colorVal=${colorVal}&sizeVal=${sizeVal}&priceVal=${priceVal}"/>

	<div id="productList">
		<h1>상품목록</h1>
		<br>
		<div id="order">
		<c:choose>
			<c:when test="${empty order || order==1}">
				<a href="${cpsearch}&order=1" style="text-decoration:underline;">판매순</a>
				<a href="${cpsearch}&order=2">신상품순</a>
				<a href="${cpsearch}&order=3">낮은가격순</a>
				<a href="${cpsearch}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==2}">
				<a href="${cpsearch}&order=1">판매순</a>
				<a href="${cpsearch}&order=2" style="text-decoration:underline;">신상품순</a>
				<a href="${cpsearch}&order=3">낮은가격순</a>
				<a href="${cpsearch}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==3}">
				<a href="${cpsearch}&order=1">판매순</a>
				<a href="${cpsearch}&order=2">신상품순</a>
				<a href="${cpsearch}&order=3" style="text-decoration:underline;">낮은가격순</a>
				<a href="${cpsearch}&order=4">높은가격순</a>
			</c:when>
			<c:when test="${order==4}">
				<a href="${cpsearch}&order=1">판매순</a>
				<a href="${cpsearch}&order=2">신상품순</a>
				<a href="${cpsearch}&order=3">낮은가격순</a>
				<a href="${cpsearch}&order=4" style="text-decoration:underline;">높은가격순</a>
			</c:when>
		</c:choose>
		</div>
		
		
		
		
		<%
			String colorVal=(String)request.getAttribute("colorVal");
			String[] cchk={"BLACK","WHITE","RED","GREEN"};	
			if(colorVal!=null){
				String[] colors=colorVal.split("/");
				for(int i=0; i<colors.length; i++){
					for(int j=0; j<cchk.length; j++){
						if(colors[i].equals(cchk[j])){
							cchk[j]="checked";
							break;
						}
					}
				}
			}
			for(int j=0; j<cchk.length; j++){
				if(!cchk[j].equals("checked")){
					cchk[j]="unchecked";
				}
			}
			
			String sizeVal=(String)request.getAttribute("sizeVal");
			String[] schk={"90","95","100","105","110"};	
			if(sizeVal!=null){
				String[] sizes=sizeVal.split("/");
				for(int i=0; i<sizes.length; i++){
					for(int j=0; j<schk.length; j++){
						if(sizes[i].equals(schk[j])){
							schk[j]="checked";
							break;
						}
					}
				}
			}
			for(int j=0; j<schk.length; j++){
				if(!schk[j].equals("checked")){
					schk[j]="unchecked";
				}
			}
			
			String priceVal=(String)request.getAttribute("priceVal");
			String[] pchk=priceVal.split("/");
		%>
		
		<input type="button" id="search" value="검색조건" onclick="displaySearchWindow()">	
			<form method="post" action="${cplist}">
				<div id="searchWindow" >
					<label for="color">색상</label>
					<span><input type="checkbox" id="all_color" onclick="selectAll('color')"> ALL</span>
					<span><input type="checkbox" <%=cchk[0]%> name="color" value="BLACK"> BLACK</span>
					<span><input type="checkbox" <%=cchk[1]%> name="color" value="WHITE"> WHITE</span>
					<span><input type="checkbox" <%=cchk[2]%> name="color" value="RED"> RED</span>
					<span><input type="checkbox" <%=cchk[3]%> name="color" value="GREEN"> GREEN</span>
					<hr>
					<label for="psize">사이즈</label>
					<span><input type="checkbox" id="all_psize" onclick="selectAll('psize')"> ALL</span>
					<span><input type="checkbox" <%=schk[0]%> name="psize" value="90"> 90</span>
					<span><input type="checkbox" <%=schk[1]%> name="psize" value="95"> 95</span>
					<span><input type="checkbox" <%=schk[2]%> name="psize" value="100"> 100</span>
					<span><input type="checkbox" <%=schk[3]%> name="psize" value="105"> 105</span>
					<span><input type="checkbox" <%=schk[4]%> name="psize" value="110"> 110</span>
					<hr>
					<label for="price range">가격범위</label>
					<input type="text" name="price1" value="<%=pchk[0]%>" onkeyup="checkRange()">
					 ~ 
					<input type="text" name="price2" value="<%=pchk[1]%>" onkeyup="checkRange()">
		
					<hr>
					<div name="circle" class="circle1"></div>
					<div name="circle" class="circle2"></div>
		
					<div id="button">
						<input type="submit" value="검색하기">
						<input type="button" value="닫기" onclick="displaySearchWindow()">
					</div>
					<input type="hidden" name="category" value="${category}">
					<input type="hidden" name="order" value="${order}">
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
		
		
		
		
		<c:set var="cpcso" value="${cpsearch}&order=${order}"/>
		
		<div id="paging">
		<c:if test="${not empty pageCount}">
			<c:if test="${startPageNum!=1}">
				<a href="${cpcso}&pageNum=${startPageNum-1}">이전</a>
			</c:if>
		
			<c:forEach var="i" begin="${startPageNum}" end="${endPageNum}">
				<c:choose>
					<c:when test="${pageNum==i}">
						<a href="${cpcso}&pageNum=${i}" style="color:red;">[${i}]</a>
					</c:when>
					<c:otherwise>
						<a href="${cpcso}&pageNum=${i}">[${i}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		
			<c:if test="${endPageNum!=pageCount}">
				<a href="${cpcso}&pageNum=${endPageNum+1}">다음</a>
			</c:if>
		
		</c:if>
		</div>
	
	</div>

</div>





<script type="text/javascript">
	
	function displaySearchWindow(){
		var sw=document.getElementById("searchWindow");
		if(sw.style.display!="none"){
			sw.style.display="none";	
		}else{
			sw.style.display="inline";
		}
	}
	
	function selectAll(sel){
		var chk=document.getElementById("all_"+sel);
		var all=document.getElementsByName(sel);
		if(chk.checked==true){
			for(var i=0; i<all.length; i++){
				all[i].checked=true;
			}
		}else{
			for(var i=0; i<all.length; i++){
				all[i].checked=false;
			}			
		}
	}
	
</script>