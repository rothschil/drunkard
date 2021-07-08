package xyz.wongs.drunkard.war3.domain.entity;

import lombok.*;
import xyz.wongs.drunkard.base.po.BasePo;

/** 行政区域实体类
 * @ClassName Location
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
public class Location extends BasePo<Long> {

    private Long id;

    private String flag;

    private String localCode;

    private String localName;

    private Integer lv;

    private String supLocalCode;

    private String url;

    public Location(Long id, String localName, String supLocalCode, String url, Integer lv) {
        this.id=id;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv,String flag) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
        this.flag=flag;
    }

}