package xyz.wongs.drunkard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan(basePackages = {"xyz.wongs.drunkard.**.mapper"})
@SpringBootApplication
public class KoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(KoAuthApplication.class,args);
    }
}
