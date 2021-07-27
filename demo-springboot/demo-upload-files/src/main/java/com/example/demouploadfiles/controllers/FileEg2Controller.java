package com.example.demouploadfiles.controllers;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.cell.CellEditor;
import com.example.demouploadfiles.vo.MdKeywordList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取 关键词清单列表
 *
 * @author zhangxinqiang
 * @create 2021/7/17 15:18
 */
public class FileEg2Controller {


    private static List<MdKeywordList> getMdKeywordListsFromExcel(String fileName) {
        ExcelReader reader = ExcelUtil.getReader(fileName);

        Map<String, String> headerAlias = getHeaderAlias();
        reader.setHeaderAlias(headerAlias);

        CellEditor editor = new CellEditor() {
            @Override
            public Object edit(Cell cell, Object value) {
                if (value == null) {
                    return null;
                }
                String val = value.toString().replaceAll("[\\t\r\n]", "");
                return "".equals(val) ? null : val;
            }
        };
        reader.setCellEditor(editor);

        Object date = reader.readCellValue(1, 5);
        System.out.println(date.toString());
        List<MdKeywordList> read = reader.read(6, 7, Integer.MAX_VALUE, MdKeywordList.class);
        return read;
    }

    public static void main(String[] args) {
//        String fileName = "d:/PM-102-【任务2】关键词清单导入模板.xlsx";
//
//        List<MdKeywordList> read = getMdKeywordListsFromExcel(fileName);
//
//        System.out.println(" rows size " + read.size());
//        for (MdKeywordList mdKeywordList : read) {
//            System.out.println(mdKeywordList);
//        }

        ExcelWriter writer = ExcelUtil.getWriter("d:/test.xlsx");
        writer.setDefaultRowHeight(105);
        writer.setColumnWidth(-1, 30);

        //测试写入10个图片
        for (int i = 0; i < 10; i++) {
            //读取图片
            byte[] pictureData = FileUtil.readBytes("d:/1.jpg");
            HttpUtil.downloadBytes("");

            //写入图片
            writePic(writer, 0, i, pictureData, HSSFWorkbook.PICTURE_TYPE_JPEG);
        }
        writer.close();

        System.out.println("success");
    }

    private static void writePic(ExcelWriter writer, int x, int y, byte[] pictureData, int picType) {
        Sheet sheet = writer.getSheet();
        Drawing drawingPatriarch = sheet.createDrawingPatriarch();

        //设置图片单元格位置
        ClientAnchor anchor = drawingPatriarch.createAnchor(0, 0, 0, 0, x, y, x + 1, y + 1);
        //随单元格改变位置和大小
        anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

        //添加图片
        int pictureIndex = sheet.getWorkbook().addPicture(pictureData, picType);
        drawingPatriarch.createPicture(anchor, pictureIndex);
    }

    private static Map<String, String> getHeaderAlias() {
        Map<String, String> headerAlias = new HashMap<>();
        headerAlias.put("平台", "platform");
        headerAlias.put("品牌", "brand");
        headerAlias.put("关键词编码", "keywordCode");
        headerAlias.put("关键词", "keyword");
        headerAlias.put("一级类目编码", "category1Code");
        headerAlias.put("一级类目", "category1");
        headerAlias.put("二级类目编码", "category2Code");
        headerAlias.put("二级类目", "category2");
        headerAlias.put("三级类目编码", "category3Code");
        headerAlias.put("三级类目", "category3");
        headerAlias.put("老NC代码", "ncCodeOld");
        headerAlias.put("老NC类目", "ncCategoryOld");
        headerAlias.put("新NC代码", "ncCodeNew");
        headerAlias.put("新NC类目", "ncCategoryNew");
        headerAlias.put("建议零售价（元）", "retailPrice");
        headerAlias.put("收费类型", "feeType");
        headerAlias.put("综合服务费收费标准（元）", "integrateServiceFee");
        headerAlias.put("必选配套辅料1NC编码", "subMaterialMust1Code");
        headerAlias.put("必选配套辅料1", "subMaterialMust1");
        headerAlias.put("必选配套辅料2NC编码", "subMaterialMust2Code");
        headerAlias.put("必选配套辅料2", "subMaterialMust2");
        headerAlias.put("必选配套辅料3NC编码", "subMaterialMust3Code");
        headerAlias.put("必选配套辅料3", "subMaterialMust3");
        headerAlias.put("必选配套辅料4NC编码", "subMaterialMust4Code");
        headerAlias.put("必选配套辅料4", "subMaterialMust4");
        headerAlias.put("可选配套辅料1NC编码", "subMaterialOptional1Code");
        headerAlias.put("可选配套辅料1", "subMaterialOptional1");
        headerAlias.put("可选配套辅料2NC编码", "subMaterialOptional2Code");
        headerAlias.put("可选配套辅料2", "subMaterialOptional2");
        headerAlias.put("商标注册证类目", "trademarkCategory");
        headerAlias.put("商标注册证编码", "trademarkCode");
        headerAlias.put("可授权类目（商标分类）", "trademarkAuthorizedCategory");
        headerAlias.put("合格证1", "certificate1");
        headerAlias.put("合格证2", "certificate2");
        headerAlias.put("强制性认证资质", "ccc");
        headerAlias.put("备注", "remarks");
//        headerAlias.put("序号", "1");
//        headerAlias.put("平台", "2");
//        headerAlias.put("品牌", "3");
//        headerAlias.put("关键词编码", "4");
//        headerAlias.put("关键词", "5");
//        headerAlias.put("一级类目编码", "6");
//        headerAlias.put("一级类目", "7");
//        headerAlias.put("二级类目编码", "8");
//        headerAlias.put("二级类目", "9");
//        headerAlias.put("三级类目编码", "10");
//        headerAlias.put("三级类目", "11");
//        headerAlias.put("老NC代码", "12");
//        headerAlias.put("老NC类目", "13");
//        headerAlias.put("新NC代码", "14");
//        headerAlias.put("新NC类目", "15");
//        headerAlias.put("建议零售价（元）", "16");
//        headerAlias.put("收费类型", "17");
//        headerAlias.put("综合服务费收费标准（元）", "18");
//        headerAlias.put("必选配套辅料1NC编码", "19");
//        headerAlias.put("必选配套辅料1", "20");
//        headerAlias.put("必选配套辅料2NC编码", "21");
//        headerAlias.put("必选配套辅料2", "22");
//        headerAlias.put("必选配套辅料3NC编码", "23");
//        headerAlias.put("必选配套辅料3", "24");
//        headerAlias.put("必选配套辅料4NC编码", "25");
//        headerAlias.put("必选配套辅料4", "26");
//        headerAlias.put("可选配套辅料1NC编码", "27");
//        headerAlias.put("可选配套辅料1", "28");
//        headerAlias.put("可选配套辅料2NC编码", "29");
//        headerAlias.put("可选配套辅料2", "30");
//        headerAlias.put("商标注册证类目", "31");
//        headerAlias.put("商标注册证编码", "32");
//        headerAlias.put("可授权类目（商标分类）", "33");
//        headerAlias.put("合格证1", "34");
//        headerAlias.put("合格证2", "35");
//        headerAlias.put("强制性认证资质", "36");
//        headerAlias.put("备注", "37");
        return headerAlias;
    }

    public void p1() {
        ExcelReader reader = ExcelUtil.getReader("d:/xxxx.xlsx");

        List<Map<String, Object>> readAll = reader.read(6, 7, Integer.MAX_VALUE);
        System.out.println(" rows size " + readAll.size());
        for (Map<String, Object> stringObjectMap : readAll) {
            stringObjectMap.forEach((k, v) -> System.out.print("(" + k + " : " + v + ")"));
            System.out.println();
        }
    }

    public void aTest() {
        System.out.println("");
    }
}

