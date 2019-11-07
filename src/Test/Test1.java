package Test;

import Chat2.Model.User;
import Chat2.Service.UserServiceImpl;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 05
 */


public class Test1 {
    public static void main(String[] args) throws Exception {
        UserServiceImpl userService = new UserServiceImpl();
        User user = new User();
        user.setPassword("1234");
        user.setUsername("hello");
        user.setSalt(1234);
        user.setId(9);
        userService.queryResult(user);
    }
}