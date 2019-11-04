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
        try {
            ServerSocket server = new ServerSocket(8888);
            while (true) {
                Socket socket = server.accept();
System.out.println("一个客户端连接成功");

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                System.out.println(dis.readUTF());
                dis.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
