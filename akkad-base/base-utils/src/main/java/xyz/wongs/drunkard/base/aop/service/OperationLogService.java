package xyz.wongs.drunkard.base.aop.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;

/**
 * @ClassName OperationLogService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Slf4j
@Transactional(readOnly = false)
@Service
public class OperationLogService {

    public void insert(OperationLog operationLog){
        log.error(operationLog.toString());
    }

}
