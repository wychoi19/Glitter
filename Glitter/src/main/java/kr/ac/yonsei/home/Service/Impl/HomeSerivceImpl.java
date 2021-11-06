package kr.ac.yonsei.home.Service.Impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

	public ResultVO getMoveCareList(ResultVO resultVO) {
		return homeDAO.getMoveCareList(resultVO);
	}
}
