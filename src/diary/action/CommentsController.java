package diary.action;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sunine on 2017/6/4.
 */
@Controller
@RequestMapping("/comments")
public class CommentsController {
    @RequestMapping(params = "method=add",method = RequestMethod.POST)
    public void add(HttpServletRequest request, HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        String content=request.getParameter("nickname");
        String longitude=request.getParameter("content");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
    }
    @RequestMapping(params = "method=query",method = RequestMethod.POST)
    public void query(HttpServletRequest request,HttpServletResponse response)throws  IOException{
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        JSONObject myJSON = new JSONObject();
        PrintWriter writer = response.getWriter();
    }
}
