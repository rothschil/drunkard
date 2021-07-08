package xyz.wongs.drunkard.base.message.annoation;

import java.lang.annotation.*;

/**
 * @Description 注解用于统一处理响应内容
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/10/30 21:57
 * @Version 1.0.0
*/
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseResult {
}
