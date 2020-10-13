package xyz.wongs.drunkard.domain.real.entity;

import lombok.Data;
import xyz.wongs.drunkard.base.persistence.mybatis.entity.BaseEntityAbstract;

/**
 * @ClassName RelCertId
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/17 11:15
 * @Version 1.0.0
*/
@Data
public class RelCertId extends BaseEntityAbstract<Long> {

    private Long Id;

    private String certName;

    private String certId;

    private String relStat;
}