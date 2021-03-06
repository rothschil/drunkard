package xyz.wongs.drunkard.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;

import java.util.ArrayList;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/9 - 15:13
 * @Version 1.0.0
 */
@RestController
public class IndexController {

    /**
     * @return ArrayList
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 路由映射到/users
     * @Date 2021/7/9-10:41
     * @Param
     **/
    @ResponseResult
    @RequestMapping(value = "/users")
    public ArrayList usersList() {
        ArrayList<String> users = new ArrayList<String>() {{
            add("freewolf");
            add("tom");
            add("jerry");
        }};
        return users;
    }

    @GetMapping("/data")
    private ArrayList data() {
        return new ArrayList<String>() {{
            add("ArrayList data");
        }};
    }

    @ResponseResult
    @RequestMapping(value = "/hello")
    public ArrayList hello() {
        return new ArrayList<String>() {{
            add("hello");
        }};
    }

    @ResponseResult
    @RequestMapping(value = "/world")
    public ArrayList world() {
        return new ArrayList<String>() {{
            add("world");
        }};
    }
}
