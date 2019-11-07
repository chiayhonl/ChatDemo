package Chat2.Util;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */

import Chat2.Server;
import Chat2.View.MultiChatView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *  服务器端读写类线程
 *  用于服务器端读取客户端的信息，并把信息发送给所有客户端
 */
public class ServerReadAndPrintThread extends Thread{
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
                for(Socket socket: Server.list) {
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
            Server.list.remove(nowSocket);  // 线程关闭，移除相应套接字
        }
    }
}