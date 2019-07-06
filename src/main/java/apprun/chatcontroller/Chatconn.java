package apprun.chatcontroller;


import chatservice.ChatRobotInit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

@Controller
@EnableAutoConfiguration
public class Chatconn {


    public static final  String API_URL = "http://openapi.tuling123.com/openapi/api/v2";


    @RequestMapping("/chat")
    public @ResponseBody String run(){


        //用户发送的消息
        String resMes ="迷迷糊糊的童年";
        String resStr = new ChatRobotInit().getReqMes(resMes);
        String respStr = new ChatRobotInit().tulingPost(API_URL,resStr);
        String resultText = new ChatRobotInit().getResultMes(respStr);


        //机器人返回的消息
        return resultText;

    }


    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(8899);
    }



}
