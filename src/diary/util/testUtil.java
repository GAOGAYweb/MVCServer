package diary.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static diary.util.NPLUtil.keywords;
import static diary.util.NPLUtil.sentimentAnalysis;


public class testUtil {

    public static void main(String[] args) {
        // 输入数据
        List<String> data = Arrays.asList("123");
        try {
            // 输出情感分析结果
            System.out.println("sentiment: " + sentimentAnalysis(data));
            // 输出关键词，阈值（0-1）可以自己设定
            List<List<String>> list=keywords(data, 0.1);
            String result=list.get(0).toString();
            String tag="";
            for(String s:list.get(0)){
                tag+=",";
                tag+=s;
            }
            System.out.println("keywords:" + result);
            System.out.println("keywords:" + tag.substring(1,tag.length()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}