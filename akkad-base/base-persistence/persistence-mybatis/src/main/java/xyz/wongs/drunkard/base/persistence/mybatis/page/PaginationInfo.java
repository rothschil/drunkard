package xyz.wongs.drunkard.base.persistence.mybatis.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:48
 * @Version 1.0.0
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix = "page")
public class PaginationInfo {

    private int pageNum;

    private int pageSize;

}
