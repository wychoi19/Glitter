var myChart1, myChart2, myChart3;

function drawChart(data){
	var context = document.getElementById('chart1').getContext('2d');
	$("#chart3").css("display", "none");
	
	if(myChart1==undefined){
		myChart1 = new Chart(context, {
			type: 'bar', // 차트의 형태
			data: { // 차트에 들어갈 데이터
				labels: data[0][1],
				datasets: [
					{ //데이터
						label: '타지역 진료자 수(단위 : 명)', //차트 제목
						fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
						data: data[0][0],
						borderRadius: 10,
						backgroundColor: [
							//색상
							'rgba(117, 184, 250, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)'
						],
						borderColor: [
							//경계선 색상
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)'
						]
					}
				]
			},
			options: {
				    legend: {
				      display: true,
				      position: 'top',
				      fontColor: 'white',
				      fontSize: 20,
				      labels: {
				        fontColor: 'white',
				        fontSize: 20
				      }
				    },
    			responsive: true,
				scales: {
					yAxes: [{
						stacked: false,
						scaleLabel: {
							display: true,
							fontColor: 'white',
							fontSize: 25,
							labelString: 'Faction Points'
						},
						ticks: {
							fontColor: 'white',
							fontSize: 20,
							min: 0
						},
						gridLines: {
							color: 'white'
						}
					}],
					xAxes: [{
						stacked: false,
						scaleLabel: {
							display: true,
							fontColor: 'white',
							fontSize: 25,
							labelString: 'Day'
						},
						ticks: {
							fontColor: 'white',
							fontSize: 20,
							min: 0
						}
					}]
				},
		        plugins: {
		            title: {
						display: true,
						text: '타지역 진료가 많은 지역 TOP5',	
						font : {size:20, weight:'bold', family:'Noto Sans KR'},
		                padding: {
		                    top: 10,
		                    bottom: 30
		                }
		            }
				}
			}
		});
		
		var context = document.getElementById('chart2').getContext('2d');
		myChart2 = new Chart(context, {
			type: 'bar', // 차트의 형태
			data: { // 차트에 들어갈 데이터
				labels: data[1][1],
				datasets: [
					{ //데이터
						label: '타지역 진료자 수(단위 : 명)', //차트 제목
						fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
						data: data[1][0],
						borderRadius: 10,
						backgroundColor: [
							//색상
							'rgba(117, 184, 250, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)',
							'rgba(207, 231, 255, 1)'
						],
						borderColor: [
							//경계선 색상
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)',
							'rgba(117, 184, 250, 1)'
						]
					}
				]
			},
			options: {
		        plugins: {
		            title: {
		                display: true,
		                text: '타지역 진료자가 많이 방문하는 지역 TOP5',	
						font : {size:20, weight:'bold', family:'Noto Sans KR'},
		                padding: {
		                    top: 10,
		                    bottom: 30
		                }
		            }
				}
			}
			
		});
	} else {
		myChart1.data.labels = data[0][1];
		myChart1.data.datasets[0].data = data[0][0];
		//myChart1.data.datasets[0].label = "타지역 진료 과목";
	    myChart1.options.plugins.title.text = '타지역 진료가 많은 지역 TOP5';
		myChart1.update();
		
		myChart2.data.labels = data[1][1];
		myChart2.data.datasets[0].data = data[1][0];
		//myChart2.data.datasets[0].label = "진료를 위해 방문한 지역";
	    myChart2.options.plugins.title.text = '타지역 진료자가 많이 방문하는 지역 TOP5';
		myChart2.update();
	
	}
};

function drawDetailChart(data){
	console.log(data);
	
	/*$("#chart1").remove();
	$("#chart2").remove();
	$("#chart3").remove();*/
	
	var context = document.getElementById('chart1').getContext('2d');
	$("#chart3").css("display", "block");
	
	myChart1.data.labels = data[0][1];
	myChart1.data.datasets[0].data = data[0][0];
	//myChart1.data.datasets[0].label = "타지역 진료 과목";
    myChart1.options.plugins.title.text = '타지역 진료 과목 TOP 5';
	myChart1.update();
	
	myChart2.data.labels = data[1][1];
	myChart2.data.datasets[0].data = data[1][0];
	//myChart2.data.datasets[0].label = "진료를 위해 방문한 지역";
    myChart2.options.plugins.title.text = '타지역 방문 병원 TOP 5';
	myChart2.update();
	
	
	
	$("#chart3").html("<div>평균이동거리</div> <div class='big'>"+Math.ceil(Number(data[2]))+"KM</div>");
}
