package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

/**
 * @ClassName RegisterUser
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/15 22:01
 * @Version 1.0.0
*/
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser extends BaseEntityAbstract<Long> {

    private Long id;

    private String nickName;

    private String uniCode;

    private String mobile;

    private String email;

    private String passWord;

    private String sat;

    private String status;
}