var bounds;
var ps;
var map;
var circles = [];
function drawCircle(circleData, topData){
	console.log(circleData);
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(35.3025223, 127.9602451), //지도의 중심좌표.
		level : 13
	//지도의 레벨(확대, 축소 정도)
	};

	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	// 장소 검색 객체를 생성합니다
	ps = new kakao.maps.services.Places(); 
	
   	for(var i = 0;i<topData.length ; i++){
   		var tmpData = topData[i]+"청";
		////console.log(tmpData);
   		
   		 ps.keywordSearch( tmpData, function (data, status, pagination, dd) {
              if (status === kakao.maps.services.Status.OK) {
  				//console.log(data);

              	// 지도에 표시할 원을 생성합니다
             	var circle = new kakao.maps.Circle({
             	    center : new kakao.maps.LatLng(data[0].y, data[0].x),  // 원의 중심좌표 입니다 
             	    ////console.log(tmpdata, data[0].y, data[0].x);
             	    radius: 30000, // 미터 단위의 원의 반지름입니다 
             	    strokeWeight: 5, // 선의 두께입니다 
             	    strokeColor: '#75B8FA', // 선의 색깔입니다
             	    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
             	    strokeStyle: 'solid', // 선의 스타일 입니다
             	    fillColor: '#CFE7FF', // 채우기 색깔입니다
             	    fillOpacity: 0.7  // 채우기 불투명도 입니다   
             	}); 
         	    //console.log(data[0].y, data[0].x, data[0].address_name);
             	
             	// 지도에 원을 표시합니다 
             	circle.setMap(map);
				circles.push(circle);
             	var cityName = data[0].address_name.split(" ")[0];


				//도시의 원래 이름을 조회해서 그에따른 질병명을 표기해야 한다.
				var cityOrgName = "";
				topData.forEach(function(str){
					var cnt = 0;
					for(var i=0;i<cityName.length;i++){
							//console.log(str+"안에 "+cityName.charAt(i)+"비교함!")
						if(str.indexOf(cityName.charAt(i)) >= 0){
							//console.log("포함됨!")
							cnt++;
						} else {
							//console.log(str+"안에 "+cityName.charAt(i)+"포함안됨!")
						}
					}
					//console.log(str+"과"+cityName+"을 비교결과: "+cnt+"개 일치")
					if(cityName.length == cnt){cityOrgName = str;}
				});
				
				
               	//커스텀 오버레이
               	var contents = document.createElement('div');
				contents.className = 'overlay';
				var content = '<ul class="info overlay">';
       		    content += '    <li>';
       		    content += '        <span class="label">'+data[0].address_name.split(" ")[0]+'</span><span class="number">' + '</span>';
       		    content += '    </li>';
       		    content += '    <li>';
       		    content += '        <span class="label">1위</span>'+'<span class="number">'+circleData[cityOrgName][0].careName+'</span>' ;
       		    content += '    </li>';
       		    content += '    <li>';
       		    content += '        <span class="label">2위</span>'+'<span class="number">'+circleData[cityOrgName][1].careName+'</span>';
       		    content += '    </li>';
       		    content += '</ul>';
				contents.innerHTML = content;

               	// 커스텀 오버레이가 표시될 위치입니다 
               	var position = new kakao.maps.LatLng(Number(data[0].y)-0.5, data[0].x);  
				var customOverlay;
				
				// 다각형에 마우스오버 이벤트를 등록합니다
				kakao.maps.event.addListener(circle, 'mouseover', function() { 
				
				    // 다각형의 채우기 옵션을 변경합니다
				    circle.setOptions({fillColor: '#75B8FA'});
				
	               	// 커스텀 오버레이를 생성합니다
	               	customOverlay = new kakao.maps.CustomOverlay({
	               	    position: position,
	               	    content: content   ,
						map:map
	               	});
				}); 

				kakao.maps.event.addListener(circle, 'mouseout', function() { 
				
				    // 다각형의 채우기 옵션을 변경합니다
				    circle.setOptions({fillColor: '#CFE7FF'});
					customOverlay.setMap(null);
				}); 

             } 
   		}); 
   	}
};
function clearMap(){
	/*var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(35.3025223, 127.9602451), //지도의 중심좌표.
		level : 13
	//지도의 레벨(확대, 축소 정도)
	};
	
	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴*/
	for (var i = 0; i < circles.length; i++) {
        circles[i].setMap(null);
    }   
}

// 커스텀 오버레이를 드래그 하기 위해 필요한  
// 드래그 시작좌표, 커스텀 오버레이의 위치좌표를 넣을 변수를 선업합니다
var startX, startY, startOverlayPoint;

// 커스텀 오버레이에 mousedown 했을 때 호출되는 핸들러 입니다 
function onMouseDown(e) {
	//console.log(onMouseDown)
    // 커스텀 오버레이를 드래그 할 때, 내부 텍스트가 영역 선택되는 현상을 막아줍니다.
    if (e.preventDefault) {
        e.preventDefault();
    } else {
        e.returnValue = false;
    }

    var proj = map.getProjection(), // 지도 객체로 부터 화면픽셀좌표, 지도좌표간 변환을 위한 MapProjection 객체를 얻어옵니다 
        overlayPos = customoverlay.getPosition(); // 커스텀 오버레이의 현재 위치를 가져옵니다

    // 커스텀오버레이에서 마우스 관련 이벤트가 발생해도 지도가 움직이지 않도록 합니다
    kakao.maps.event.preventMap();

    // mousedown된 좌표를 설정합니다 
    startX = e.clientX; 
    startY = e.clientY;

    // mousedown됐을 때의 커스텀 오버레이의 좌표를
    // 지도 컨테이너내 픽셀 좌표로 변환합니다 
    startOverlayPoint = proj.containerPointFromCoords(overlayPos);

    // document에 mousemove 이벤트를 등록합니다 
    addEventHandle(document, 'mousemove', onMouseMove);       
}

// 커스텀 오버레이에 mousedown 한 상태에서 
// mousemove 하면 호출되는 핸들러 입니다 
function onMouseMove(e) {
	//console.log(onMouseDown)
    // 커스텀 오버레이를 드래그 할 때, 내부 텍스트가 영역 선택되는 현상을 막아줍니다.
    if (e.preventDefault) {
        e.preventDefault();
    } else {
        e.returnValue = false;
    }

    var proj = map.getProjection(),// 지도 객체로 부터 화면픽셀좌표, 지도좌표간 변환을 위한 MapProjection 객체를 얻어옵니다 
        deltaX = startX - e.clientX, // mousedown한 픽셀좌표에서 mousemove한 좌표를 빼서 실제로 마우스가 이동된 픽셀좌표를 구합니다 
        deltaY = startY - e.clientY,
        // mousedown됐을 때의 커스텀 오버레이의 좌표에 실제로 마우스가 이동된 픽셀좌표를 반영합니다 
        newPoint = new kakao.maps.Point(startOverlayPoint.x - deltaX, startOverlayPoint.y - deltaY), 
        // 계산된 픽셀 좌표를 지도 컨테이너에 해당하는 지도 좌표로 변경합니다 
        newPos = proj.coordsFromContainerPoint(newPoint);

    // 커스텀 오버레이의 좌표를 설정합니다 
    customoverlay.setPosition(newPos);
}

// mouseup 했을 때 호출되는 핸들러 입니다 
function onMouseUp(e) {
	//console.log(onMouseDown)
    // 등록된 mousemove 이벤트 핸들러를 제거합니다 
    removeEventHandle(document, 'mousemove', onMouseMove);
}

// target node에 이벤트 핸들러를 등록하는 함수힙니다  
function addEventHandle(target, type, callback) {
    if (target.addEventListener) {
        target.addEventListener(type, callback);
    } else {
        target.attachEvent('on' + type, callback);
    }
}

// target node에 등록된 이벤트 핸들러를 제거하는 함수힙니다 
function removeEventHandle(target, type, callback) {
    if (target.removeEventListener) {
        target.removeEventListener(type, callback);
    } else {
        target.detachEvent('on' + type, callback);
    }
}