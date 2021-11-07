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
		
		<script src="/resources/js/chart.js"></script>
		<script src="/resources/js/index.js"></script>
		<script src="/resources/js/map.js"></script>
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
                <span class="logo">Glitter</span>
                <span class="category">Yonsei Mirae Campus SW Hackathon</span>
            </div>
        </header>
        <!-- header end -->
        <!-- main start -->
        <main>
            <div class="intro">
                <h1 class="intro_text">Analysis of the shortage<br>of local hospitals</h1>
            </div>
            <ul class="statistics">
                <li>
                    <div>
                        <div class="contents00">진료를 위한 이동거리</div>
                        <div class="ratio00"><span  id="moveDistance"></span>KM</div>
                    </div>
                </li>
                <li>
                    <div>
                        <div class="contents00">타지역 진료가 많은 지역 TOP3</div>
                        <ul class="TOP3" id="fewCareList">
                            <!-- <li>1. ㅇㅇㅇ도</li>
                            <li>2. ㅁㅁㅁ도</li>
                            <li>3. ㅎㅎㅎ도</li> -->
                        </ul>
                    </div>
                </li>
                <li>
                    <div>
                        <div class="contents00">타지역 진료자가 많이 방문하는 지역 TOP3</div>
                        <ul class="TOP3" id="manyCareList">
                            <!-- <li>1. ㅇㅇㅇ도</li>
                            <li>2. ㅁㅁㅁ도</li>
                            <li>3. ㅎㅎㅎ도</li> -->
                        </ul>
                    </div>
                </li>
                <li>
                    <div>
                        <div class="contents00">타지역 진료 과목 TOP3</div>
                        <ul class="TOP3" id="moveDiseaseList">
                            <!-- <li>1. ㅇㅇ과</li>
                            <li>2. ㅁㅁ과</li>
                            <li>3. ㅎㅎ과</li> -->
                        </ul>
                    </div>
                </li>
            </ul>
            <div class="analysis">
                <div class="map">
            		<div class="map_search">
                         <select id="autoCompleteText" style="height:50px;"></select>  
                         <button id="src">Search</button>
                         <button id="rst" onclick="selectInit();">Refresh</button>
                    </div>
                    <div class="map_detail" id="map">

                    </div>
                </div>
                <div class="graph">
                    <canvas class="patients" id="chart1">

                    </canvas>
                    <canvas class="patients" id="chart2">

                    </canvas>
                    <div class="patients_text" id="chart3" style="display:none;">
                    </div>
                    
                    
                </div>
            </div>
        </main>
        <!-- main end -->
        <!-- footer start -->
        <footer>

        </footer>
        <!-- footer end -->
    </body>
</html>
