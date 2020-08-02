package xyz.wongs.drunkard.base.utils.thread;

/**
 * @ClassName DrunkardHandler
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:50
 * @Version 1.0.0
*/
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class DrunkardHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (r instanceof ThreadInfo) {
            ThreadInfo thTk = (ThreadInfo) r;
            // 写入消息或者数据库
            System.out.println("当前任务 "+ thTk.getValue()+" 执行失败！");
        }
    }
}
