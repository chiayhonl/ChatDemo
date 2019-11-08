package Chat.Dao;

import Chat.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        try {
            String sql = "insert into chat values ( ?, ?, ?, ? )";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(user.getId()));
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword() + user.getSalt());
            preparedStatement.setString(4, String.valueOf(user.getSalt()));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet findUser(String userName) {
        ResultSet resultSet = null;
        try {
            String sql = "select name from chat where name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public boolean matchPassword(User user) {
        boolean result = false;
        ResultSet resultSet = null;
        try {
            String sql = "select name , password , salt from chat where name = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                if(name.equals(user.getUsername()) && password.equals(user.getPassword() + resultSet.getString("salt"))){
                    result = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
