package diary.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static diary.util.NPLUtil.keywords;
import static diary.util.NPLUtil.sentimentAnalysis;


public class testUtil {

    public static void main(String[] args) {
        // 输入数据
        List<String> data = Arrays.asList("五五开（卢本伟）疑似开挂事件可以说是游戏圈最为关注的事了。斗鱼在回应该事时披露了对卢本伟事件的处理。一是，责令其在微博、直播间向公众致歉；二是，斗鱼将视其认错改过态度，决定下一步处理措施；三是，斗鱼会严格主播管理、规范主播言行，带头整顿直播风气。");
        try {
            // 输出情感分析结果
            System.out.println("sentiment: " + sentimentAnalysis(data).get(0).get(0));
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