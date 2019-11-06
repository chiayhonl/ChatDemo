package Test;

import Chat2.DataBase.Service.DatabaseConnectionService;

import java.sql.*;
/**
 * @author Chiayhon
 * @create 2019 - 11 - 05
 */


public class Test1 {
    public static void main(String[] args) throws Exception {
//        // 1.加载数据访问驱动
//        Class.forName("com.mysql.jdbc.Driver");
//        //2.连接到数据"库"上去
//        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=true","root", "ljr0123...");
        //3.构建SQL命令
        DatabaseConnectionService connection = new DatabaseConnectionService();

        String sql ="insert into chat values('9','hello' , '1234' , '1234')";
        PreparedStatement state = connection.getConnection().prepareStatement(sql);

        state.executeUpdate();
    }
}