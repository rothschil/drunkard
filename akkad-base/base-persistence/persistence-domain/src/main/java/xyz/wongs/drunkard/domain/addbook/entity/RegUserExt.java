package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

import java.util.Date;

@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserExt extends BaseEntityAbstract<Long> {
    private Long id;

    private Long dictRegionId;

    private Long registerUserId;

    private String gender;

    private Date birthday;

    private Date registerDate;

    private Date lastActiveDate;
}