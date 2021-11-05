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
		logger.info("����", locale);
		
		return "/home/main";
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.GET)
	public boolean save(String json) {
		logger.info("save.do");
		homeServiceImpl.save(null);
		return true; //������ ��� true;
	}
	

	/*
	 * �ڵ� ��ȸ��
	 */
	@RequestMapping(value = "/getCodeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCodeList(CodeVO codeVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		logger.info("getCodeList.do");
		List<CodeVO> list = homeServiceImpl.getCodeList(codeVO);
		result.put("data", list);
		result.put("result", true);
		
		return result; //������ ��� true;
	}
	
	/*
	 * ����ȭ�鿡�� ����ڷ� ��ȸ�ϱ�
	 */
	
	
	/*
	 * ������ �ʱ������ ��� �������� top3�� ���� ��ȸ�ϱ�
	 */
	@RequestMapping(value = "/getCityTop3.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCityTop3(ResultVO resultVO) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		//DB���� �뵵�ú��� �������� ���� �̵��� Ƚ���� �� ȸ���� ��ȸ�Ѵ�.
		
		List<ResultVO> list = homeServiceImpl.getCityTop3(resultVO);
		
		
		result.put("data", list);
		
		return result; //������ ��� true;
	}
	
	/*
	 * ������ Ȯ���� ��� ������ ���� ����ȸ
	 * Ÿ���� ���� ����/����/����̵��Ÿ�
	 */
	/*
	 * @RequestMapping(value = "/getCityDetail.do", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Map<String, Object> getCityDetail(ResultVO resultVO) {
	 * Map<String, Object> result = new HashMap<String, Object>();
	 * 
	 * logger.info("getCodeList.do"+codeVO.getCdType());
	 * 
	 * List<CodeVO> list = homeServiceImpl.getCodeList(codeVO);
	 * logger.info("list : "+list); result.put("data", list); result.put("result",
	 * true);
	 * 
	 * return result; //������ ��� true; }
	 */
	
}
