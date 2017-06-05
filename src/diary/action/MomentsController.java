package diary.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Moments;
import diary.dao.MomentsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunine on 2017/6/1.
 */
@Controller
@RequestMapping(value="moments")
public class MomentsController {
    private MomentsDAO momentsDAO;
    @Resource
    private void setMomentsDAO(MomentsDAO momentsDAO){this.momentsDAO=momentsDAO;}
    @RequestMapping(params = "method=listNew", method = RequestMethod.POST)
    public void listMomentsNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String count=request.getParameter("count");
        JSONObject myJSON=new JSONObject();
        PrintWriter writer = response.getWriter();
        if (count == null) {
            myJSON.put("status","400");
            writer.println(myJSON.toJSONString());
            writer.flush();
            return;
        }
        List<Moments> list=momentsDAO.listNewestMoments(Integer.parseInt(count));
        JSONArray array=new JSONArray();
        for(Moments m:list){
            JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(m));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(m.getTime());
            jsonObject.put("time",dateStr);
            System.out.println(jsonObject.toJSONString());
            array.add(jsonObject.toJSONString());
        }
        myJSON.put("status","200");
        myJSON.put("data",array);
        writer.println(myJSON.toJSONString());
        writer.flush();
    }

    @RequestMapping(params = "method=listFriends", method = RequestMethod.POST)
    public void listMomentsFriends(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String count=request.getParameter("count");
        String userid=request.getParameter("userid");
        JSONObject myJSON=new JSONObject();
        PrintWriter writer = response.getWriter();
        if (count == null||userid==null) {
            myJSON.put("status","400");
            writer.println(myJSON.toJSONString());
            writer.flush();
            return;
        }
        List<Moments> list=momentsDAO.listFriendsMoments(Integer.parseInt(count), Integer.parseInt(userid));
        JSONArray array=new JSONArray();
        if (list==null) {
            myJSON.put("status","200");
            myJSON.put("data",array);
            writer.println(myJSON.toJSONString());
            writer.flush();
            return;
        }

        for(Moments m:list){
            JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(m));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(m.getTime());
            jsonObject.put("time",dateStr);
            System.out.println(jsonObject.toJSONString());
            array.add(jsonObject.toJSONString());
        }
        myJSON.put("status","200");
        myJSON.put("data",array);
        writer.println(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=listOwner",method = RequestMethod.POST)
    public void listOwner(HttpServletResponse response,HttpServletRequest request)throws  IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer=response.getWriter();
        if(id==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        List<Moments> list=momentsDAO.findMomengsByOwner(id);
        JSONArray array=new JSONArray();
        for(Moments m:list){
            JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(m));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(m.getTime());
            jsonObject.put("time",dateStr);
            System.out.println(jsonObject.toJSONString());
            array.add(jsonObject.toJSONString());
        }
        myJSON.put("data",array);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=query",method= RequestMethod.POST)
    public void query(HttpServletRequest request,HttpServletResponse response)throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if (id == null) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Moments m = momentsDAO.findMomentsById(id);
        JSONObject json=new JSONObject(JSON.parseObject(JSON.toJSONString(m)));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(m.getTime());
        json.put("time",dateStr);
        String[] likes={};
        if(m.getLikes()!=null){
            likes=momentsDAO.queryAllLikes(m.getLikes());
        }
        json.put("likes",likes);
        myJSON.put("data",json.toJSONString());
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }
    @RequestMapping(params = "method=add",method=RequestMethod.POST)
    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String content=request.getParameter("content");
        String longitude=request.getParameter("longitude");
        String latitude=request.getParameter("latitude");
        String image=request.getParameter("image");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(id==null||content==null||longitude==null||latitude==null||image==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Moments m=new Moments();
        m.setOwnerId(Long.parseLong(id));
        m.setContent(content);
        m.setLongitude(Double.parseDouble(longitude));
        m.setLatitude(Double.parseDouble(latitude));
        m.setLikeCount(0);
        m.setLikes("");
        m.setImageSrc(image);
        momentsDAO.addMoments(m);
        myJSON.put("status",200);
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=like",method = RequestMethod.POST)
    public void like(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String momentid = request.getParameter("momentid");
        String userid = request.getParameter("userid");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if(momentid==null||userid==null){
            myJSON.put("status","400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Moments m=momentsDAO.findMomentsById(momentid);
        String likes=m.getLikes();
        int count=m.getLikeCount();
        int change=1;
        if(likes==null){
            m.setLikes(""+userid);
        }else{
            ArrayList<String> list=new ArrayList<String>();
            String[] allLikes=likes.split(",");
            for(String s:allLikes){
                list.add(s);
            }
            if(list.contains(userid)){
                list.remove(userid);
                change=-1;
                String result="";
                for(String s:list){
                    result+=",";
                    result+=s;
                }
                m.setLikes(result.substring(1,result.length()));
            }else{
                m.setLikes(likes+","+userid);
            }
        }
        m.setLikeCount(count+change);
        momentsDAO.update(m);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();


    }

}
