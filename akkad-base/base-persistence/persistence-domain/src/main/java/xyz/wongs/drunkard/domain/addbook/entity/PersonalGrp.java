package xyz.wongs.drunkard.domain.addbook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalGrp extends BaseEntityAbstract<Long> {
    private Long id;

    private Long registerUserId;

    private String status;

    private byte[] fdGrp;
}