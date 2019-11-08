package Chat2.Controller;

import Chat2.Model.User;
import Chat2.Service.UserService;
import Chat2.Service.UserServiceImpl;

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
         * @param res
         *
         * 状态码：-3:未知错误 -2:用户名或密码为空 -1：用户名或密码错误  0：用户名已存在  1：登陆成功
         */
        int  res = -3;//默认登录失败

        if(userName.equals("") || userPwd.equals("")){
            return -2;
        }

        switch (status){
            case 0: res = userService.Login(user)? 1 : -1;break;
            case 1: res = userService.register(user)? 1 : 0;break;
        }
        return res;
    }
}
