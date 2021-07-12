package xyz.wongs.drunkard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName DustAreaApplication
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:03
 * @Version 1.0.0
*/
//@EnableJpaAuditing
//@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"xyz.wongs.drunkard"},
//        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//Specify your own factory class
//)
@MapperScan(basePackages = {"xyz.wongs.drunkard.**.mapper"})
@SpringBootApplication
public class MoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class,args);
    }

}

