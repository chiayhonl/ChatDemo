package Chat.Model;

/**
 *
 * 定义登陆所需属性：id、name、password，并设置相应setting与getting方法
 *
 *
 *
 * */
public class User {

    private int id;

    private String username;//用户登录注册的姓名

    private String password;//用户密码

    private long salt;//加密字段

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalt() {
        return salt;
    }

    public void setSalt(long salt) {
        this.salt = salt;
    }
}
