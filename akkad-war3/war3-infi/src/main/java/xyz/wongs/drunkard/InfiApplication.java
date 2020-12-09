package xyz.wongs.drunkard;

import com.github.hiwepy.ip2region.spring.boot.EnableIP2region;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @ClassName InfiApplication
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:39
 * @Version 1.0.0
*/
@ServletComponentScan
@EnableIP2region
@EnableAsync
@MapperScan(basePackages = {"xyz.wongs.drunkard.**.mapper"})
@ComponentScan(basePackages = {"com.github.hiwepy","xyz.wongs.drunkard"})
@SpringBootApplication
public class InfiApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiApplication.class,args);
    }

}
