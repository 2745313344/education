package excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel  {
    public static void main(String[] args) {
        String filename="C:\\Users\\15720586534\\Desktop\\demo.xlsx";
        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());
    }
    private static List<DemoData> getData(){
        List<DemoData> list=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData=new DemoData();
            demoData.setSno(i);
            demoData.setSname("Lucy"+i);
            list.add(demoData);
        }
        return list;
    }
}
