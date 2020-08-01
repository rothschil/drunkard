package xyz.wongs.drunktard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunktard.base.persistence.mybatis.entity.BaseEntity;

import java.util.Date;

@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegUserExt extends BaseEntity<Long> {
    private Long id;

    private Long dictRegionId;

    private Long uId;

    private String gender;

    private Date birthday;

    private Date lastActiveDate;
}