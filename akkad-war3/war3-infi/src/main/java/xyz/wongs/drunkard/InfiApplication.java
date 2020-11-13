package xyz.wongs.drunkard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @ClassName InfiApplication
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:39
 * @Version 1.0.0
*/
@EnableAsync
@EnableCaching
@SpringBootApplication
public class InfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiApplication.class,args);
    }

}
