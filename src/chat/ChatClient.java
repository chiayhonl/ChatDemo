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

    DataOutputStream dos = null;

    TextField inputText = new TextField();

    TextArea outputText = new TextArea();

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    //窗口初始化
    public void launchFrame(){
        setLocation(300 , 400);
        setSize(500 , 600);

        add(inputText , BorderLayout.SOUTH);
        add(outputText , BorderLayout.NORTH);
        pack();

        //窗口监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                disConnect();
System.out.println("连接已断开");
//                dispose();//关闭当前窗口组件
                System.exit(0);//关闭虚拟机
            }
        });

        //TextField监听器
        inputText.addActionListener(new TextFieldAdapter());

        this.setVisible(true);
        this.connect();
    }

    //连接服务器
    public void connect(){
        try {
            socket = new Socket("192.168.1.185" , 8888);
            dos = new DataOutputStream(socket.getOutputStream());
System.out.println("已连接服务器");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disConnect(){
        try {
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //TextField监听器
    private class TextFieldAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = inputText.getText().trim();
            outputText.setText(str);
            inputText.setText("");

            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }


}
