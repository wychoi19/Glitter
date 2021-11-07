package kr.ac.yonsei.code.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.ac.yonsei.home.VO.CodeVO;
import kr.ac.yonsei.home.VO.ResultVO;

@Repository("codeDAO")
public class CodeDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<CodeVO> getCodeList(CodeVO codeVO) {
		System.out.println("SQL DAO");
		return sqlSession.selectList("code.getCodeList", codeVO);
	}


	public List<CodeVO> getResultCalList(ResultVO resultVO) {
		return sqlSession.selectList("code.getResultCalList", resultVO);
	}
}
