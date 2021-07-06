package xyz.wongs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date
 * @Version 1.0.0
 */
@RestController
@SpringBootApplication
public class JwtSafetyApp {
    public static void main(String[] args) {
        SpringApplication.run(JwtSafetyApp.class, args);
    }
}
