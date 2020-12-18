package xyz.wongs.drunkard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import xyz.wongs.drunkard.base.persistence.jpa.repository.BaseRepositoryFactoryBean;

/**
 * @ClassName DustAreaApplication
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:03
 * @Version 1.0.0
*/
@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"xyz.wongs.drunkard"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class//Specify your own factory class
)
public class MoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoonApplication.class,args);
    }

}
