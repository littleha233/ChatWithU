package chatservice;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class ChatRobotInit {

    public static final String API_KEY = "b910a60caa2c48d8a36f7a823df08ab8";
    //public static final String API_KEY ="fe6314779ad64373ae1b189562c90913";
    public static final  String API_URL = "http://openapi.tuling123.com/openapi/api/v2";
    public static final String USER_ID ="451427";


//    public ChatRobotInit(){
//    }

    public String getReqMes(String resMes){
        JSONObject reqJson= new JSONObject();
        int reqType = 0 ;
        reqJson.put("reqType",reqType);
        //输入信息
        JSONObject perception = new JSONObject();
        //输入的文本信息
        JSONObject inputText = new JSONObject();
        //用户输入部分
        inputText.put("text",resMes);
        perception.put("inputText",inputText);

        JSONObject userInfo = new JSONObject();
        userInfo.put("apiKey",API_KEY);
        userInfo.put("userId",USER_ID);
        reqJson.put("perception",perception);
        reqJson.put("userInfo",userInfo);

        return reqJson.toString();
    }


    public String tulingPost(String url,String reqMes) {

        String status = " ";
        String responseStr = " ";
        PrintWriter out = null ;
        BufferedReader in = null ;
        try{
            URL realUrl = new URL(url);
            URLConnection connection =realUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("x-adviewrtb-version", "2.1");

            // 发送POST请求
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            out = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            out.write(reqMes);
            out.flush();
            httpURLConnection.connect();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                responseStr += line;
            }
            status = new Integer(httpURLConnection.getResponseCode()).toString();
//            System.out.println("status"+status);

        } catch (java.net.MalformedURLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return responseStr;
    }

    public String getResultMes(String tulinPostStr){

        JSONObject thesultStr = JSONObject.fromObject(tulinPostStr);
        List<Object> results = (List<Object>) thesultStr.get("results");
        JSONObject resultObj = JSONObject.fromObject(results.get(0));
        JSONObject values = JSONObject.fromObject(resultObj.get("values"));
        return values.get("text").toString();
    }
}
