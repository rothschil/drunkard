package xyz.wongs.weathertop.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 忽略Token验证注解
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/6 - 10:16
 * @Version 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreTokenCheck {
    /**
     * 默认 为 true，需要检查
     * @return
     */
    boolean required() default true;
}
