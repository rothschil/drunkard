package xyz.wongs.drunkard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class DistLockZookeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistLockZookeeperApplication.class,args);
    }


}
