package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatServer {

    private List<ClientThread> clientThreadGroup = new ArrayList<>();

    public static void main(String[] args) {
        new ChatServer().startHandle();
    }

    public void startHandle(){

        ServerSocket server = null;

        boolean started = false;

        //绑定端口
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

        //监听控制
        try{
            while (started){
                Socket socket = server.accept();
                System.out.println("一个客户端连接成功");

                ClientThread client = new ClientThread(socket);
                new Thread(client).start();
                clientThreadGroup.add(client);
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

        private Socket socket = null;

        private DataInputStream dis = null;

        private DataOutputStream dos = null;

        private boolean connected = false;

        public ClientThread(Socket socket) {
            try {
                this.socket = socket;
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                connected = true;
            } catch (Exception e) {
                System.out.println("客户端线程初始化失败");
                e.printStackTrace();
            }
        }

        public void sendMessage(String str){
            try {
                dos.writeUTF(str);
            } catch (IOException e) {
                System.out.println("发送消息失败");
                e.printStackTrace();
            }
        }
        //数据传输控制
        @Override
        public void run() {
            String str = null;

            try {
                while (connected){
                    str = dis.readUTF();
System.out.println(str);
                    for(int i = 0 ; i < clientThreadGroup.size(); i++){
                        ClientThread client = clientThreadGroup.get(i);
                        client.sendMessage(str);
                    }
                }
            } catch (EOFException e1) {
                System.out.println("客户端已退出");
            } catch (Exception e2) {
                System.out.println("客户端线程运行时出现异常");
                e2.printStackTrace();
            } finally {
                try {
                    if(dis != null )    dis.close();//仍处于初始状态则无需关闭
                    if(dos != null )    dos.close();
                    if(socket != null )    socket.close();
                }catch (IOException e){
                    System.out.println("客户端线程关闭时出现异常");
                    e.printStackTrace();
                }
            }
        }

    }
}
