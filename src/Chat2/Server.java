// Server.java
package Chat2;

import Chat2.Util.ServerFileThread;
import Chat2.Util.ServerReadAndPrintThread;
import Chat2.View.MultiChatView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{

    static ServerSocket server = null;

    static Socket socket = null;

    public static List<Socket> list = new ArrayList<>();  // 存储客户端

    public static void main(String[] args) {
        MultiChatView multiChat = new MultiChatView();  // 新建聊天系统界面
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
}


