package diary.util;

import diary.bean.Moments;
import diary.dao.MomentsDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sunine on 2017/6/7.
 */
public class TagCompute {
    public static void main(String[] args) throws IOException {

//        String[] fils={"web/WEB-INF/applicationContext.xml","web/WEB-INF/dispatcher-servlet.xml","web/WEB-INF/hib-config.xml"};
//        ApplicationContext ac= new FileSystemXmlApplicationContext(fils);
//        MomentsDAO momentsDAO= (MomentsDAO) ac.getBean("MomentsDAO");
//
//        for(Moments m:list){
//            List<String> data= Arrays.asList(m.getContent());
//            System.out.println(NPLUtil.keywords(data,0.1));
//        }
    }
}
