package xyz.wongs.drunkard.oauth2.event.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName AuthencationFailureListener
 * @Description 用户认证失败监听器事件
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/27 17:01
 * @Version 1.0.0
*/
@Slf4j
@Component
public class AuthencationFailureListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        if(event instanceof AuthenticationFailureBadCredentialsEvent){
            //提供的凭据是错误的，用户名或者密码错误
            log.error("---AuthenticationFailureBadCredentialsEvent---");
        } else if(event instanceof AuthenticationFailureCredentialsExpiredEvent){
            //验证通过，但是密码过期
            log.error("---AuthenticationFailureCredentialsExpiredEvent---");
        } else if(event instanceof AuthenticationFailureDisabledEvent){
            //验证过了但是账户被禁用
            log.error("---AuthenticationFailureDisabledEvent---");
        } else if(event instanceof AuthenticationFailureExpiredEvent){
            //验证通过了，但是账号已经过期
            log.error("---AuthenticationFailureExpiredEvent---");
        }  else if(event instanceof AuthenticationFailureLockedEvent){
            //账户被锁定
            log.error("---AuthenticationFailureLockedEvent---");
        } else if(event instanceof AuthenticationFailureProviderNotFoundEvent){
            //配置错误，没有合适的AuthenticationProvider来处理登录验证
            log.error("---AuthenticationFailureProviderNotFoundEvent---");
        } else if(event instanceof AuthenticationFailureProxyUntrustedEvent){
            //代理不受信任，用于Oauth、CAS这类三方验证的情形，多属于配置错误
            log.error("---AuthenticationFailureProxyUntrustedEvent---");
        } else if(event instanceof AuthenticationFailureServiceExceptionEvent){
            //其他任何在AuthenticationManager中内部发生的异常都会被封装成此类
            log.error("---AuthenticationFailureServiceExceptionEvent---");
        }
    }

}
