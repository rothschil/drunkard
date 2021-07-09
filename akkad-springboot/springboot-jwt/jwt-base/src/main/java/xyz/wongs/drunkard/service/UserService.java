package xyz.wongs.drunkard.service;

import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.jwt.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 10:39
 * @Version 1.0.0
 */
@Component
public class UserService {
    private static final List<User> USERS = new ArrayList();

    static {
        USERS.add(new User("admin", "123"));
        USERS.add(new User("sam", "123"));
    }

    public User getUserById(String id) {
        List<User> lists = USERS.stream().filter(user -> id.equals(user.getUserName())).collect(Collectors.toList());

        return lists.get(0);
    }

    public User findByUsername(User params) {
        List<User> lists = USERS.stream().filter(user -> (user.getUserName().equals(params.getUserName()))).collect(Collectors.toList());
        if(lists.isEmpty()){
            return null;
        }
        return lists.get(0);
    }

    public boolean validateUser(User params) {
        List<User> lists = USERS.stream().filter(user -> (
                user.getUserName().equals(params.getUserName())) && user.getPassWord().equals(params.getPassWord())
        ).collect(Collectors.toList());
        return lists.isEmpty();
    }
}
