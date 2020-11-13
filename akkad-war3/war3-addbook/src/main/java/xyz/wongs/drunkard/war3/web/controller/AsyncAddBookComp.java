package xyz.wongs.drunkard.war3.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.domain.addbook.entity.RegUserExt;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;
import xyz.wongs.drunkard.domain.addbook.service.RegUserExtService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/** 异步写入个人地址簿的扩展信息
 * @ClassName AsyncAddBookComp
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:07
 * @Version 1.0.0
*/
@Component
public class AsyncAddBookComp {

    @Autowired
    private RegUserExtService regUserExtService;

    /** 异步方法,初始化
     * @Description
     * @param registerUser
     * @return void
     * @throws
     * @date 2020/9/9 15:07
     */
    @Async
    public void inrecord(RegisterUser registerUser){
        RegUserExt regUserExt = RegUserExt.builder().registerDate(new Date()).build();
        regUserExt.setRegisterUserId(registerUser.getId());
        regUserExtService.insert(regUserExt);
    }
}
