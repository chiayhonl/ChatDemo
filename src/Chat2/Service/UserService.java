package Chat2.Service;

import Chat2.Model.User;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public interface UserService {

    boolean Login(User user);

    boolean register(User user);

    boolean queryResult(User user);
}
