package kr.ac.yonsei.home.Service.Impl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kr.ac.yonsei.code.DAO.CodeDAO;
import kr.ac.yonsei.home.Dao.HomeDAO;
import kr.ac.yonsei.home.Service.HomeService;
import kr.ac.yonsei.home.VO.CodeVO;
import kr.ac.yonsei.home.VO.ResultVO;

@Service("homeService")
public class HomeSerivceImpl implements HomeService {
	
	@Resource(name="homeDAO")
	HomeDAO homeDAO;

	@Resource(name="codeDAO")
	CodeDAO codeDAO;
	
	public int save(List<ResultVO> resultVO) {
		return homeDAO.save(resultVO);
	}
	
	public List<CodeVO> getCodeList(CodeVO codeVO) {
		return codeDAO.getCodeList(codeVO);
	}

	public List<ResultVO> getCityTopN(ResultVO resultVO) {
		List<ResultVO> result = homeDAO.getCityTopN(resultVO);
		return result;
	}

	public List<ResultVO> getFewCareList(ResultVO resultVO) {
		return homeDAO.getFewCareList(resultVO);
	}

	public List<ResultVO> getManyCareList(ResultVO resultVO) {
		return homeDAO.getManyCareList(resultVO);
	}

	public List<ResultVO> getMoveDiseaseList(ResultVO resultVO) {
		return homeDAO.getMoveDiseaseList(resultVO);
	}

	public ResultVO getMoveDistance(ResultVO resultVO) {
		return homeDAO.getMoveDistance(resultVO);
	}

	public List<ResultVO> getMoveCareList(ResultVO resultVO) {
		return homeDAO.getMoveCareList(resultVO);
	}

	/*
	 * public void getResultCalList(ResultVO resultVO) { List<CodeVO> resVO =
	 * codeDAO.getResultCalList(resultVO); for(CodeVO tmpVO : resVO) {
	 * 
	 * String APIKey = "ba0f8db0452ad4bf3ccde980430acfb1"; String address =
	 * tmpVO.getCdName();
	 * 
	 * HashMap<String, Object> map = new HashMap<>(); //결과를 담을 map
	 * 
	 * try { String apiURL =
	 * "https://dapi.kakao.com/v2/local/search/address.json?query=" +
	 * URLEncoder.encode(address, "UTF-8");
	 * 
	 * kong.unirest.HttpResponse<JsonNode> response =
	 * Unirest.get(apiURL).header("Authorization", APIKey).asJson();
	 * 
	 * //받아온 데이터를 json으로 파싱 ObjectMapper objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
	 * true);
	 * 
	 * //vo에 세팅 KakaoGeoRes bodyJson =
	 * objectMapper.readValue(response.getBody().toString(), KakaoGeoRes.class);
	 * 
	 * bodyJson.getDocuments().get(0).getX() bodyJson.getDocuments().get(0).getY()
	 * 
	 * } }
	 */
	 
}
