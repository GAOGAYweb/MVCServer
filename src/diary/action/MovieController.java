package diary.action;

import com.alibaba.fastjson.JSONObject;
import diary.util.MovieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by MSI on 2017/12/26.
 */
@Controller
@RequestMapping(value="/movie")
public class MovieController {
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
}
