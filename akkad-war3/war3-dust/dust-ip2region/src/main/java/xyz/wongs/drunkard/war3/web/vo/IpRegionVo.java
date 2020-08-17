package xyz.wongs.drunkard.war3.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder(toBuilder = true)
@Data
public class IpRegionVo {

    private String country;

    private String region;

    private String province;

    private String city;

    private String isp;
}
