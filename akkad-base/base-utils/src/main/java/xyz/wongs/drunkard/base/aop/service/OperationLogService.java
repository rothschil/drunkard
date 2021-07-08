package xyz.wongs.drunkard.base.aop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Transactional(rollbackFor = Exception.class)
@Service
public class OperationLogService {

    private static final Logger LOG = LoggerFactory.getLogger(OperationLogService.class);

    public void insert(OperationLog operationLog){
        LOG.error(operationLog.toString());
    }

}
