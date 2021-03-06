<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<html lang="ko">
    <head>
        <title>의사 선생님 여기에요!</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index.css'/>">
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7801d10688d1111d9032248b5c5072c5&libraries=services,clusterer,drawing"></script>
		<!-- 카카오맵 api -->
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.9/js/select2.min.js"></script>
		
    	<script src="https://malsup.github.io/jquery.form.js"></script> 
		<script src="/resources/js/file.js"></script>
		<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
    </head>

<style>

</style>
<script>

   	
	
</script>
<body>
	<!-- header start -->
	<header>
		<div class="nav">
			<span class="logo">Glitter</span> <span class="category">Yonsei
				Mirae Campus SW Hackathon</span>
		</div>
	</header>
	<!-- header end -->
	<!-- main start -->
	<main>
		<div class="intro">
			<h1 class="intro_text">
				Analysis of the shortage<br>of local hospitals
			</h1>
		</div>
	</main>

	<div class="upload-btn-wrapper" style="text-align:center">
		<form id="uploadForm" method="post" enctype="multipart/form-data" style="text-align:center">  
		
		<input type="file" name="upload" class="file" />
		</form> 
		<button id="btnupload">업로드</button>
	</div>

	<br />
</body>
</html>
