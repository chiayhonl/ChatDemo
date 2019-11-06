package Chat2.DataBase.Dao;

import Chat2.DataBase.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 06
 */
public class UserDaoImpl implements UserDao {

    Connection connection = null;

    PreparedStatement preparedStatement = null;



    public UserDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public boolean findUser(String userName) {
        return false;
    }

    @Override
    public boolean matchPassword(String password) {
        return false;
    }
}
