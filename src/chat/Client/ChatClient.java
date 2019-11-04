package chat.Client;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatClient extends Frame {

    TextField inputArea = new TextField();
    TextArea outputArea = new TextArea();

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame(){
        this.setLocation(300 , 400);
        this.setSize(500 , 600);
        add(inputArea , BorderLayout.SOUTH);
        add(outputArea , BorderLayout.NORTH);
        pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();//关闭当前窗口组件
//                System.exit(0);//关闭虚拟机
            }
        });
        this.setVisible(true);
    }
}
