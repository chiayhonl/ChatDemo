package Chat2.Dao;

import Chat2.Model.User;

import java.sql.ResultSet;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public interface UserDao {

    //注册：增加用户
    void addUser(User user);

    //用户存在状态查询
    ResultSet findUser(String userName);

    //密码验证
    boolean matchPassword(User user);
}
