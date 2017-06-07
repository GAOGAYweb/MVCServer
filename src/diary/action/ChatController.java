package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Message;
import diary.dao.MessageDAO;
import diary.util.ChatSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sunine on 2017/6/3.
 */
@Controller

public class ChatController {
    private MessageDAO messageDAO;
    @Resource
    public void setMessageDAO(MessageDAO messageDAO){this.messageDAO=messageDAO;}
    @RequestMapping(value="/chat")
    public void chat(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        writer.write(ChatSignature.chatParams().toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/message/add")
    public  void add(HttpServletResponse response, HttpServletRequest request)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String content=request.getParameter("content");
        String from=request.getParameter("from");
        String to=request.getParameter("to");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(from==null||to==null||content==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Message m=new Message();
        m.setFromName(from);
        m.setToName(to);
        m.setContent(content);
        m.setTime(new Date());
        messageDAO.add(m);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="/message/query")
    public void query(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String from=request.getParameter("from");
        String to=request.getParameter("to");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(from==null||to==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        List<Message> list=messageDAO.query(from,to);
        JSONArray array=new JSONArray();
        if(list!=null){
            for(Message m:list){
                JSONObject json= JSON.parseObject(JSON.toJSONString(m));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(m.getTime());
                json.put("time",dateStr);
                array.add(json.toJSONString());
            }
        }
        myJSON.put("data",array);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }
}
