package com.ccg.io.excel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * excel工具类 提供读取和写入excel的功能
 */
public class MyExcelUtils {

	/**
	 * 表头样式--对应title
	 */
	private final static String STYLE_TITLE = "title";
    /**
     * 标题样式--对应POJO的headers
     */
    private final static String STYLE_HEADER = "header";
    /**
     * 数据样式--对应dataList
     */
    private final static String STYLE_DATA = "data";

    /**
     * 存储样式,缓存上面三种样式,供创建excel调用。用完后要clear,不同的workbook的样式不可共用，报错
     */
    private static final HashMap<String, CellStyle> cellStyleMap = new HashMap<>();
    
    private static int writeExcelNum = 0;
    
    /**
     * 读取excel，
     * 行数,列数可限制可不限制。
     * 支持数据格式：日期，数字，字符，函数公式，布尔类型
     */
    public static List<ExcelSheetPO> readExcel(File file, Integer rowCount, Integer columnCount)
            throws FileNotFoundException, IOException {

        // 根据后缀名称判断excel的版本
        String extName = FilenameUtils.getExtension(file.getPath());
        Workbook wb = null;
        if (ExcelVersion.V2003.getSuffix().equals(extName)) {
            wb = new HSSFWorkbook(new FileInputStream(file));
        } else if (ExcelVersion.V2007.getSuffix().equals(extName)) {
            wb = new XSSFWorkbook(new FileInputStream(file));
        } else {
            // 无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
            throw new IllegalArgumentException("Invalid excel version");
        }
        // 开始读取数据
        List<ExcelSheetPO> sheetPOs = new ArrayList<>();
        // 循环工作表sheets：wb.getNumberOfSheets()
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            List<List<Object>> dataList = new ArrayList<>();
            ExcelSheetPO sheetPO = new ExcelSheetPO();
            sheetPO.setSheetName(sheet.getSheetName());
            sheetPO.setDataList(dataList);
            int readRowCount = 0;
            if (rowCount == null || rowCount > sheet.getPhysicalNumberOfRows()) {
                readRowCount = sheet.getPhysicalNumberOfRows();
            } else {
                readRowCount = rowCount;
            }
            // 解析sheet 的行
            for (int j = sheet.getFirstRowNum(); j < readRowCount; j++) {
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                if (row.getFirstCellNum() < 0) {
                    continue;
                }
                int readColumnCount = 0;
                if (columnCount == null || columnCount > row.getLastCellNum()) {
                    readColumnCount = (int) row.getLastCellNum();
                } else {
                    readColumnCount = columnCount;
                }
                List<Object> rowValue = new LinkedList<Object>();
                // 解析sheet 的列
                for (int k = 0; k < readColumnCount; k++) {
                    Cell cell = row.getCell(k);
                    rowValue.add(getCellValue(wb, cell));
                }
                dataList.add(rowValue);
            }
            sheetPOs.add(sheetPO);
        }
        return sheetPOs;
    }

    private static Object getCellValue(Workbook wb, Cell cell) {
        Object columnValue = null;
        if (cell != null) {
            DecimalFormat df = new DecimalFormat("0");// 格式化数字,保留整数
            DecimalFormat df2 = new DecimalFormat("0.00");// 格式化数字,保留两位有效数字
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
            switch (cell.getCellTypeEnum()) {//switch枚举类型
            case STRING://字符串型
                columnValue = cell.getStringCellValue();
                break;
            case NUMERIC://数值型
                if ((cell.getCellStyle().getDataFormatString()).contains("@")) {
                	columnValue = cell.getNumericCellValue();//保留原值：53918.403927475614
                    df.format(cell.getNumericCellValue());//保留整数:53918
                    df2.format(cell.getNumericCellValue());//保留整数两位:53918.40
                } else if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    columnValue = df2.format(cell.getNumericCellValue());
                } else if (HSSFDateUtil.isCellDateFormatted(cell)){ //对否是日期。两种方式获取
                		columnValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));//方式一
						//columnValue = sdf.format(cell.getDateCellValue());//方式二
                } else {
                    columnValue = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }
                break;
            case BOOLEAN://布尔型
                columnValue = cell.getBooleanCellValue();
                break;
            case BLANK://空值
                columnValue = "";
                break;
            case FORMULA://公式型
                FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
                evaluator.evaluateFormulaCellEnum(cell);
                CellValue cellValue = evaluator.evaluate(cell);
                columnValue = cellValue.getStringValue(); //用getNumberValue()获取的都是0
                break;
            default:
                columnValue = cell.toString();
            }
        }
        return columnValue;
    }

    /**
     * 在服务器硬盘固定路径写入excel文件
     */
    public static void createWorkbookAtDisk(ExcelVersion version, List<ExcelSheetPO> excelSheets, String filePath)
            throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filePath);
        downloadExcelFromWorkbook(version, excelSheets, fileOut, true);
    }

    /**
     * 从数据源下载excel，将工作簿写给输出流
     * 数据封装成excelSheets后，写到输出流outStream
     * @param closeStream 是否关闭输出流
     */
    public static void downloadExcelFromWorkbook(ExcelVersion version, List<ExcelSheetPO> excelSheets,
            OutputStream outStream, boolean closeStream) throws IOException {
        if (CollectionUtils.isNotEmpty(excelSheets)) {
            Workbook wb = createWorkBook(version, excelSheets);
            wb.write(outStream);//将工作簿写进输出流
            if (closeStream) {
                outStream.close();
            }
        }
    }

    /**
     * 根据一张张工作表ExcelSheetPO,生成工作簿Workbook
     * @param version
     * @param excelSheets
     * @return
     */
    private static Workbook createWorkBook(ExcelVersion version, List<ExcelSheetPO> excelSheets) {
        Workbook wb = createWorkbook(version);
        for (int i = 0; i < excelSheets.size(); i++) {
            ExcelSheetPO excelSheetPO = excelSheets.get(i);
            if (excelSheetPO.getSheetName() == null) {
                excelSheetPO.setSheetName("sheet" + i);
            }
            // 过滤特殊字符
            Sheet tempSheet = wb.createSheet(WorkbookUtil.createSafeSheetName(excelSheetPO.getSheetName()));
            buildSheetData(wb, tempSheet, excelSheetPO, version);
        }
        return wb;
    }
    
    private static void buildSheetData(Workbook wb, Sheet sheet, ExcelSheetPO excelSheetPO, ExcelVersion version) {
        sheet.setDefaultRowHeight((short) 400);//默认工作表单元格的高度
        sheet.setDefaultColumnWidth((short) 10);
        
        //必须依次创建title，header，body。writeExcelNum记录body从第几行开始写
        writeExcelNum = 0;
        if(createTitle(sheet, excelSheetPO, wb, version)){
        	writeExcelNum ++;
        }
        if(createHeader(sheet, excelSheetPO, wb, version)){
        	writeExcelNum ++;
        }
        createBody(sheet, excelSheetPO, wb, version);
        //创建结束后，要clear掉缓存，否则其他请求会沿用此workbook的样式，报错
        cellStyleMap.clear();
    }

    private static boolean createTitle(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
    	
    	String title = excelSheetPO.getTitle();
    	if(StringUtils.isBlank(title)){
    		return false;
    	}
    	Row titleRow = sheet.createRow(writeExcelNum);
    	Cell titleCel = titleRow.createCell(0);
    	titleCel.setCellValue(title);
    	titleCel.setCellStyle(getStyle(STYLE_TITLE, wb));
    	// 限制最大列数
    	int column = excelSheetPO.getDataList().size() > version.getMaxColumn() ? version.getMaxColumn()
    			: excelSheetPO.getDataList().size();
    	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, column-1));//合并单元格
    	return true;
    }

    private static boolean createHeader(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
        String[] headers = excelSheetPO.getHeaders();
        if(headers==null||headers.length==0){
        	return false;
        }
        Row row = sheet.createRow(writeExcelNum);
        for (int i = 0; i < headers.length && i < version.getMaxColumn(); i++) {
            Cell cellHeader = row.createCell(i);
            cellHeader.setCellStyle(getStyle(STYLE_HEADER, wb));
            cellHeader.setCellValue(headers[i]);
        }
        return true;
    }

    private static void createBody(Sheet sheet, ExcelSheetPO excelSheetPO, Workbook wb, ExcelVersion version) {
        List<List<Object>> dataList = excelSheetPO.getDataList();
        for (int i = 0; i < dataList.size() && i < version.getMaxRow(); i++) {
            List<Object> values = dataList.get(i);
            Row row = sheet.createRow(writeExcelNum + i);
            for (int j = 0; j < values.size() && j < version.getMaxColumn(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(getStyle(STYLE_DATA, wb));
                cell.setCellValue(values.get(j).toString());
            }
        }
    }

    /**
     * 给工作簿wb创建样式：可创建表头、标题、内容等
     * @param wb
     * @param HorizontalAlignment 水平对齐方式，枚举型
     * @param fontsize 字体大小short类型
     * @param boldFontFlag 字体是否加粗
     * @return 单元格样式
     */
    private static CellStyle createCellStyle(Workbook wb,HorizontalAlignment horizontalAlign, 
    		short fontsize,boolean boldFontFlag) {
    	CellStyle style = wb.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);//单元格四个边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setWrapText(true);//是否自动换行
        
        //水平垂直居中
        style.setAlignment(horizontalAlign);//水平对齐方式，枚举型
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        
        //创建字体
        Font font = wb.createFont();
        font.setBold(boldFontFlag);//是否加粗字体
        font.setFontHeightInPoints(fontsize);//设置字体大小
        style.setFont(font);//加载字体
        
        return style;
    }
    
    /**
     * 获取工作簿的样式（标题,表头,数据）
     * 利用静态全局变量Map作为缓存，用完需要清除，不同的工作簿不能共用样式
     * @param type
     * @param wb
     * @return
     */
    private static CellStyle getStyle(String type, Workbook wb) {

        if (cellStyleMap.containsKey(type)) {
            return cellStyleMap.get(type);
        }
        createCellStyle(wb,HorizontalAlignment.CENTER,(short)16,true);
        
        // 生成一个样式
        CellStyle style = wb.createCellStyle();
        
        if (STYLE_TITLE == type) { //表格标题
        	style = createCellStyle(wb,HorizontalAlignment.CENTER,(short)16,true);
        } else if (STYLE_HEADER == type) {  //头部标题
        	style = createCellStyle(wb,HorizontalAlignment.CENTER,(short)14,true);
         } else if (STYLE_DATA == type) { //数据样式
         	style = createCellStyle(wb,HorizontalAlignment.CENTER,(short)12,false);
        }
        //缓存起来
        cellStyleMap.put(type, style);
        return style;
    }

    /**
     * 根据枚举类型，new一个工作簿
     * @param version
     * @return
     */
    private static Workbook createWorkbook(ExcelVersion version) {
        switch (version) {
        case V2003:
            return new HSSFWorkbook();
        case V2007:
            return new XSSFWorkbook();
        }
        return null;
    }
    
    /**
     * 从路径下载excel，功能同从路径下载文件
     * @param filePath excel所在文件路径
     * @param resp 
     * @throws Exception
     */
    public static void downLoadExcelFromPath(String filePath, HttpServletResponse resp) throws Exception {

        File file = new File(filePath);
	    if (!file.exists()) { 
	    	resp.sendError(404, "File not found!");
	    	return; 
	    }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
        byte[] buf = new byte[1024];
        int len = 0;
        OutputStream out = resp.getOutputStream();
        while ((len = br.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.flush();
        br.close();
        out.close();
    }
    /**
     * excel 列字符 得到 列索引
     * 	AA column index of 27 ;
     *  Z column index of 26 ;
     * @param colStr
     * @return
     */
    public static int getColumnIndexFromStr(String colStr) { 
    	int num = 0; 
    	int result = 0; 
    	int length = colStr.length();
    	for(int i = 0; i < length; i++) { 
    		char ch = colStr.charAt(length - i - 1); 
    		num = (int)(ch - 'A' + 1) ; 
    		num *= Math.pow(26, i); 
    		result += num; 
    	} 
    	return result; 
    }
    /**
     * excel 列索引 得到 列字符
     * 26 column in excel of Z 
     * @param columnIndex
     * @return
     */
    public static String getColumnStrFromIndex(int columnIndex) { 
    	if (columnIndex <= 0) { 
    		return null; 
    	} 
    	String columnStr = ""; 
    	columnIndex--; 
    	do { 
    		if (columnStr.length() > 0) { 
    			columnIndex--; 
    		} 
    		columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr; 
    		columnIndex = (int) ((columnIndex - columnIndex % 26) / 26); 
    	} while (columnIndex > 0); 
    	return columnStr; 
    }
}