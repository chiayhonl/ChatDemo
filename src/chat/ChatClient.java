package chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatClient extends Frame {

    Socket socket = null;

    TextField inputText = new TextField();

    TextArea outputText = new TextArea();

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame(){
        setLocation(300 , 400);
        setSize(500 , 600);
        add(inputText , BorderLayout.SOUTH);
        add(outputText , BorderLayout.NORTH);
        pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();//关闭当前窗口组件
//                System.exit(0);//关闭虚拟机
            }
        });
        inputText.addActionListener(new TextFieldAdapter());
        this.setVisible(true);
        this.connect();
    }

    private class TextFieldAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = inputText.getText();
            outputText.setText(str);
            inputText.setText("");

            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(str);
                dos.flush();
                dos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }

    public void connect(){
        try {
            socket = new Socket("192.168.1.185" , 8888);
System.out.println("已连接服务器");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
