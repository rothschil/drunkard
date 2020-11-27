package xyz.wongs.drunkard.oauth2.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: Description
 * @ProjectName: spring-parent
 * @Version: 1.0
 */
@RestController
public class ExceptionController {
    @RequestMapping("/oauth/error")
    public void error(HttpServletRequest request, HttpServletResponse response){
        System.out.println("------------------");
    }
}
