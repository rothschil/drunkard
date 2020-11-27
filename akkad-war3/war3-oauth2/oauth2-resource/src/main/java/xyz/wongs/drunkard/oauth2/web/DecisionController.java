package xyz.wongs.drunkard.oauth2.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decision")
public class DecisionController {

    @RequestMapping("/userinfo")
    public String hello(){
        return "userinfo" ;
    }
}
