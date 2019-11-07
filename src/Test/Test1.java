package Test;

import java.io.File;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 05
 */


public class Test1 {
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\Administrator\\Desktop\\接收文件\\" + "a.txt" );
        file.createNewFile();
    }
}