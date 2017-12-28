package diary.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static diary.util.NPLUtil.keywords;
import static diary.util.NPLUtil.sentimentAnalysis;


public class testUtil {

    public static void main(String[] args) {
        // 输入数据
//        List<String> data = Arrays.asList("五五开（卢本伟）疑似开挂事件可以说是游戏圈最为关注的事了。斗鱼在回应该事时披露了对卢本伟事件的处理。一是，责令其在微博、直播间向公众致歉；二是，斗鱼将视其认错改过态度，决定下一步处理措施；三是，斗鱼会严格主播管理、规范主播言行，带头整顿直播风气。");
//        try {
//            // 输出情感分析结果
//            System.out.println("sentiment: " + sentimentAnalysis(data).get(0).get(0));
//            // 输出关键词，阈值（0-1）可以自己设定
//            List<List<String>> list=keywords(data, 0.1);
//            String result=list.get(0).toString();
//            String tag="";
//            for(String s:list.get(0)){
//                tag+=",";
//                tag+=s;
//            }
//            System.out.println("keywords:" + result);
//            System.out.println("keywords:" + tag.substring(1,tag.length()));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.out.println(("之前网上流传了一篇极度丑化冯小刚的文章，说他是来自北京底层胡同世界的冯裤子，混迹于一群部队大院高干子弟中间，靠着一把鼻涕 一包眼泪，骗取同情和上位的故事。\n" +
                "\n" +
                "文章里还写，某位高干子弟出面，才帮冯裤子找军区借到了坦克，于是才有了冯裤子一炮走红的中国首部贺岁片《甲方乙方》里，英达过巴顿将军的瘾那场全是坦克的戏。\n" +
                "\n" +
                "然而在电影《芳华》里，冯小刚又借到了坦克，可这一次，他却不是为了逗人家笑的，他在用心讲述一个西南军区文工团的故事，还有那些被无情的国家机器和肆意的时代车轮所碾碎的善良与芳华。\n").replace("\n","<br>"));
    }
}