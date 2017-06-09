package diary.action;

import com.alibaba.fastjson.JSONObject;
import diary.bean.Friends;
import diary.dao.FriendsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sunine on 2017/6/9.
 */
@Controller
@RequestMapping(value="/friends")
public class FriendsController {
    private FriendsDAO friendsDAO;
    @Resource
    public void setFriendsDAO(FriendsDAO friendsDAO){this.friendsDAO=friendsDAO;}

    @RequestMapping(params = "method=addGroup",method = RequestMethod.POST)
    public void addGroup(HttpServletResponse response, HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String group=request.getParameter("groupName");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if (id == null||group==null) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Friends f=new Friends();
        f.setOwnerId(Integer.valueOf(id));
        f.setGroupName(group);
        f.setFriends("");
        friendsDAO.addFriends(f);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=addFriend",method = RequestMethod.POST)
    public void  addFriend(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String friend=request.getParameter("friend");
        String group=request.getParameter("groupName");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if ((id == null||friend==null)||friendsDAO.checkFriend(id,friend)) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        if(group==null)group="MyFriends";
        friendsDAO.addFriend(id,friend,group);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=deleteGroup",method = RequestMethod.POST)
    public void  deleteGroup(HttpServletResponse response,HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String group=request.getParameter("groupName");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if (id == null||group==null) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        Friends f=friendsDAO.queryGroup(id,group);
        friendsDAO.deleteFriends(f);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=deleteFriend",method = RequestMethod.POST)
    public void deleteFriend(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String friend=request.getParameter("friend");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if ((id == null||friend==null)||!friendsDAO.checkFriend(id,friend)) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        friendsDAO.deleteFriend(id,friend);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
    @RequestMapping(params = "method=move",method = RequestMethod.POST)
    public void moveFriend(HttpServletResponse response,HttpServletRequest request) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String friend=request.getParameter("friend");
        String group=request.getParameter("groupName");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if ((id == null||group==null||friend==null)||!friendsDAO.checkFriend(id,friend)) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        friendsDAO.moveFriend(id,friend,group);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();
    }
}
