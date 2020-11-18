package xyz.wongs.drunkard.base.handler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.base.aop.service.OperationLogService;
import xyz.wongs.drunkard.base.handler.IQueueTaskHandler;

/**
 * @ClassName QueueTaskHandler
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Data
@Slf4j
@Component
public class QueueTaskHandler implements IQueueTaskHandler {

    @Autowired
    private OperationLogService operationLogService;

    private OperationLog operationLog;

    /**
     * @Description 这里也就是我们实现QueueTaskHandler的处理接口
     * @param
     * @return void
     * @throws
     * @date 20/11/13 16:32
     */
    @Override
    public void processData() {
        operationLogService.insert(operationLog);
        // 可以去做你想做的业务了
        // 这里需要引用spring的service的话，我写了一个工具类，下面会贴出来
        // ItestService testService = SpringUtils.getBean(ItestService.class);
    }
}
