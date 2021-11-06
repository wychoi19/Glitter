var bounds;
var ps;
var map;
	
$(function() {

	//지도 api
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(35.3025223, 127.9602451), //지도의 중심좌표.
		level : 13
	//지도의 레벨(확대, 축소 정도)
	};

	map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	// 장소 검색 객체를 생성합니다
	ps = new kakao.maps.services.Places(); 
	
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
    
	//검색 텍스트 자동완성
    $("#autoCompleteText").autocomplete({
        source: autoCompleteData,
        select: function(event, ui) {
        },
        focus: function(event, ui) {
            return false;
        }
    });
	
	
	//초기 지도화면에서 top3개 질병 조회하는 화면 -> 여기서는 오버레이만 그리자
	/*$.ajax({
        url:'/getCityTop3.do',
        dataType:'json',
        data:{year:"2021"},
        method: "POST", 
        success:function(data){ //자동 완성 데이터는 코드 타입'2'에서 조회한다 (시+구 데이터)
          	
                	
        	
        }
    }); *///ajax
    

    
	var data=['강원도 오대산', '청양군', '전라도', '창녕', '제주도'];
   	for(var i = 0;i<data.length ; i++){
   		var tmpData = data[i];
		console.log(tmpData);
   		
   		 ps.keywordSearch( tmpData, function (data, status, pagination) {
              if (status === kakao.maps.services.Status.OK) {
  				console.log(tmpData+i);

              	// 지도에 표시할 원을 생성합니다
             	var circle = new kakao.maps.Circle({
             	    center : new kakao.maps.LatLng(data[0].y, data[0].x),  // 원의 중심좌표 입니다 
             	    //console.log(tmpdata, data[0].y, data[0].x);
             	    radius: 30000, // 미터 단위의 원의 반지름입니다 
             	    strokeWeight: 5, // 선의 두께입니다 
             	    strokeColor: '#75B8FA', // 선의 색깔입니다
             	    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
             	    strokeStyle: 'solid', // 선의 스타일 입니다
             	    fillColor: '#CFE7FF', // 채우기 색깔입니다
             	    fillOpacity: 0.7  // 채우기 불투명도 입니다   
             	}); 
         	    console.log(data[0].y, data[0].x);
             	
             	// 지도에 원을 표시합니다 
             	circle.setMap(map);
             	
               	//커스텀 오버레이
               	var content = '<ul class="info">';
       		    content += '    <li>';
       		    content += '        <span class="label">'+tmpData+'</span><span class="number">' + '</span>';
       		    content += '    </li>';
       		    content += '    <li>';
       		    content += '        <span class="label">1위</span>'+'<span class="number">감기</span>' ;
       		    content += '    </li>';
       		    content += '    <li>';
       		    content += '        <span class="label">2위</span>'+'<span class="number">치과치료</span>';
       		    content += '    </li>';
       		    content += '</ul>'

               	// 커스텀 오버레이가 표시될 위치입니다 
               	var position = new kakao.maps.LatLng(Number(data[0].y)-0.5, data[0].x);  

               	// 커스텀 오버레이를 생성합니다
               	var customOverlay = new kakao.maps.CustomOverlay({
               	    position: position,
               	    content: content   
               	});
               	customOverlay.setMap(map);
             } 
   		}); 
   	}
   	

});