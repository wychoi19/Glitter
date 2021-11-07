
window.onload = function () {
	selectInit();
	$.ajax({
        url:'/getCodeList.do',
        dataType:'json',
        data:{cdType:"2"},
        method: "POST", 
        success:function(data){ //자동 완성 데이터는 코드 타입'2'에서 조회한다 (시+구 데이터)
          	for(var i = 0;i<data.data.length ; i++){
          		var tmpData = data.data[i];
          		
				//autoCompleteData.push(tmpData.cdName);
				$("#autoCompleteText").append("<option value="+tmpData.cd+">"+tmpData.cdName+"</option>");
          	}
			$("#autoCompleteText").select2({
				width: 'resolve',
				height: 'resolve'
			});
        }
    });

	
	//지역을 검색했을 경우 뜨는 데이터
	$("#src").on("click", function () {
		// 키워드 검색을 요청하는 함수입니다
		clearMap();
	    var keyword = $("#autoCompleteText :selected").text();
		
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
		
		$.ajax({
	        url:'/getDetailCityTopN.do',
	        method: "POST", 
        	dataType:'json',
			data:{addrId:$("#autoCompleteText").val()},
	        success:function(data){ 
				var chartData = [];
				var moveDiseaseList = [];
				var datas = [];
				var label = [];
				
	        	for(index = 0; index< (data.moveDiseaseList.length > 5? 5 :data.moveDiseaseList.length) ; index++){
					var tmpData = data.moveDiseaseList[index];
					datas.push(tmpData.cnt);
					label.push(tmpData.careName);
				}
				moveDiseaseList.push(datas);
				moveDiseaseList.push(label);
				chartData.push(moveDiseaseList);
				
				
				
				var getMoveCareList = [];
				var datas = [];
				var label = [];
				
	        	for(index = 0; index< (data.getMoveCareList.length > 5? 5 :data.getMoveCareList.length); index++){
					var tmpData = data.getMoveCareList[index];
					datas.push(tmpData.cnt);
					label.push(tmpData.addrName);
				}
				getMoveCareList.push(datas);
				getMoveCareList.push(label);
				chartData.push(getMoveCareList);
				
				chartData.push(data.moveDistance.distance);
				
				drawDetailChart(chartData);
			}
		});
	    
	});
	
}

function selectInit(){
	//main에 뿌려질 데이터를 조회하는 부분
	$.ajax({
        url:'/getCityTopN.do',
        dataType:'json',
        method: "POST", 
        success:function(data){ //자동 완성 데이터는 코드 타입'2'에서 조회한다 (시+구 데이터)
			//차트 그리기 위한 변수
			console.log(data);
			var chartData = [];
			
			var html = "";
			var index = 1;
			
			var fewCareList = [];
			var datas = [];
			var label = [];
			
        	for(index = 0; index< 5; index++){
				var tmpData = data.fewCareList[index];
				if(index<3) //출력은 3개까지만
					html += "<li>"+(index+1)+". "+tmpData.addrName+"("+Math.ceil(tmpData.cnt/tmpData.total*100)+"%) </li>";
				datas.push(tmpData.cnt);
				label.push(tmpData.addrName);
			}
			fewCareList.push(datas);
			fewCareList.push(label);
			chartData.push(fewCareList);
			
			$("#fewCareList").html(html);
			
			
			var html = "";
			var index = 1;
			var manyCareList = [];
			var datas = [];
			var label = [];
			
        	for(index = 0; index< 5; index++){
				var tmpData = data.manyCareList[index];
				if(index<3) //출력은 3개까지만
					html += "<li>"+(index+1)+". "+tmpData.addrName+"("+Math.ceil(tmpData.cnt/tmpData.total*100)+"%) </li>";
				datas.push(tmpData.cnt);
				label.push(tmpData.addrName);
			}
			manyCareList.push(datas);
			manyCareList.push(label);
			chartData.push(manyCareList);
			
			$("#manyCareList").html(html);
			
			
			var html = "";
			var index = 1;
			
        	for(index = 0; index< 3; index++){
				var tmpData = data.moveDiseaseList[index];
				html += "<li>"+(index+1)+". "+tmpData.careName+"("+Math.ceil(tmpData.cnt/tmpData.total*100)+"%) </li>";
			}
			$("#moveDiseaseList").html(html);
			
			$("#moveDistance").html(Math.ceil(Number(data.moveDistance.distance)));
        	
			drawCircle(data.mapList, fewCareList[1]);
			drawChart(chartData);
        }
    });

}
