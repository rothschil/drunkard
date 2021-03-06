package xyz.wongs.drunkard.entity.quanmin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.wongs.drunkard.base.po.BasePo;

import java.util.Date;

@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig extends BasePo<Long> {
    private Long id;

    private String key;

    private String value;

    private int status;

    private String remark;

    private Date gmtCreate;

    private Date gmtModified;


}