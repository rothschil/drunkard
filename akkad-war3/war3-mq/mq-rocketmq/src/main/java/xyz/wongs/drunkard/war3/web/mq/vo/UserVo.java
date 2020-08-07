package xyz.wongs.drunkard.war3.web.mq.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVo implements Serializable {

    private String id;
    private String name;
}
