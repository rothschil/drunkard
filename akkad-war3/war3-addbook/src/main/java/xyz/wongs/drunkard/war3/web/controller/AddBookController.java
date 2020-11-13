package xyz.wongs.drunkard.war3.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.web.BaseController;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;
import xyz.wongs.drunkard.domain.addbook.service.RegisterUserService;

import java.util.List;

/**
 * @ClassName AddBookController
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
*/
@Slf4j
@RestController
@RequestMapping(value = "/addBook")
@ResponseResult
public class AddBookController extends BaseController {

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private AsyncAddBookComp asyncAddBookComp;


    /**
     * @Description
     * @param regUserVo
     * @return void
     * @throws
     * @date 2020/8/4 19:14
     */
    @PostMapping("/register")
    public RegisterUser register(@RequestBody RegisterUser registerUser){
        List<RegisterUser> regUsers = null;
        regUsers = registerUserService.selectByRegUser(registerUser);
        if(regUsers.isEmpty()){
            Long id = registerUserService.save(registerUser);
            registerUser.setId(id);
            asyncAddBookComp.inrecord(registerUser);
        }
        return registerUser;
    }
}

