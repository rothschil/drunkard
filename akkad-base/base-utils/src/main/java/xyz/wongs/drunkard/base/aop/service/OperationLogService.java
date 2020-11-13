package xyz.wongs.drunkard.base.aop.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.aop.pojo.OperationLog;

@Slf4j
@Transactional(readOnly = false)
@Service
public class OperationLogService {

    public void insert(OperationLog operationLog){
        log.error(operationLog.toString());
    }

}
