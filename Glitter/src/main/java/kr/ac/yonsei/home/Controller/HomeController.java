package kr.ac.yonsei.home.Controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.yonsei.home.Service.Impl.HomeSerivceImpl;
import kr.ac.yonsei.home.VO.CodeVO;
import kr.ac.yonsei.home.VO.ResultVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	HomeSerivceImpl homeServiceImpl;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("메인", locale);
		
		return "/home/main";
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.GET)
	public boolean save(String json) {
		logger.info("save.do");
		homeServiceImpl.save(null);
		return true; //성공인 경우 true;
	}
	

	/*
	 * 코드 조회용
	 */
	@RequestMapping(value = "/getCodeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCodeList(CodeVO codeVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		logger.info("getCodeList.do");
		List<CodeVO> list = homeServiceImpl.getCodeList(codeVO);
		result.put("data", list);
		result.put("result", true);
		
		return result; //성공인 경우 true;
	}
	
	/*
	 * 메인화면에서 통계자료 조회하기
	 */

	/*
	 * 검색에 따른 상세조회
	 */
	@RequestMapping(value = "/getDetailCityTopN.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailCityTopN(ResultVO resultVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		
		/* 개별 조회에 대해서 */
		/* (특정 지역에 대해서 ) 이동거리 평균 */
		ResultVO vo = homeServiceImpl.getMoveDistance(resultVO);
		result.put("moveDistance", vo);
		
		/* (특정 지역에 대해서 ) 이동하는 진료과목 top 5 */
		List<ResultVO> list = homeServiceImpl.getMoveDiseaseList(resultVO);
		result.put("moveDiseaseList", list);
		
		/* (특정 지역에 대해서 ) 원주에서 어디로 많이 가냐? */
		list = homeServiceImpl.getMoveCareList(resultVO);
		result.put("getMoveCareList", list);
		

		result.put("result", true);
		
		return result; //성공인 경우 true;
	}
	

	
	
	/*
	 * 지도가 초기상태일 경우 지역별로 top3개 질병 조회하기
	 */
	@RequestMapping(value = "/getCityTopN.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCityTopN(ResultVO resultVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		/* 병원이 적은 순 데이터*/
		List<ResultVO> list = homeServiceImpl.getFewCareList(resultVO);
		result.put("fewCareList", list);

		/* 지도에 표시할 데이터 */
		//-> 병원이 작은 순 데이터에서 상위5개는 어떤 질병으로 이동하는지에 대한 정보
		HashMap<String, Object> mapList = new HashMap<String, Object>();
		for(int i = 0 ; i < 5 ; i ++) {
			//list.get(i).get
			String tmpAddrName = list.get(i).getAddrName();
			list.get(i).setAddrName(null);
			List<ResultVO> tmpList = homeServiceImpl.getMoveDiseaseList(list.get(i));
			list.get(i).setAddrName(tmpAddrName);
			mapList.put(tmpAddrName, tmpList);
			
		}
		System.out.println(mapList.toString());
		result.put("mapList", mapList);
		
		/*병원이 많은 순 */
		list = homeServiceImpl.getManyCareList(resultVO);
		result.put("manyCareList", list);

		/* 이동하는 질병 순서*/
		list = homeServiceImpl.getMoveDiseaseList(resultVO);
		result.put("moveDiseaseList", list);

		/*  이동거리 평균 */
		ResultVO vo = homeServiceImpl.getMoveDistance(resultVO);
		result.put("moveDistance", vo);
		
		
		result.put("result", true);
		
		System.out.println("성공반환!!!");
		return result; //성공인 경우 true;
	}
	
	/*
	 * 지역코드에따라서 위경도 계산 후 거리계산
	 */
	
	/*
	 * @RequestMapping(value = "/calDistance.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> calDistance(ResultVO resultVO) {
	 * Map<String, Object> result = new HashMap<String, Object>();
	 * 
	 * 
	 * homeServiceImpl.getResultCalList(resultVO);
	 * 
	 * return result; //성공인 경우 true; }
	 */
}
