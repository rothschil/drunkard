package xyz.wongs.drunkard.entity.location;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.po.BasePo;

@EqualsAndHashCode(callSuper=false)
@Data
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
        this.localCode = localCode;
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