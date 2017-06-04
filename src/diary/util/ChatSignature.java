package diary.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sunine on 2017/6/3.
 */
public class ChatSignature {
    private static final String appkey="979c2bfbd0c179d6fa9517c9";
    private static final String random_str="022cd9fd995849b58b3ef0e943421ed9";
    private static final String secret="d8e0431c3f7a1f10cc56f58d";
    public static JSONObject chatParams(){
        HashMap<String,String> result=new HashMap<>();
        result.put("appkey",appkey);
        System.out.println(appkey);
        result.put("random_str",random_str);
        System.out.println(random_str);
        String time=System.currentTimeMillis()+"";
        System.out.println(time);
        String signature=Encoder.EncoderByMd5("appkey="+appkey+"&timestamp="+time+"&random_str="+random_str+"&key="+secret);
        System.out.println(signature);
        result.put("signature",signature);
        result.put("timestamp",time);
        JSONObject json=new JSONObject();
        json.put("appkey",result.get("appkey"));
        json.put("random_str",result.get("random_str"));
        json.put("signature",result.get("signature"));
        json.put("timestamp",result.get("timestamp"));
        return json;

    }
    public static void main(String[] args){

        System.out.println(chatParams().toJSONString());

    }

}
