// Server.java
package Chat2;

import Chat2.Controller.ServerChatController;
import Chat2.View.MultiChatView;

public class Server{

    public static void main(String[] args) {
        MultiChatView multiChat = new MultiChatView();  // 后台聊天系统界面

        ServerChatController serverChatController = new ServerChatController(multiChat);
        serverChatController.monitor();//调用监听方法
    }

}


