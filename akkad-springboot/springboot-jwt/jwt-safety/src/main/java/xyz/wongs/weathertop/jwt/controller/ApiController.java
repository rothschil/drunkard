package xyz.wongs.weathertop.jwt.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.weathertop.jwt.annotation.LoginToken;
import xyz.wongs.weathertop.jwt.pojo.User;
import xyz.wongs.weathertop.jwt.service.JwtService;
import xyz.wongs.weathertop.jwt.service.UserService;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/7 - 19:19
 * @Version 1.0.0
 */
@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @ResponseResult
    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonObject=new JSONObject();
        User userForBase=userService.findByUsername(user);
        if(userForBase==null){
            jsonObject.put("message","登录失败,用户不存在");
        }else {
            if(!userForBase.getName().equals(user.getName())){
                jsonObject.put("message","登录失败,密码错误");
            }else {
                String token = jwtService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
            }
        }
        return jsonObject;
    }

    @ResponseResult
    @LoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
