package xyz.wongs.drunkard.war3.domain.entity;

import lombok.*;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbstractPo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 行政区域实体类
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:27
 * @Version 1.0.0
*/
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_locations")
public class Location extends AbstractPo<Long> {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "flag")
    private String flag;

    @Column(name = "local_code")
    private String localCode;

    @Column(name = "local_name")
    private String localName;

    @Column(name = "lv")
    private Integer lv;

    @Column(name = "sup_local_code")
    private String supLocalCode;

    @Column(name = "url")
    private String url;

}