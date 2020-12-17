package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

/**
 * @ClassName OauthUser
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/2 16:18
 * @Version 1.0.0
*/
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OauthUser extends AbstractEntity<Long> {

    private Long id;

    private String uName;

    private String passWord;
}