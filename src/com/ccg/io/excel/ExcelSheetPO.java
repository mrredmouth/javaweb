package com.ccg.io.excel;
import java.util.List;

import lombok.Data;


/**
 * 定义表格的数据对象
 */
@Data
public class ExcelSheetPO {

    /**
     * sheet的名称
     */
    private String sheetName;

 
    /**
     * 表格标题
     */
    private String title;

    /**
     * 头部标题集合
     */
    private String[] headers;

    /**
     * 数据集合，每一行数据是一个List<Object>
     */
    private List<List<Object>> dataList;

}
