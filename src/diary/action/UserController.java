package diary.action;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import diary.bean.Friends;
import diary.bean.User;
import diary.dao.FriendsDAO;
import diary.dao.UserDao;
import diary.util.Encoder;
import diary.util.MyJSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserDao userDao;
    private FriendsDAO friendsDAO;
    @Resource
    public void  setFriendsDAO(FriendsDAO friendsDAO){this.friendsDAO=friendsDAO;}
    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(params = "method=login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        System.out.println("password: " + password + "account:" + account);
        User user = userDao.login(account, Encoder.EncoderByMd5(password));
        System.out.println(user);
        MyJSON myJSON = new MyJSON();
        PrintWriter writer = response.getWriter();
        if (user == null) {
            myJSON.setStatus("400");
            writer.println(myJSON.toJSONString());
            writer.flush();
            return;
        }

        request.getSession().setAttribute("user", user);
        myJSON.setStatus("200");
        myJSON.putData("id", user.getId() + "");
        myJSON.putData("account", user.getAccount());
        writer.println(myJSON.toJSONString());
        writer.flush();
    }

    @RequestMapping(params = "method=register", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("register");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String account = request.getParameter("account");
        String password =  request.getParameter("password");
        String gender = request.getParameter("gender");

        MyJSON myJSON = new MyJSON();
        PrintWriter writer = response.getWriter();
        if(account == null || password == null||gender==null){
            this.sendBadRequest(myJSON,writer);
            return;
        }
        User user = userDao.findUserByAccount(account);
        if (user == null) {
            user = new User();
            user.setAccount(account);
            user.setNickName(account);
            user.setGender(Integer.valueOf(gender));
            user.setDescription("This guy is shy and nothing was written...");
            user.setImageSrc("default.jpg");
            System.out.println(Encoder.EncoderByMd5(password));
            user.setPassword(Encoder.EncoderByMd5(password));
            request.getSession().setAttribute("user", user);
            userDao.addUser(user);
            User u=userDao.findUserByAccount(account);
            Friends f=new Friends();
            f.setFriends("");
            f.setGroupName("MyFriends");
            f.setOwnerId((int) u.getId());
            friendsDAO.addFriends(f);
            myJSON.setStatus("200");
            writer.println(myJSON.toJSONString());
        } else
            this.sendBadRequest(myJSON,writer);
        writer.flush();
    }

    @RequestMapping(params = "method=info",method = RequestMethod.POST)
    public void info(HttpServletRequest request,HttpServletResponse response) throws IOException {
        System.out.println("info");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer=response.getWriter();
        String id=request.getParameter("id");
        JSONObject result=new JSONObject();
        JSONObject jsonObject=new JSONObject();
        if(id==null){
            jsonObject.put("status","400");
            writer.write(jsonObject.toJSONString());
            writer.flush();
            return;
        }
        User user=userDao.findUserById(id);
        jsonObject.put("name",user.getNickName()+"");
        jsonObject.put("description",user.getDescription()+"");
        jsonObject.put("gender",user.getGender()+"");
        int friendsNum=friendsDAO.queryFriendsNum(id);
        jsonObject.put("friendsNum",friendsNum);
        jsonObject.put("imageSrc",user.getImageSrc()+"");
        result.put("data",jsonObject.toJSONString());
        result.put("status","200");
        writer.write(result.toJSONString());
        writer.flush();


    }
    @RequestMapping (params = "method=update",method=RequestMethod.POST)
    public void update(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String nickName=request.getParameter("nickname");
        String description=request.getParameter("description");
        String gender=request.getParameter("gender");
        String image=request.getParameter("imageSrc");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
        if (id == null||userDao.findUserById(id)==null) {
            myJSON.put("status", "400");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        User user=userDao.findUserById(id);
        if(nickName!=null)user.setNickName(nickName);
        if(description!=null)user.setDescription(description);
        if(gender!=null)user.setGender(Integer.valueOf(gender));
        if(image!=null)user.setImageSrc(image);
        userDao.updateUser(user);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }

    @RequestMapping(params = "method=friendList",method = RequestMethod.POST)
    public void friendList(HttpServletResponse response,HttpServletRequest request)throws IOException{
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
        Map<String,List<User>> fullList=friendsDAO.queryAllFriends(id);
        JSONArray array=new JSONArray();
        if(fullList.size()==0){
            myJSON.put("data",array);
            myJSON.put("status","200");
            writer.write(myJSON.toJSONString());
            writer.flush();
            return;
        }
        String[] keys= fullList.keySet().toArray(new String[0]);
        for(String key:keys){
            List<User> list=fullList.get(key);
            JSONObject group=new JSONObject();
            JSONArray friends=new JSONArray();
            for(User u:list){
                JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(u));
                friends.add(jsonObject.toJSONString());
            }
            group.put("groupName",key);
            group.put("friends",friends);
            array.add(group.toJSONString());
        }

        myJSON.put("data",array);
        myJSON.put("status","200");
        writer.write(myJSON.toJSONString());
        writer.flush();

    }
    private void sendBadRequest(MyJSON myJSON,PrintWriter writer){
        myJSON.setStatus("400");
        writer.println(myJSON.toJSONString());
        writer.flush();
    }
}