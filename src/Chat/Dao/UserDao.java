package Chat.Dao;

import Chat.Model.User;

import java.sql.ResultSet;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public interface UserDao {

    //插入用户消息
    void addUser(User user);

    //查询用户名
    ResultSet findUser(String userName);

    //密码匹配
    boolean matchPassword(User user);
}
