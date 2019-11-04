package chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatServer {

    public static void main(String[] args) {
        new ChatServer().startHandle();
    }

    public void startHandle(){
        boolean started = false;

        ServerSocket server = null;


        try {
            server = new ServerSocket(8888);
            started = true;
        } catch (BindException e1){
            System.out.println("端口已被使用,打开服务器失败");
            System.exit(0);
        } catch (Exception e2) {
            System.out.println("绑定端口时出现异常");
            e2.printStackTrace();
        }

        try{
            //监听控制
            while (started){
                Socket socket = server.accept();
                System.out.println("一个客户端连接成功");

                ClientThread client = new ClientThread(socket);
                new Thread(client).start();
            }
        } catch (Exception e) {
            System.out.println("接受客户端Socket时出现异常");
            e.printStackTrace();
        } finally {
            try {
                server.close();
                System.out.println("服务端已关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ClientThread implements Runnable {
        Socket socket = null;

        DataInputStream dis = null;

        boolean connected = false;

        public ClientThread(Socket socket) {
            try {
                this.socket = socket;
                dis = new DataInputStream(socket.getInputStream());
                connected = true;
            } catch (Exception e) {
                System.out.println("客户端线程初始化失败");
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            String str = null;

            try {
                while (connected){
                    str = dis.readUTF();
                    System.out.println(str);
                }
            } catch (EOFException e1) {
                System.out.println("客户端已退出");
            } catch (Exception e2) {
                System.out.println("客户端线程运行时出现异常");
                e2.printStackTrace();
            } finally {
                try {
                    if(dis != null )    dis.close();//未被初始化则无需关闭
                    if(socket != null )    socket.close();
                }catch (IOException e){
                    System.out.println("客户端线程关闭时出现异常");
                    e.printStackTrace();
                }
            }
        }
    }
}
