<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.*"%>
<%
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<title>Home</title>
</head>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=7801d10688d1111d9032248b5c5072c5&libraries=services,clusterer,drawing""></script>
<!-- 카카오맵 api -->
<link href="https://canvasjs.com/assets/css/jquery-ui.1.11.2.min.css"
	rel="stylesheet" />
<style>
.info {
	position: relative;
	top: 5px;
	left: 5px;
	border-radius: 6px;
	border: 1px solid #ccc;
	border-bottom: 2px solid #ddd;
	font-size: 12px;
	padding: 5px;
	background: #fff;
	list-style: none;
	margin: 0;
}

.info:nth-of-type(n) {
	border: 0;
	box-shadow: 0px 1px 2px #888;
}

.info .label {
	display: inline-block;
	width: 50px;
}

.number {
	font-weight: bold;
	color: #00a0e9;
}
</style>
<script>
var bounds;
window.onload = function () {

	// Construct options first and then pass it as a parameter
	var options1 = {
		animationEnabled: true,
		title: {
			text: "Chart inside a jQuery Resizable Element"
		},
		data: [{
			type: "column", //change it to line, area, bar, pie, etc
			legendText: "Try Resizing with the handle to the bottom right",
			showInLegend: true,
			dataPoints: [
				{ y: 10 },
				{ y: 6 },
				{ y: 14 },
				{ y: 12 },
				{ y: 19 },
				{ y: 14 },
				{ y: 26 },
				{ y: 10 },
				{ y: 22 }
				]
			}]
	};

	$("#resizable").resizable({
		create: function (event, ui) {
			//Create chart.
			$("#chartContainer1").CanvasJSChart(options1);
		},
		resize: function (event, ui) {
			//Update chart size according to its container size.
			$("#chartContainer1").CanvasJSChart().render();
		}
	});
	
	$("#src").on("click", function () {
		// 키워드 검색을 요청하는 함수입니다

	    var keyword = document.getElementById('autoCompleteText').value;
		
	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('장소를 입력해주세요!');
	        return false;
	    }

	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	    ps.keywordSearch( keyword, function (data, status, pagination) {
	        if (status === kakao.maps.services.Status.OK) {

	            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	            // LatLngBounds 객체에 좌표를 추가합니다
	            bounds = new kakao.maps.LatLngBounds();

	            for (var i=0; i<data.length; i++) {
	                bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
	            }       

	            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    	    map.setBounds(bounds);
	        } 
	    }); 
	    
	});
	
}
	
$(function() {
    var autoCompleteData = []; //자동 완성을 위한 배열
    
	$.ajax({
        url:'/getCodeList.do',
        dataType:'json',
        data:{cdType:"2"},
        method: "POST", 
        success:function(data){ //자동 완성 데이터는 코드 타입'2'에서 조회한다 (시+구 데이터)
          	for(var i = 0;i<data.data.length ; i++){
          		var tmpData = data.data[i];
          		autoCompleteData.push(tmpData.cdName);
          	}
        }
    });
    
	var data=['강원도', '충청도', '전라도', '경상도', '경상도', '제주도'];
   	for(var i = 0;i<data.length ; i++){
   		var tmpData = data[i];
   		
   		/* ps.keywordSearch( tmpData.cdName, function (data, status, pagination) {
              if (status === kakao.maps.services.Status.OK) {

              	// 지도에 표시할 원을 생성합니다
             	var circle = new kakao.maps.Circle({
             	    center : new kakao.maps.LatLng(data[0].y, data[0].x),  // 원의 중심좌표 입니다 
             	    radius: 30000, // 미터 단위의 원의 반지름입니다 
             	    strokeWeight: 5, // 선의 두께입니다 
             	    strokeColor: '#75B8FA', // 선의 색깔입니다
             	    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
             	    strokeStyle: 'solid', // 선의 스타일 입니다
             	    fillColor: '#CFE7FF', // 채우기 색깔입니다
             	    fillOpacity: 0.7  // 채우기 불투명도 입니다   
             	}); 
             	
             	// 지도에 원을 표시합니다 
             	circle.setMap(map);
             } 
   		}); */
   	}
    
	//검색 텍스트 자동완성
    $("#autoCompleteText").autocomplete({
        source: autoCompleteData,
        select: function(event, ui) {
        },
        focus: function(event, ui) {
            return false;
        }
    });

	
	//지도 api
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
		var options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(36.0025223, 127.9602451), //지도의 중심좌표.
			level : 13
		//지도의 레벨(확대, 축소 정도)
		};

	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	// 장소 검색 객체를 생성합니다
	ps = new kakao.maps.services.Places(); 
	
	
	//초기 지도화면에서 top3개 질병 조회하는 화면 -> 여기서는 오버레이만 그리자
	$.ajax({
        url:'/getCityTop3.do',
        dataType:'json',
        data:{year:"2021"},
        method: "POST", 
        success:function(data){ //자동 완성 데이터는 코드 타입'2'에서 조회한다 (시+구 데이터)
          	
           	//커스텀 오버레이
           	/* var content = '<ul class="info">';
   		    content += '    <li>';
   		    content += '        <span class="label">제주도</span><span class="number">' + '</span>';
   		    content += '    </li>';
   		    content += '    <li>';
   		    content += '        <span class="label">1위</span>'+'<span class="number">감기</span>' ;
   		    content += '    </li>';
   		    content += '    <li>';
   		    content += '        <span class="label">2위</span>'+'<span class="number">치과치료</span>';
   		    content += '    </li>';
   		    content += '</ul>'

           	// 커스텀 오버레이가 표시될 위치입니다 
           	var position = new kakao.maps.LatLng(33.450701, 127.570667);  

           	// 커스텀 오버레이를 생성합니다
           	var customOverlay = new kakao.maps.CustomOverlay({
           	    position: position,
           	    content: content   
           	});
           	customOverlay.setMap(map); */
                	
        	
        }
    }); //ajax
});
	
	
</script>
<body>
	<input type="text" id="autoCompleteText">
	<button id="src">검색</button>
	<P></P>

	<div>
		<div style="border: 1px solid black; width: 500px; height: 90%;"
			id="map"></div>

		<span id="resizable"
			style="height: 100%; width: 45%;; border: 1px solid gray; float: right">
			<span id="chartContainer1" style="height: 100%; width: 100%;"></span>
		</span>
	</div>
	<script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
</body>
</html>
