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
public class DictRegion extends BaseEntityAbstract<Long> {
    private Long id;

    private String regionName;

    private String regionCode;

    private String upRegionCode;

    private String status;
}