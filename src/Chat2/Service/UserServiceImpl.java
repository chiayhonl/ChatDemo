package Chat2.Service;

import Chat2.Dao.UserDao;
import Chat2.Dao.UserDaoImpl;
import Chat2.Model.User;
import Chat2.Util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Chat2.Util.StatusCode.*;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl(new DatabaseConnection().getConnection());
    @Override
    public int Login(User user) {
        if(queryResult(user) == USER_NOT_EXIST)   return USER_NOT_EXIST;//用户名不存在
        if(!userDao.matchPassword(user))  return NAME_OR_PASSWORD_ERROR;//密码错误
        return SUCCESS;
    }

    @Override
    public int register(User user) {
        if(queryResult(user) == USER_NOT_EXIST){
            userDao.addUser(user);
        }else{
            return USER_EXIST;
        }
        return SUCCESS;
    }

    public int queryResult(User user){
        ResultSet result = userDao.findUser(user.getUsername());
        try {
            while (result.next()){
                return USER_EXIST;	// 取出一个用户的真实姓名
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return USER_NOT_EXIST;
    }

}
