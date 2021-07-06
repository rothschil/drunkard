package xyz.wongs.weathertop.jwt.service;

import org.springframework.stereotype.Component;
import xyz.wongs.weathertop.jwt.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 17:01
 * @Version 1.0.0
 */
@Component
public class UserService implements Serializable {
    private static List<User> users = new ArrayList<User>();

    static {
        users.add(new User(1L, "张三",23,1));
        users.add(new User(2L, "李四",17,0));
        users.add(new User(3L, "钱九",9,0));
        users.add(new User(4L, "赵十三",27,0));
    }

    public User getUser(){
        int idx = new Random().nextInt(users.size()-1);
        idx=idx<0?0:idx;
        return users.get(idx);
    }

}
