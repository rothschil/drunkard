package xyz.wongs.drunkard.oauth2.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthencationSuccessListener
 * @Description 用户登录成功监听器事件
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 17:01
 * @Version 1.0.0
*/
@Slf4j
@Component
public class AuthencationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        //用户通过输入用户名和密码登录成功
        log.error("---AuthenticationSuccessEvent---");
    }

}
