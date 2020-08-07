package xyz.wongs.drunkard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * @ClassName War3AreaApplication
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:39
 * @Version 1.0.0
*/
@EnableCaching
@SpringBootApplication
public class War3AreaApplication {

    public static void main(String[] args) {
        SpringApplication.run(War3AreaApplication.class,args);
    }

}
