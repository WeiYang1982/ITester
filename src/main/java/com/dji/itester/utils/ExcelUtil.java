package com.dji.itester.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.dji.itester.bean.ParamBean;

/**
 * Excel读写封装
 * 
 * @author Charlie.Chen
 */
public class ExcelUtil {

	// 读取Excel中数据
	public static List<ParamBean> readExcel(String excelPath) throws Exception {

		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excelPath));
		
		HSSFSheet sheet = wb.getSheetAt(0);

		// 获得EXCEL sheet行数
		int rowNums = sheet.getLastRowNum();
		// 获得Excell列数
		// int columnNum=row.getPhysicalNumberOfCells();

		List<ParamBean> paramList = new ArrayList<ParamBean>();
		for (int i = 1; i <= rowNums; i++) {
			HSSFRow row = sheet.getRow(i);

			ParamBean param = new ParamBean();
			param.setEmail(row.getCell(0).getStringCellValue());
			param.setPassword(row.getCell(1).getStringCellValue());
			param.setExpResu(row.getCell(2).getStringCellValue());

			paramList.add(param);
		}
		return paramList;

	}

	/**
	 * 写入Excel，在任意坐标处写入数据。 String value：你要输入的内容 int x ： 行坐标，Excel从 0 算起 int y :
	 * 列坐标，Excel从 0 算起
	 */
	public static void writeCell(int x, int y, String value,String excelPath) {
		try {
			// 创建Excel的工作书册 Workbook,对应到一个excel文档
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(
					excelPath));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = sheet.getRow(x);
			HSSFCell cell = row.createCell(y);
			cell.setCellValue(value);
			FileOutputStream os = new FileOutputStream(excelPath);
			;
			wb.write(os);
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String [] args){
		String path=System.getProperty("user.dir")+"/src/main/java/com/dji/itester/data/params.xlsx";
		System.out.println(System.getProperty("user.dir")+"/src/main/java/com/dji/itester/data/params.xlsx");
		try {
			List<ParamBean> param=ExcelUtil.readExcel(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}