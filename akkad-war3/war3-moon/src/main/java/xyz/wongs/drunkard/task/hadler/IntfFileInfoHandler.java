package xyz.wongs.drunkard.task.hadler;


import xyz.wongs.drunkard.base.aop.pojo.OperationLog;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;

import java.security.PrivateKey;
import java.util.List;

public interface IntfFileInfoHandler {

    /**  这里也就是我们实现QueueTaskHandler的处理接口
     * @Description
     * @return
     * @throws 
     * @date 20/11/19 17:09
    */
    void processData();
}
