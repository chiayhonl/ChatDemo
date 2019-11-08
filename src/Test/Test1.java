package Test;

import java.net.InetAddress;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 05
 */


public class Test1 {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getByName("192.168.1.101");
        System.out.println(addr);
    }
}