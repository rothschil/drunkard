package xyz.wongs.drunkard.base.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.base.aop.service.OperationLogService;
import xyz.wongs.drunkard.base.handler.IQueueTaskHandler;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Component
public class QueueTaskHandler implements IQueueTaskHandler {

    private static final Logger LOG = LoggerFactory.getLogger(QueueTaskHandler.class);

    @Autowired
    private OperationLogService operationLogService;

    private OperationLog operationLog;

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 这里也就是我们实现QueueTaskHandler的处理接口
     * @Date 20/11/13 16:32
     * @Param
     **/
    @Override
    public void processData() {
        operationLogService.insert(operationLog);
        // 可以加上自己的事后处理逻辑....
    }

    public OperationLog getOperationLog() {
        return operationLog;
    }

    public void setOperationLog(OperationLog operationLog) {
        this.operationLog = operationLog;
    }
}
