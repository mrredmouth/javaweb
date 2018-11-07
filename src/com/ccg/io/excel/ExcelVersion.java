package com.ccg.io.excel;

import lombok.Getter;
import lombok.Setter;

/**
 * excel版本枚举
 */
public enum ExcelVersion {
	
	 /**
     * 虽然V2007版本支持最大支持1048575 * 16383 ， 
     * V2003版支持65535*255
     * 但是在实际应用中如果使用如此庞大的对象集合会导致内存溢出，
     * 因此这里限制最大为10000*100，如果还要加大建议先通过单元测试进行性能测试。
     * 1000*100 全部导出预计时间为27s左右
     */
	V2003("xls", 10000, 100), V2007("xlsx", 100, 100);
	
	@Getter@Setter
    private String suffix;
	@Getter@Setter
    private int maxRow;
	@Getter@Setter
    private int maxColumn;

    ExcelVersion(String suffix, int maxRow, int maxColumn) {
        this.suffix = suffix;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
    }
}
