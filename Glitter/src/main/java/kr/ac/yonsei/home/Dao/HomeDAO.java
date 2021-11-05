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

	public List<ResultVO> getCityTop3(ResultVO resultVO) {
		return sqlSession.selectList("result.getCityTop3", resultVO);
	}

}
