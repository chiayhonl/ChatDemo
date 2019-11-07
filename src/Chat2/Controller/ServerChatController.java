package Chat2.Controller;

import Chat2.View.MultiChatView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public class ServerChatController {

    static ServerSocket server = null;

    static Socket socket = null;

    MultiChatView multiChat = null;

    public static List<Socket> list = new ArrayList<>();  // 存储客户端

    public ServerChatController(MultiChatView multiChat) {
        this.multiChat = multiChat;
    }

    public void monitor(){
        try {
            // 在服务器端对客户端开启文件传输的线程
            ServerFileThread serverFileThread = new ServerFileThread();
            serverFileThread.start();
            server = new ServerSocket(8081);  // 服务器端套接字（只能建立一次）
            // 等待连接并开启相应线程
            while (true) {
                socket = server.accept();  // 等待连接
                list.add(socket);  // 添加当前客户端到列表
                // 在服务器端对客户端开启相应的线程
                ServerReadAndPrintThread readAndPrint = new ServerReadAndPrintThread(socket, multiChat);
                readAndPrint.start();
            }
        } catch (IOException e1) {
            e1.printStackTrace();  // 出现异常则打印出异常的位置
        }
    }

    /**
     *  服务器端读写类线程
     *  用于服务器端读取客户端的信息，并把信息发送给所有客户端
     */
    class ServerReadAndPrintThread extends Thread{
        Socket nowSocket = null;
        MultiChatView multiChat = null;
        BufferedReader in =null;
        PrintWriter out = null;
        // 构造函数
        public ServerReadAndPrintThread(Socket s, MultiChatView multiChat) {
            this.multiChat = multiChat;  // 获取多人聊天系统界面
            this.nowSocket = s;  // 获取当前客户端
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(nowSocket.getInputStream()));  // 输入流
                // 获取客户端信息并把信息发送给所有客户端
                while (true) {
                    String str = in.readLine();
                    // 发送给所有客户端
                    for(Socket socket: ServerChatController.list) {
                        out = new PrintWriter(socket.getOutputStream());  // 对每个客户端新建相应的socket套接字
                        if(socket == nowSocket) {  // 发送给当前客户端
                            out.println("(你)" + str);
                        }
                        else {  // 发送给其它客户端
                            out.println(str);
                        }
                        out.flush();  // 清空out中的缓存
                    }
                    // 调用自定义函数输出到图形界面
                    multiChat.setTextArea(str);
                }
            } catch (Exception e) {
                ServerChatController.list.remove(nowSocket);  // 线程关闭，移除相应套接字
            }
        }
    }
}
