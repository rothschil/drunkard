package xyz.wongs.drunkard.war3.web.addbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.domain.addbook.entity.RegUserExt;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;
import xyz.wongs.drunkard.domain.addbook.service.RegUserExtService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class AsyncAddBookComp {

    @Autowired
    private RegUserExtService regUserExtService;

    @Async
    public void inrecord(RegisterUser registerUser){
        RegUserExt regUserExt = RegUserExt.builder().registerDate(new Date()).build();
        regUserExt.setRegisterUserId(registerUser.getId());
        try {
            TimeUnit.SECONDS.sleep(12L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        regUserExtService.insert(regUserExt);
    }
}
