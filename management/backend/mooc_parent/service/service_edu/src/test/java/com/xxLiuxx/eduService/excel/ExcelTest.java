package com.xxLiuxx.eduService.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExcelTest {
    @Test
    public void excelWriteTest() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            list.add(new DemoData(i,"lucy"));
        }

        String fileName = "/Users/liuyuchen/Downloads/test.xlsx";
        EasyExcel.write(fileName, DemoData.class).sheet("student list").doWrite(list);
    }

    @Test
    public void excelReadTest() {
        String fileName = "/Users/liuyuchen/Downloads/test.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
