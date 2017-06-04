package diary.action;


import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Sunine on 2017/6/3.
 */
@Controller
@MultipartConfig
@RequestMapping("/image")
public class ImageController {
    @RequestMapping(value="upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("upload");
        String name=request.getParameter("name");
        System.out.println(name);
        File folder=new File("../images");
        if(!folder.exists())folder.mkdirs();
        if(name!=null){
            File f=new File("../images/"+name+".jpg");
            file.transferTo(f);
        }

    }
    @RequestMapping(value="download")
    public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String file=request.getParameter("file");
        File pic=new File("../images/"+file);
        if(file!=null&&pic.exists()){
            FileInputStream fis=new FileInputStream(pic);
            BufferedImage image = ImageIO.read(fis);
            ImageIO.write(image,"jpg",response.getOutputStream());
            response.getOutputStream().flush();
        }else{
            PrintWriter writer=response.getWriter();
            writer.write("error!");
            writer.flush();
        }
    }
}
