package Chat2.DataBase.Dao;

import Chat2.DataBase.Model.User;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public interface UserDao {

    //注册：增加用户
    void addUser(User user);

    //用户存在状态查询
    boolean findUser(String userName);

    //密码验证
    boolean matchPassword(String password);
}
