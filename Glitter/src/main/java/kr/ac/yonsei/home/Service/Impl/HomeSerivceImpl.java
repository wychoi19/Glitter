package kr.ac.yonsei.home.Service.Impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.HttpResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Util.ExcelCellRef;
import Util.ExcelFileType;
import Util.ExcelReadOption;
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

	public int getResultCount() {
		return homeDAO.getResultCount();
	}

	public List<Map<String, String>>  read(ExcelReadOption excelReadOption) {
		//���� ���� ��ü
        //���������� �о� ���δ�.
        //FileType.getWorkbook() <-- ������ Ȯ���ڿ� ���� �����ϰ� �����´�.
        Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());
        /**
         * ���� ���Ͽ��� ù��° ��Ʈ�� ������ �´�.
         */
        Sheet sheet = wb.getSheetAt(0);
        
        System.out.println("Sheet �̸�: "+ wb.getSheetName(0)); 
        System.out.println("�����Ͱ� �ִ� Sheet�� �� :" + wb.getNumberOfSheets());
        /**
         * sheet���� ��ȿ��(�����Ͱ� �ִ�) ���� ������ �����´�.
         */
        int numOfRows = sheet.getPhysicalNumberOfRows();
        int numOfCells = 0;
        
        Row row = null;
        Cell cell = null;
        
        String cellName = "";
        /**
         * �� row������ ���� ������ �� ��ü
         * ����Ǵ� ������ ������ ����.
         * put("A", "�̸�");
         * put("B", "���Ӹ�");
         */
        Map<String, String> map = null;
        /*
         * �� Row�� ����Ʈ�� ��´�.
         * �ϳ��� Row�� �ϳ��� Map���� ǥ���Ǹ�
         * List���� ��� Row�� ���Ե� ���̴�.
         */
        List<Map<String, String>> result = new ArrayList<Map<String, String>>(); 
        /**
         * �� Row��ŭ �ݺ��� �Ѵ�.
         */
        for(int rowIndex = excelReadOption.getStartRow() - 1; rowIndex < numOfRows; rowIndex++) {
            /*
             * ��ũ�Ͽ��� ������ ��Ʈ���� rowIndex�� �ش��ϴ� Row�� �����´�.
             * �ϳ��� Row�� �������� Cell�� ������.
             */
            row = sheet.getRow(rowIndex);
            
            if(row != null) {
                /*
                 * ������ Row�� Cell�� ������ ���Ѵ�.
                 */
                numOfCells = row.getPhysicalNumberOfCells();
                /*
                 * �����͸� ���� �� ��ü �ʱ�ȭ
                 */
                map = new HashMap<String, String>();
                /*
                 * cell�� �� ��ŭ �ݺ��Ѵ�.
                 */
                for(int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
                    /*
                     * Row���� CellIndex�� �ش��ϴ� Cell�� �����´�.
                     */
                    cell = row.getCell(cellIndex);
                    /*
                     * ���� Cell�� �̸��� �����´�
                     * �̸��� �� : A,B,C,D,......
                     */
                    cellName = ExcelCellRef.getName(cell, cellIndex);
                    /*
                     * ���� ��� �÷����� Ȯ���Ѵ�
                     * ���� ��� �÷��� �ƴ϶��, 
                     * for�� �ٽ� �ö󰣴�
                     */
                    if( !excelReadOption.getOutputColumns().contains(cellName) ) {
                        continue;
                    }
                    /*
                     * map��ü�� Cell�� �̸��� Ű(Key)�� �����͸� ��´�.
                     */
                    map.put(cellName, ExcelCellRef.getValue(cell));
                }
                /*
                 * ������� Map��ü�� List�� �ִ´�.
                 */
                result.add(map);
                
            }
            
        }
        
        return result;
	}
}
