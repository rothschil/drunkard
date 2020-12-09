package xyz.wongs.drunkard.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author WCNGS@QQ.COM
 * @ClassName DataScope
 * @Description 数据权限过滤注解
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/9 10:30
 * @Version 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
