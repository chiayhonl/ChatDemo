package chat;

import java.io.DataInputStream;
import java.io.EOFException;
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

        ServerSocket server = null;

        Socket socket = null;

        DataInputStream dis = null;

        try {
            server = new ServerSocket(8888);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            started = true;

            //监听控制
            while (started) {
                boolean connected = false;

                socket = server.accept();
System.out.println("一个客户端连接成功");
                 dis = new DataInputStream(socket.getInputStream());
                connected = true;

                //数据读取控制
                while (connected) {
                    String str = dis.readUTF();
                    System.out.println(str);
                }
            }
        } catch (EOFException e1){
            //此异常不处理
        } catch (Exception e) {
System.out.println("传输出现异常");
            e.printStackTrace();
        } finally {
            try {
                if(dis != null )    dis.close();//未被初始化则无需关闭
                if(socket != null )    socket.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
System.out.println("客户端已退出");
        }
    }
}
