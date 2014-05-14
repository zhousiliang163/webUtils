package com.util.excel;

import java.io.FileInputStream;

import jxl.CellType;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.examples.CellTypes;
import org.apache.poi.ss.format.CellTextFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	private static String _fileName="C:\\Users\\xiaozhou\\Desktop\\导出供应商数据20140417.xlsx";
	public static void main(String[] args) {
		ReadExcel.readXlsx(_fileName);
	}
	/**
	 * read excel file
	 *
	 * @author  xiaozhou   
	 * @date 2014-5-13 下午04:56:06 
	 * @param fileName
	 */
	public static void  readXls(String fileName){
		try{
			FileInputStream fileInputStream=new FileInputStream(fileName);
			// 创建对Excel工作簿文件的引用
			HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
			// 创建对工作表的引用。
			// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
			int sheet_num=workbook.getNumberOfSheets();
			for (int i = 0; i < sheet_num; i++) {
				HSSFSheet sheet = workbook.getSheetAt(i);
				// 也可用getSheetAt(int index)按索引引用，
				// 在Excel文档中，第一张工作表的缺省索引是0，
				// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
				// 读取左上端单元
				 int row_num=sheet.getLastRowNum()+1;
				 for (int j = 0; j < row_num; j++) {
					    HSSFRow row = sheet.getRow(j);
						int cell_num=row.getLastCellNum()+1;
						for (int k = 0; k < cell_num; k++) {
							 HSSFCell cell=row.getCell(k);
							 
							 System.out.print("左上端单元是： " + getValue(cell)+"     ;");
						}
						System.out.println();
						// 输出单元内容，cell.getStringCellValue()就是取所在单元的值
				}
				
			}
			
			}catch(Exception e) {
			System.out.println("已运行xlRead() : " + e );
			}
		
	}
	
	  @SuppressWarnings("static-access")  
	  private static String getValue(Cell cell){  
	    if(cell.getCellType() == cell.CELL_TYPE_BOOLEAN){  
	      return String.valueOf( cell.getBooleanCellValue());  
	    }else if(cell.getCellType() == cell.CELL_TYPE_NUMERIC){  
	      return String.valueOf( cell.getNumericCellValue());  
	    }else{  
	      return String.valueOf( cell.getStringCellValue());  
	    }  
	  }  
	  

	/**
	 * read excel file
	 *
	 * @author  xiaozhou   
	 * @date 2014-5-13 下午04:56:06 
	 * @param fileName
	 */
	public static void  readXlsx(String fileName){
		try{
			FileInputStream fileInputStream=new FileInputStream(fileName);
			// 创建对Excel工作簿文件的引用
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
			// 创建对工作表的引用。
			// 本例是按名引用（让我们假定那张表有着缺省名"Sheet1"）
			int sheet_num=workbook.getNumberOfSheets();
			for (int i = 0; i < sheet_num; i++) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// 也可用getSheetAt(int index)按索引引用，
				// 在Excel文档中，第一张工作表的缺省索引是0，
				// 其语句为：HSSFSheet sheet = workbook.getSheetAt(0);
				// 读取左上端单元
				 int row_num=sheet.getLastRowNum()+1;
				 for (int j = 0; j < row_num; j++) {
					   XSSFRow row = sheet.getRow(j);
						int cell_num=row.getLastCellNum();
						for (int k = 0; k < cell_num; k++) {
							 XSSFCell cell=row.getCell(k);
							 System.out.println(j);
							 if (cell!=null) {
								// System.out.print(" " + getValue(cell)+"     ;");
							}else{
								//System.out.println("null    ;");
							}
						}
						System.out.println();
						// 输出单元内容，cell.getStringCellValue()就是取所在单元的值
				}
				
			}
			
			}catch(Exception e) {
			System.out.println("已运行xlRead() : " + e );
			}
		
	}

}
