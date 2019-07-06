package chatservice;


import java.util.Scanner;

public class ChatRobotInitTest {


    public static final  String API_URL = "http://openapi.tuling123.com/openapi/api/v2";




    public static void main(String[] args)  {

        System.out.println("你说 ：");
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            String resStr = new ChatRobotInit().getReqMes(in.nextLine());
            String respStr = new ChatRobotInit().tulingPost(API_URL,resStr);
            String resultText = new ChatRobotInit().getResultMes(respStr);

            System.out.println("他对你说 ： " + resultText);

        }
    }

}
