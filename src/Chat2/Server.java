// Server.java
package Chat2;

import Chat2.Controller.ServerChatController;
import Chat2.View.ChatHistoryView;

public class Server{

    public static void main(String[] args) {
        ChatHistoryView chatHistoryView = new ChatHistoryView();  // 后台聊天系统界面

        ServerChatController serverChatController = new ServerChatController(chatHistoryView);
        serverChatController.monitor();//调用监听方法
    }

}


