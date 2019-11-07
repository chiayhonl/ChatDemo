package Chat2.Controller;

import Chat2.Model.User;
import Chat2.Service.UserService;
import Chat2.Service.UserServiceImpl;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public class LoginController {
    public boolean verification(String userName , String userPwd , int status){
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setId((int) System.currentTimeMillis());
        user.setUsername(userName);
        user.setPassword(userPwd);
        user.setSalt(System.currentTimeMillis() % 100);

        boolean res = false;
        switch (status){
            case 0: res = userService.Login(user);break;
            case 1: res = userService.register(user);break;
            default: return false;
        }

        return res;
    }
}
