package diary.action;

import diary.util.ChatSignature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sunine on 2017/6/3.
 */
@Controller

public class ChatController {
    @RequestMapping(value="/chat")
    public void chat(HttpServletRequest request,HttpServletResponse response) throws IOException {
        PrintWriter writer=response.getWriter();
        writer.write(ChatSignature.chatParams().toJSONString());
        writer.flush();
    }
}
