package chat.Client;

import java.awt.*;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatClient extends Frame {
    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame(){
        this.setLocation(300 , 400);
        this.setSize(500 , 600);
        this.setVisible(true);
    }
}
