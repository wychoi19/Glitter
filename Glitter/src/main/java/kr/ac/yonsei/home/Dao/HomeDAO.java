package kr.ac.yonsei.home.Dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.yonsei.home.VO.ResultVO;

@Repository("homeDAO")
public class HomeDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int save(List<ResultVO> resultVO) {
		return sqlSession.insert("result.save", resultVO);
	}

	public List<ResultVO> getCityTopN(ResultVO resultVO) {
		return sqlSession.selectList("result.getCityTopN", resultVO);
	}

	public List<ResultVO> getFewCareList(ResultVO resultVO) {
		return sqlSession.selectList("result.getFewCareList", resultVO);
	}

	public List<ResultVO> getManyCareList(ResultVO resultVO) {
		return sqlSession.selectList("result.getManyCareList", resultVO);
	}

	public List<ResultVO> getMoveDiseaseList(ResultVO resultVO) {
		return sqlSession.selectList("result.getMoveDiseaseList", resultVO);
	}
	public ResultVO getMoveDistance(ResultVO resultVO) {
		String str = sqlSession.selectOne("result.getMoveDistance", resultVO);
		ResultVO resVO = new ResultVO();
		resVO.setDistance(str);
		return resVO;
	}

	/* 어디로 많이 이동했는가? */
	public List<ResultVO> getMoveCareList(ResultVO resultVO) {
		return sqlSession.selectList("result.getMoveCareList", resultVO);
	}

	public int getResultCount() {
		return sqlSession.selectOne("result.getResultCount");
	}
	
}
