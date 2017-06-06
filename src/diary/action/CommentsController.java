package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Comments;
import diary.bean.User;
import diary.dao.CommentsDAO;
import diary.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Sunine on 2017/6/4.
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
    private CommentsDAO commentsDAO;
    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao){this.userDao=userDao;}
    @Resource
    public void setCommentsDAO(CommentsDAO commentsDAO){this.commentsDAO=commentsDAO;}
    @RequestMapping(params = "method=add",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String userid=request.getParameter("userid");
        String content=request.getParameter("content");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id==null||content==null||userid==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Comments c=new Comments();
        User u=userDao.findUserById(userid);
        c.setContent(content);
        c.setMomentId(Integer.parseInt(id));
        c.setNickname(u.getNickName());
        c.setAvatar(u.getImageSrc());
        c.setTime(new Date());

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
        if(list!=null){
            for(Comments c:list){
                JSONObject json=JSON.parseObject(JSON.toJSONString(c));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(c.getTime());
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
