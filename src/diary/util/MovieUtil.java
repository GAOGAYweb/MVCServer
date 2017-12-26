package diary.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by MSI on 2017/12/26.
 */
public class MovieUtil {
    public static void main(String[] args){
        movieList("hot",0,10);
        //movieDetail(1170264);
    }
    public static String movieList(String type,int offset,int limit) {
        try {
            String strURL="http://m.maoyan.com/movie/list.json?";
            strURL+="type="+type+"&offset="+offset+"&limit="+limit;
            System.out.println(strURL);
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
//            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
//            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();

            int code = connection.getResponseCode();
            System.out.println(code);
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();

            } else {
                is = connection.getErrorStream();
            }
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String temp=null;
            String result="";
            while((temp=br.readLine())!=null){
                System.out.println(temp);
                result+=temp;
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
    public static String movieDetail(int id){
        try {
            String strURL="http://m.maoyan.com/movie/";
            strURL+=id+".json";
            System.out.println(strURL);
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("GET"); // 设置请求方式
            connection.setRequestProperty("User-agent", "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");
//            connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
//            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();

            int code = connection.getResponseCode();
            System.out.println(code);
            InputStream is = null;
            if (code == 200) {
                is = connection.getInputStream();

            } else {
                is = connection.getErrorStream();
            }
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));
            String temp=null;
            String result="";
            while((temp=br.readLine())!=null){
                System.out.println(temp);
                result+=temp;
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
