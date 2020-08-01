package xyz.wongs.drunktard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunktard.base.persistence.mybatis.entity.BaseEntity;

@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrpAddbook extends BaseEntity<Long> {
    private Long id;

    private Long uId;

    private Integer status;

    private byte[] fdGrp;
}