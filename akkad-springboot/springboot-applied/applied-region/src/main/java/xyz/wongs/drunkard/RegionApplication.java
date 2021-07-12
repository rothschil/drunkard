package xyz.wongs.drunkard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @author WCNGS@QQ.COM
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date {DATE} {TIME}
 * @Version 1.0.0
 **/
@ComponentScan(basePackages = {"org.lionsoul","xyz.wongs"})
@SpringBootApplication
public class RegionApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegionApplication.class,args);
    }

}
