package xyz.wongs.drunkard.oauth2.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OauthUser extends AbstractEntity<Long> {

    private Long id;

    private String uName;

    private String passWord;
}