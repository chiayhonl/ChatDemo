package Chat2.Controller;

import Chat2.Model.User;
import Chat2.Service.UserService;
import Chat2.Service.UserServiceImpl;
import static Chat2.Util.StatusCode.*;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public class LoginController {
    public int verification(String userName , String userPwd , int status){
        UserService userService = new UserServiceImpl();

        User user = new User();
        user.setId((int) System.currentTimeMillis());
        user.setUsername(userName);
        user.setPassword(userPwd);
        user.setSalt(System.currentTimeMillis() % 100);

        /**
         * @param 状态码：
         * -4:未知错误 -3:用户名或密码为空  -2:用户名或密码错误 -1：用户名不存在  0：用户名已存在  1：登陆成功
         */

        if(userName.equals("") || userPwd.equals("")){
            return NAME_OR_PASSWORD_NULL;
        }

        switch (status){
            case 0: return userService.Login(user);
            case 1: return userService.register(user);
        }
        return NAME_OR_PASSWORD_NULL;
    }
}
