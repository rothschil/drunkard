package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

/**
 * @ClassName
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:48
 * @Version 1.0.0
 */
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUser extends BaseEntityAbstract<Long> {
    private Long id;

    private String status;

    private String nickName;

    private String uCode;

    private String uMobile;

    private String email;

    private String uPwd;

    private String sat;

}