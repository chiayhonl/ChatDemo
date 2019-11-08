package Chat2.Service;

import Chat2.Model.User;

/**
 * @author Chiayhon
 * @create 2019 - 11 - 07
 */
public interface UserService {

    int Login(User user);

    int register(User user);

    int queryResult(User user);
}
