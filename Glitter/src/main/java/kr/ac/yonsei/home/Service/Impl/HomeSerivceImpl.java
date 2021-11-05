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

	public List<ResultVO> getCityTop3(ResultVO resultVO) {
		List<ResultVO> result = homeDAO.getCityTop3(resultVO);
		return result;
	}
}
