package xyz.wongs.drunkard.base.aop.pojo;

import lombok.*;
import xyz.wongs.drunkard.base.po.BasePo;

import java.util.Date;
/**
 * @Description 操作日志
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/12/3 15:06
 * @Version 1.0.0
*/
@EqualsAndHashCode(callSuper=false)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationLog extends BasePo<Long> {

    private Long id;

    private String ipAddress;

    private String logType;

    private String logName;

    private String actionUrl;

    private String userAcct;

    private String requestMethod;

    private String userAgent;

    private String className;

    private String methodName;

    private Date beginTime;

    private Date endTime;

    private Long cost;

    private int isSuccess;

    private String methodDesc;

    private String methodParam;

    private String errMsg;

    private String reqContent;

    private String respContent;

}