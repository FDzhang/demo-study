package cn.seasun.demoeasyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author : zxq
 * @create : 2022/4/11 15:03
 */
@Data
public class DemoData2 {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
    @ExcelProperty("数字标题")
    private Double doubleData;
    @ExcelProperty("字符串标题2")
    private String string2;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}