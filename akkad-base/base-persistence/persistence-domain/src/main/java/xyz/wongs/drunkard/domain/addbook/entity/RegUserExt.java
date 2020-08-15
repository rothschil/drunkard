package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

import java.util.Date;

/**
 * @ClassName RegUserExt
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/15 23:49
 * @Version 1.0.0
*/
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