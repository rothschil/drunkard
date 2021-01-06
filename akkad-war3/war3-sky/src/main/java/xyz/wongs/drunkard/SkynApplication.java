package xyz.wongs.drunkard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import xyz.wongs.drunkard.base.persistence.jpa.repository.factory.BaseRepositoryFactoryBean;

/**
 * @ClassName DustAreaApplication
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:03
 * @Version 1.0.0
*/
@EnableJpaAuditing
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"xyz.wongs.drunkard"},
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
public class SkynApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkynApplication.class,args);
    }

}

