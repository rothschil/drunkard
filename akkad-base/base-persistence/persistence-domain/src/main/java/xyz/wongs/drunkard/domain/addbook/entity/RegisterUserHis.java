package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserHis extends BaseEntityAbstract<Long> {
    private Long id;
    private Long registerUserId;

    private String nickName;

    private String uniCode;

    private String mobile;

    private String email;

    private String passWord;

    private String sat;

    private String status;
}