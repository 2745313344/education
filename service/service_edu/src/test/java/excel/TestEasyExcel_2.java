package excel;

import com.alibaba.excel.EasyExcel;

public class TestEasyExcel_2 {
    public static void main(String[] args) {
        String filename="C:\\Users\\15720586534\\Desktop\\demo.xlsx";
        EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
}
