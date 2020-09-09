package xyz.wongs.drunkard.war3.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/** 区域Vo
 * @ClassName IpRegionVo
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:36
 * @Version 1.0.0
*/
@Builder(toBuilder = true)
@Data
public class IpRegionVo {

    private String country;

    private String region;

    private String province;

    private String city;

    private String isp;
}
