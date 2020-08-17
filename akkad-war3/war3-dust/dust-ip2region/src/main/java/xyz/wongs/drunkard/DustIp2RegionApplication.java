package xyz.wongs.drunkard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;


/** 自动将IP解析成国家，地区
 * @ClassName DustIp2RegionApplication
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:39
 * @Version 1.0.0
*/
@EnableAsync
@EnableCaching
@ComponentScan(basePackages = {"org.lionsoul","xyz.wongs.drunkard"})
@MapperScan(basePackages = {"xyz.wongs.drunkard.**.mapper"})
@SpringBootApplication
public class DustIp2RegionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DustIp2RegionApplication.class,args);
    }

}
