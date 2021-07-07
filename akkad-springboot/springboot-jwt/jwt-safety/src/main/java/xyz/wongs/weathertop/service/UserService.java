package xyz.wongs.weathertop.service;

import org.springframework.stereotype.Component;
import xyz.wongs.weathertop.jwt.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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
    private static List<User> users = new ArrayList<User>();

    static {
        users.add(new User("1", "A"));
        users.add(new User("2", "B"));
        users.add(new User("3", "E"));
        users.add(new User("4", "F"));
        users.add(new User("5", "G"));
    }

    public User getUserById(String id) {
        List<User> lists = users.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return id.equals(user.getId());
            }
        }).collect(Collectors.toList());

        return lists.get(0);
    }

    public User findByUsername(User params) {
        List<User> lists = users.stream().filter(user -> (user.getName().equals(params.getName()))).collect(Collectors.toList());
        if(lists.isEmpty()){
            return null;
        }
        return lists.get(0);
    }
}
