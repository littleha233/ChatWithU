package chatserviceImpl;

import chatservice.ChatRobotInit;

import java.io.IOException;
import java.util.Scanner;

public class ChatRobotImpl {


    public static final  String API_URL = "http://openapi.tuling123.com/openapi/api/v2";


    public static void main(String[] args)  {

        System.out.println("你 ：");
        Scanner in = new Scanner(System.in);

       while(in.hasNext()){
           String resStr = new ChatRobotInit().getReqMes(in.nextLine());
           String respStr = new ChatRobotInit().tulingPost(API_URL,resStr);
           String resultText = new ChatRobotInit().getResultMes(respStr);

          System.out.println("robot ： " + resultText);

       }
    }
}
