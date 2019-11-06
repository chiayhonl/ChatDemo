package Chat2.DataBase;

import java.sql.*;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public class Database {

    /**
     * 用以实现用户的注册和登录
     */
    private  String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false";

    private  String user = "root";//

    private  String pass = "123456";//mysql登录密码

    private  Connection con;

    private PreparedStatement preparedStatement = null;

    public static void main(String[] args) {
        new Database().verification("aaa", "aaa" , 1);
    }

    public boolean verification(String userName, String userPwd, int status){
        boolean res = false;
        //加载数据库连接驱动并连接
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);

            switch (status){
                case 0: res = login(userName , userPwd);break;
                case 1: res = register(userName , userPwd);break;
                default: return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return res;
    }

    //用户注册
   public boolean register(String userName, String userPwd){


        return false;
    }

    //用户登录
    public boolean login(String userName, String userPwd) {

        try {
            String sql ="insert into chat(name) values(?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            int res = preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String sql = "select username,password from user_info where username=? and password=?";
//        PreparedStatement ptmt = con.prepareStatement(sql);
//        ptmt.setString(1, username);
//        ptmt.setString(2, password);
//        ResultSet rs = ptmt.executeQuery();
//        //从登录用户给出的账号密码来检测查询在数据库表中是否存在相同的账号密码
//        if (rs.next()) {
//            System.out.println("登录成功！");
//        } else {
//            System.out.println("姓名或密码错误！\n请重新登录：");
//            denglu(userName, userPwd);
//        }

        return false;
    }


}
