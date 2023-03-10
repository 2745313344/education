package com.atguigu.vod.Utils;

import java.util.*;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormatFactory;
import net.sourceforge.pinyin4j.format.*;

public class PinyinSorter {

    public static void main(String[] args) {
        // 测试数据
        List<String> names = new ArrayList<>();
        names.add("张三");
        names.add("李四");
        names.add("王五");
        names.add("赵六");
        names.add("钱七");
        names.add("爱丽丝");
        names.add("Zigbee");

        // 排序
        List<String> sortedNames = sortNamesByPinyin(names);

        // 输出结果
        System.out.println("按照拼音排序后的结果：");
        for (String name : sortedNames) {
            System.out.println(name);
        }
    }

    /**
     * 根据汉字拼音排序
     * @param names 待排序的姓名列表
     * @return 排序后的姓名列表
     */
    public static List<String> sortNamesByPinyin(List<String> names) {
        // 创建拼音输出格式对象
        HanyuPinyinOutputFormat format =  new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
//        format.setCaseType(HanyuPinyinOutputFormat.CaseType.UPPERCASE); // 大写输出
//        format.setToneType(HanyuPinyinOutputFormat.ToneType.WITHOUT_TONE); // 不带声调

        // 构造一个包含姓名和拼音的映射表
        Map<String, String> namePinyinMap = new HashMap<>();
        for (String name : names) {
            StringBuilder pinyinBuilder = new StringBuilder();
            for (char c : name.toCharArray()) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) { // 中文字符
                    try {
                        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c, format);
                        pinyinBuilder.append(pinyins[0]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else { // 非中文字符
                    pinyinBuilder.append(c);
                }
            }
            namePinyinMap.put(name, pinyinBuilder.toString());
        }

        // 按照拼音排序
        List<Map.Entry<String, String>> entryList = new ArrayList<>(namePinyinMap.entrySet());
        entryList.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()));

        // 构造排序后的姓名列表
        List<String> sortedNames = new ArrayList<>();
        for (Map.Entry<String, String> entry : entryList) {
            sortedNames.add(entry.getKey());
        }
        return sortedNames;
    }
}

