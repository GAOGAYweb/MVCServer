package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Comments;
import diary.dao.CommentsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Sunine on 2017/6/4.
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
    private CommentsDAO commentsDAO;
    @Resource
    public void setCommentsDAO(CommentsDAO commentsDAO){this.commentsDAO=commentsDAO;}
    @RequestMapping(params = "method=add",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String nickname=request.getParameter("nickname");
        String content=request.getParameter("content");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id==null||nickname==null||content==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Comments c=new Comments();
        c.setContent(content);
        c.setMomentId(Integer.parseInt(id));
        c.setNickname(nickname);
        commentsDAO.add(c);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }
    @RequestMapping(params = "method=query",method = RequestMethod.POST)
    public void query(HttpServletRequest request,HttpServletResponse response)throws  IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        List<Comments> list=commentsDAO.queryComments(id);
        JSONArray array=new JSONArray();
        for(Comments c:list){
            JSONObject json=JSON.parseObject(JSON.toJSONString(c));
            array.add(json.toJSONString());
        }
        myJSON.put("data",array);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }
}
