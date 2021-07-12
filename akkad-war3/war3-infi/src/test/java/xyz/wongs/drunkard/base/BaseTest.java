package xyz.wongs.drunkard.base;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.lionsoul.ip2region","xyz.wongs.drunkard"})
public abstract class BaseTest {
}
