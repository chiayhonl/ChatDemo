package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatServer {



    public static void main(String[] args) {

        boolean started = false;


        try {
            ServerSocket server = new ServerSocket(8888);
            started = true;

            //监听控制
            while (started) {
                boolean connected = false;

                Socket socket = server.accept();
System.out.println("一个客户端连接成功");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                connected = true;

                //数据读取控制
                while (connected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }

                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
