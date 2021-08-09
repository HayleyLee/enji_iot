package com.enji_iot.util.Util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.web.multipart.MultipartFile;

public class ExcelUtil {

	/**
	 * 导出文件
	 * 
	 * @param fileName
	 * @param excelTemplateName
	 * @param list
	 * @param resp
	 */
	public static void exportExcel(String fileName, String excelTemplateName, List<?> list, HttpServletResponse resp) {
		String path = "./" + fileName + ".xls";
		try (InputStream is = new ExcelUtil().getClass().getResourceAsStream("/" + excelTemplateName + ".xls")) {
			try (OutputStream os = new FileOutputStream(path)) {
				Context context = new Context();
				context.putVar("list", list);
				JxlsHelper.getInstance().processTemplate(is, os, context);
			}
			Thread.sleep(1000);
			// path是指欲下载的文件的路径。
			java.io.File file = new java.io.File(path);
			// 取得文件名。
			String filename = file.getName();
			// 以流的形式下载文件。
			InputStream bis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[bis.available()];
			bis.read(buffer);
			bis.close();
			// 清空response
			resp.reset();
			// 设置response的Header
			resp.addHeader("Content-Disposition",
					"attachment;filename=" + new String(filename.getBytes("gb2312"), "ISO8859-1"));
			resp.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(resp.getOutputStream());
			resp.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			// 删除生成的临时文件
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("D:/cjsworkspace/abc.csv");
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");

			CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("姓名", "年龄", "家乡");
			CSVPrinter csvPrinter = new CSVPrinter(osw, csvFormat);

			// csvPrinter = CSVFormat.DEFAULT.withHeader("姓名", "年龄",
			// "家乡").print(osw);

			for (int i = 0; i < 10; i++) {
				csvPrinter.printRecord("张三", 20, "湖北");
			}

			csvPrinter.flush();
			csvPrinter.close();
		} catch (Exception w) {
			w.printStackTrace();
		}
	}
	
	
	/**
	 * excel 导入
	 * @param file
	 * @param clazz
	 * @param startColumn
	 * @param columnLength
	 * @param fields
	 * @param startRow
	 * @return
	 * @throws IOException
	 */
	public  static <T> List<T> getExcelInfo(MultipartFile file, Class<T> clazz,Integer startColumn,Integer columnLength ,
				String[] fields ,Integer startRow) throws IOException {
		
		Workbook wb = null;
	
		File f = File.createTempFile("tmp", null);
		file.transferTo(f);
		FileInputStream fis = new FileInputStream(f);
		String fileName = file.getOriginalFilename ();  
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);  
		
		if ("xls".equals(suffix)) {
			wb = new HSSFWorkbook(fis);				
		} else if ("xlsx".equals(suffix)) {
			wb = new XSSFWorkbook(fis);
		}
		
		if (wb == null) {
			return null;
		}
		List datas = new ArrayList();
		try {
			//
			Integer index = 0;
			Sheet sheet = wb.getSheetAt(index);
			if(startRow == null){
				startRow = 1;
			}
			for (int rowNum = startRow ; rowNum <=  sheet.getLastRowNum(); rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = startColumn ;
				//int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int lastCellNum = columnLength ;
				//int lastCellNum = firstCellNum + fields.length;		// row.getPhysicalNumberOfCells();
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				//
				Object o = clazz.newInstance();
				Class[] fieldsTypes = new Class[fields.length];
				for (int i = 0; i < fields.length; i++) {
					fieldsTypes[i] = clazz.getDeclaredField(fields[i]).getType();
				}
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					Object value = null;
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						Double v = new Double(cell.getNumericCellValue());
						if (fieldsTypes[cellNum].getSimpleName().equals("Long")) {
							value = v.longValue();
						} else if (fieldsTypes[cellNum].getSimpleName().equals("Integer")) {
							value = v.intValue();
						} else if (fieldsTypes[cellNum].getSimpleName().equals("String")) {
							if( v.intValue() +0 == v ){
								value = v.intValue()+"" ;
							}else{
								value = v.toString();								
							}
						} else if (fieldsTypes[cellNum].getSimpleName().equals("Double")) {
							value =v ;
						} 
					} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						value = cell.getStringCellValue();
					}
					// 通过反射,将值设置到对象里面
					Method setter = clazz.getMethod("set" + upFirstChar(fields[cellNum]),
							new Class[] { fieldsTypes[cellNum] });
					setter.invoke(o, value);
				}
				if(datas.size() < 5000)
					datas.add(o);
				
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
	
	
	/**
	 * 获取excel里的数据集合
	 * @param wb
	 * @param clazz
	 * @param fields
	 * @return
	 */
	public static List getExcelData(Workbook wb, Class clazz, String[] fields) {
		if (wb == null) {
			return null;
		}
		List datas = null;
		try {
			//
			Integer index = 0;
			if (index == null) {
				return datas;
			}
			Sheet sheet = wb.getSheetAt(index);
			datas = new ArrayList();
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了第一行的所有列
			for (int rowNum = firstRowNum + 2; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int lastCellNum = firstCellNum + fields.length;// row.getPhysicalNumberOfCells();
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				//
				Object o = clazz.newInstance();
				Class[] fieldsTypes = new Class[fields.length];
				for (int i = 0; i < fields.length; i++) {
					fieldsTypes[i] = clazz.getDeclaredField(fields[i]).getType();
				}
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					Object value = null;
					if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
						Double v = new Double(cell.getNumericCellValue());
						if (fieldsTypes[cellNum].getSimpleName().equals("Long")) {
							value = v.longValue();
						} else if (fieldsTypes[cellNum].getSimpleName().equals("Integer")) {
							value = v.intValue();
						} else if (fieldsTypes[cellNum].getSimpleName().equals("String")) {
							value = v.toString();
						} 
					} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						value = cell.getStringCellValue();
					}
					// 通过反射,将值设置到对象里面
					Method setter = clazz.getMethod("set" + upFirstChar(fields[cellNum]),
							new Class[] { fieldsTypes[cellNum] });
					setter.invoke(o, value);
				}
				if(datas.size() < 1000)
					datas.add(o);
				
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}

	private static String upFirstChar(String str) {
		return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
	}
	
}
