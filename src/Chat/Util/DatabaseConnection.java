package Chat.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public class DatabaseConnection {

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    private  static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false";

    private  static final String DATABASE_USER = "root";

    private  static final String DATABASE_PASSWORD = "123456";

    private Connection connection = null;

    //驱动注册
    public DatabaseConnection(){
        try {
            Class.forName(DATABASE_DRIVER);//注册驱动
            this.connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);//实例化驱动，取得连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public Connection getConnection(){
        return this.connection;
    }

    //断开连接
    public void closed(){
        if(this.connection != null) {
            try {
                this.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
