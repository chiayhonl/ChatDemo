package Chat2.Service;

import Chat2.Dao.UserDao;
import Chat2.Dao.UserDaoImpl;
import Chat2.Model.User;
import Chat2.Util.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl(new DatabaseConnection().getConnection());
    @Override
    public boolean Login(User user) {
        if(!queryResult(user))   return false;//用户名错误
        if(!userDao.matchPassword(user))  return false;//密码错误
        return true;
    }

    @Override
    public boolean register(User user) {
        if(!queryResult(user)){
            userDao.addUser(user);
        }else{
            return false;
        }
        return true;
    }

    public boolean queryResult(User user){
        ResultSet result = userDao.findUser(user.getUsername());
        try {
            while (result.next()){
                return true;	// 取出一个用户的真实姓名
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
