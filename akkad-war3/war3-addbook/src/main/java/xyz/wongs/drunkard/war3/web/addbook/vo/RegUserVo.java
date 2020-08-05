package xyz.wongs.drunkard.war3.web.addbook.vo;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * @ClassName RegUserVo
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/4 18:54
 * @Version 1.0.0
*/
@Builder(toBuilder=true)
@Data
public class RegUserVo implements Serializable {

    @Size(min=6, max=15, message = "编码在 66 ~ 15 个字符之间")
    private String uCode;

    @Length(min=11, max=11, message = "手机号不合法")
    private String uMobile;
}
