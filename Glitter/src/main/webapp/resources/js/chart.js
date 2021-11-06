function drawChart(data){
	console.log("chart : "+data);
	var context = document.getElementById('chart1').getContext('2d');
	var myChart = new Chart(context, {
		type: 'bar', // 차트의 형태
		data: { // 차트에 들어갈 데이터
			labels: data[0][1],
			datasets: [
				{ //데이터
					label: '타지역 진료가 많은 지역', //차트 제목
					fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
					data: data[0][0],
					borderRadius: 10,
					backgroundColor: [
						//색상
						'rgba(207, 231, 255, 1)',
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
			scales: {
				yAxes: [
					{
						ticks: {
							beginAtZero: true
						}
					}
				]
			}
		}
		
	});
	
	var context = document.getElementById('chart2').getContext('2d');
	var myChart = new Chart(context, {
		type: 'bar', // 차트의 형태
		data: { // 차트에 들어갈 데이터
			labels: data[1][1],
			datasets: [
				{ //데이터
					label: '타지역 진료과목 top 5', //차트 제목
					fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
					data: data[1][0],
					borderRadius: 10,
					backgroundColor: [
						//색상
						'rgba(207, 231, 255, 1)',
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
			scales: {
				yAxes: [
					{
						ticks: {
							beginAtZero: true
						}
					}
				]
			}
		}
		
	});
	
	var context = document.getElementById('chart3').getContext('2d');
	var myChart = new Chart(context, {
		type: 'bar', // 차트의 형태
		data: { // 차트에 들어갈 데이터
			labels: [
				//x 축
				'경기도', '강원도', '경상도', '강원도', '경상도'
			],
			datasets: [
				{ //데이터
					label: '타지역 진료자가 많이 방문하는 지역 top 5', //차트 제목
					fill: false, // line 형태일 때, 선 안쪽을 채우는지 안채우는지
					data: [
						21, 19, 25, 20, 23, 26, 25 //x축 label에 대응되는 데이터 값
					],
					borderRadius: 25,
					backgroundColor: [
						//색상
						'rgba(207, 231, 255, 1)',
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
			scales: {
				yAxes: [
					{
						ticks: {
							beginAtZero: true
						}
					}
				]
			}
		}
		
	});
};
