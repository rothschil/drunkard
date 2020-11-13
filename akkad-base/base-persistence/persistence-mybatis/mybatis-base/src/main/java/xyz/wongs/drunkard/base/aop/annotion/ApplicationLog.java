package xyz.wongs.drunkard.base.aop.annotion;

import java.lang.annotation.*;

/**
 * @ClassName ApplicationLog
 * @Description 应用全局日志APO
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/10/29 16:50
 * @Version 1.0.0
*/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ApplicationLog {

    /**
     * 业务操作名称,例如:"修改用户、修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:用户实体的唯一标识为"id"
     */
    String key() default "id";

}
