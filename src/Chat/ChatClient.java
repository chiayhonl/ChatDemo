package Chat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 04
 */
public class ChatClient extends Frame {

    private Socket socket = null;

    private DataOutputStream dos = null;

    private DataInputStream dis = null;

    private TextField inputText = new TextField();

    private TextArea outputText = new TextArea();

    private boolean isReceive = false;

    private Thread messageReceiver = new Thread(new MessageReceiver());

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    //窗口主体
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

        //窗口组件可见性
        this.setVisible(true);

        //连接服务器
        this.connect();

        //开启消息接受线程
        messageReceiver.start();
    }

    //连接与断开
    public void connect(){
        try {
            socket = new Socket("192.168.1.185" , 8888);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            isReceive = true;
System.out.println("已连接服务器");
        } catch (IOException e) {
            System.out.println("服务器连接失败");
            e.printStackTrace();
        }
    }

    public void disConnect(){
        try {
            //简单处理，使服务端有时间响应但客户端会报错，此处可添加一个监听器对象来执行退出
            isReceive = false;
            messageReceiver.join(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                dos.close();
                dis.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //TextField监听器
    private class TextFieldAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String str = inputText.getText().trim();
//            outputText.setText(str);
            inputText.setText("");

            //输出数据
            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }

    //消息接受内部类
    private class MessageReceiver implements Runnable{

        @Override
        public void run() {
            try {
                while (isReceive){
                    String message = dis.readUTF();
System.out.println(message);
                    outputText.setText(outputText.getText() + message + '\n');
                }
            } catch (IOException e) {
System.out.println("接受消息时出错");
                e.printStackTrace();
            }
        }
    }

}
