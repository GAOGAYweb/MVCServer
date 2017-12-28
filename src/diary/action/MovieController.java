package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Comments;
import diary.bean.User;
import diary.dao.CommentsDAO;
import diary.dao.UserDao;
import diary.util.MovieUtil;
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
 * Created by MSI on 2017/12/26.
 */
@Controller
@RequestMapping(value="/movie")
public class MovieController {
    private CommentsDAO commentsDAO;
    private UserDao userDao;
    @Resource
    public void setUserDao(UserDao userDao){this.userDao=userDao;}
    @Resource
    public void setCommentsDAO(CommentsDAO commentsDAO){this.commentsDAO=commentsDAO;}
    @RequestMapping(value="list", method = RequestMethod.POST)
    public void list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("movie/list");
        response.setContentType("application/json");

        String type = request.getParameter("type");
        String offset =  request.getParameter("offset");
        String limit = request.getParameter("limit");

        JSONObject jsonObject=new JSONObject();
        PrintWriter writer = response.getWriter();
        if(type == null || offset == null||limit==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        String result=MovieUtil.movieList(type,Integer.parseInt(offset),Integer.parseInt(limit));
        writer.write(result);
        writer.flush();
    }
    @RequestMapping(value="detail", method = RequestMethod.POST)
    public void detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("movie/detail");
        response.setContentType("application/json");

        String id = request.getParameter("id");

        JSONObject jsonObject=new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id == null ){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        String result=MovieUtil.movieDetail(Integer.parseInt(id));
        writer.write(result);
        writer.flush();
    }
    @RequestMapping(value="addComment",method = RequestMethod.POST)
    public void addComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("movie/detail");
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String movie=request.getParameter("movie");
        String content=request.getParameter("content");
        JSONObject jsonObject=new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id == null ||movie==null||content==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        User u=userDao.findUserById(id);
        Comments c=new Comments();
        c.setContent(content.replace("\n","<br>"));
        c.setMovieId(Integer.parseInt(movie));
        c.setNickname(u.getNickName());
        c.setAvatar(u.getImageSrc());
        c.setTime(new Date());
        commentsDAO.add(c);
        jsonObject.put("status",200);
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="listComment",method=RequestMethod.POST)
    public void listComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
        List<Comments> list=commentsDAO.queryShortComments(id);
        JSONArray array=new JSONArray();
        if(list!=null){
            for(Comments c:list){
                JSONObject json= JSON.parseObject(JSON.toJSONString(c));
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
    @RequestMapping(value="addMovieComment",method = RequestMethod.POST)
    public void addMovieComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("movie/detail");
        response.setContentType("application/json");
        String id = request.getParameter("id");
        String movie=request.getParameter("movie");
        String content=request.getParameter("content");
        String title =request.getParameter("title");
        JSONObject jsonObject=new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id == null ||movie==null||content==null||title==null){
            jsonObject.put("status",400);
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        User u=userDao.findUserById(id);
        Comments c=new Comments();
        c.setTitle(title);
        c.setContent(content.replace("\n","<br>"));
        c.setMovieId(Integer.parseInt(movie));
        c.setNickname(u.getNickName());
        c.setAvatar(u.getImageSrc());
        c.setTime(new Date());
        commentsDAO.add(c);
        jsonObject.put("status",200);
        writer.write(jsonObject.toJSONString());
        writer.flush();
    }
    @RequestMapping(value="listMovieComment",method=RequestMethod.POST)
    public void listMovieComment(HttpServletRequest request,HttpServletResponse response) throws IOException {
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
        List<Comments> list=commentsDAO.queryMovieComments(id);
        JSONArray array=new JSONArray();
        if(list!=null){
            for(Comments c:list){
                JSONObject json= JSON.parseObject(JSON.toJSONString(c));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = sdf.format(c.getTime());
                String cut=c.getContent();
                if(cut.length()>=70){
                    cut=cut.substring(0,70);
                }
                json.put("time",dateStr);
                json.put("short",cut);
                array.add(json.toJSONString());
            }
        }

        myJSON.put("data",array);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
}
