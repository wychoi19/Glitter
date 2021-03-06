package kr.ac.yonsei.home.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.google.gson.Gson;

import Util.CellRef;
import Util.ExcelFileType;
import Util.FileType;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Locale locale, Model model) {
		// ???????????? ????????? main.do
		if (homeServiceImpl.getResultCount() > 0) {
			return "redirect:/main.do";
		} else {
			return "/home/fileMain";
		}
	}

	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("??????", locale);

		if (homeServiceImpl.getResultCount() <= 0) {
			return "redirect:/";
		}

		return "/home/main";
	}

	public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		return new JSONObject(content);
	}

	// ?????? ???????????? ?????? ?????? ??????
	@RequestMapping(value = "/fileUpload.do", method = RequestMethod.POST)
	@ResponseBody
	public boolean fileUpload(MultipartRequest multipartRequest) throws IllegalStateException, IOException {

		MultipartFile file = multipartRequest.getFile("upload");
		String path = "D:/upload/"; // ??? ??????????????? upload ???????????? ???????????????. ????????? ????????? ?????????.???

		String replaceName = "upload.xlsx";

		Util.FileUpload.fileUpload(file, path, replaceName);

		try {
			FileInputStream file1 = new FileInputStream(path + replaceName);
			XSSFWorkbook workbook = new XSSFWorkbook(file1);

			int rowindex = 0;
			int columnindex = 0;
			// ?????? ??? (??????????????? ??????????????? 0??? ??????)
			// ?????? ??? ????????? ?????????????????? FOR?????? ????????? ????????????
			XSSFSheet sheet = workbook.getSheetAt(0);
			// ?????? ???
			List<ResultVO> list = new ArrayList<ResultVO>();
			int rows = sheet.getPhysicalNumberOfRows();
			for (rowindex =1; rowindex < rows; rowindex++) {
				// ???????????????
				XSSFRow row = sheet.getRow(rowindex);
				if (row != null) {
					// ?????? ???
					int cells = row.getPhysicalNumberOfCells();

					//DB????????? ?????? ??????
					ResultVO vo = new ResultVO();
					
					XSSFCell cell = row.getCell(1);
					String value= "";
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setYear(value);

					cell = row.getCell(2);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setPersonId(value);
					
					cell = row.getCell(3);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setInstId(value);

					cell = row.getCell(4);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setCareId(value);

					cell = row.getCell(5);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setAddrId(value);

					cell = row.getCell(6);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setCareAddrId(value);

					cell = row.getCell(7);
					if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
						value=(int) cell.getNumericCellValue()+"";
					else
						value = cell.getStringCellValue() + "";
					vo.setDistance(value);
					list.add(vo);
				}
			} //end for
			homeServiceImpl.save(list);

		} catch (Exception e) {
			e.printStackTrace();
		} //end try
		return false;

	}

	public File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	/*
	 * ?????? ?????????
	 */
	@RequestMapping(value = "/getCodeList.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCodeList(CodeVO codeVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		logger.info("getCodeList.do");
		List<CodeVO> list = homeServiceImpl.getCodeList(codeVO);
		result.put("data", list);
		result.put("result", true);

		return result; // ????????? ?????? true;
	}

	/*
	 * ?????????????????? ???????????? ????????????
	 */

	/*
	 * ????????? ?????? ????????????
	 */
	@RequestMapping(value = "/getDetailCityTopN.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getDetailCityTopN(ResultVO resultVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		/* ?????? ????????? ????????? */
		/* (?????? ????????? ????????? ) ???????????? ?????? */
		ResultVO vo = homeServiceImpl.getMoveDistance(resultVO);
		result.put("moveDistance", vo);

		/* (?????? ????????? ????????? ) ???????????? ???????????? top 5 */
		List<ResultVO> list = homeServiceImpl.getMoveDiseaseList(resultVO);
		result.put("moveDiseaseList", list);

		/* (?????? ????????? ????????? ) ???????????? ????????? ?????? ??????? */
		list = homeServiceImpl.getMoveCareList(resultVO);
		result.put("getMoveCareList", list);

		result.put("result", true);

		return result; // ????????? ?????? true;
	}

	/*
	 * ????????? ??????????????? ?????? ???????????? top3??? ?????? ????????????
	 */
	@RequestMapping(value = "/getCityTopN.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getCityTopN(ResultVO resultVO) {
		Map<String, Object> result = new HashMap<String, Object>();

		/* ????????? ?????? ??? ????????? */
		List<ResultVO> list = homeServiceImpl.getFewCareList(resultVO);
		result.put("fewCareList", list);

		/* ????????? ????????? ????????? */
		// -> ????????? ?????? ??? ??????????????? ??????5?????? ?????? ???????????? ?????????????????? ?????? ??????
		HashMap<String, Object> mapList = new HashMap<String, Object>();
		for (int i = 0; i < 5; i++) {
			// list.get(i).get
			String tmpAddrName = list.get(i).getAddrName();
			list.get(i).setAddrName(null);
			List<ResultVO> tmpList = homeServiceImpl.getMoveDiseaseList(list.get(i));
			list.get(i).setAddrName(tmpAddrName);
			mapList.put(tmpAddrName, tmpList);

		}
		System.out.println(mapList.toString());
		result.put("mapList", mapList);

		/* ????????? ?????? ??? */
		list = homeServiceImpl.getManyCareList(resultVO);
		result.put("manyCareList", list);

		/* ???????????? ?????? ?????? */
		list = homeServiceImpl.getMoveDiseaseList(resultVO);
		result.put("moveDiseaseList", list);

		/* ???????????? ?????? */
		ResultVO vo = homeServiceImpl.getMoveDistance(resultVO);
		result.put("moveDistance", vo);

		result.put("result", true);

		System.out.println("????????????!!!");
		return result; // ????????? ?????? true;
	}

	/*
	 * ???????????????????????? ????????? ?????? ??? ????????????
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
	 * return result; //????????? ?????? true; }
	 */
}
