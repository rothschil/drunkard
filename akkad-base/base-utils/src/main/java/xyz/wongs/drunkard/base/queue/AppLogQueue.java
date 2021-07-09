package xyz.wongs.drunkard.base.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.handler.IQueueTaskHandler;
import xyz.wongs.drunkard.base.utils.thread.ThreadPoolUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 异步处理日志的队列，将需要存储的日志放入这个队列中
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/13 16:14
 * @Version 1.0.0
*/
@Component
public class AppLogQueue {
    
    private static final Logger LOG = LoggerFactory.getLogger(AppLogQueue.class);

    private final LinkedBlockingQueue<IQueueTaskHandler> queue = new LinkedBlockingQueue<>(500);

    /**
     * 检查服务是否运行
     */
    private volatile boolean running = true;

    private final ThreadPoolExecutor threadPoolExecutor =getThread();

    /**
     * 线程状态
     */
    private Future<?> threadStatus = null;

    @PostConstruct
    public void init(){
        threadStatus  = threadPoolExecutor.submit(() -> {
            while(running){
                try {
                    // 队列中不存在元素 则不处理
                    if(!queue.isEmpty()){
                        IQueueTaskHandler taskHandler = queue.take();
                        taskHandler.processData();
                    }
                } catch (InterruptedException e) {
                    LOG.error("服务停止，退出", e);
                    running = false;
                }
            }
        });
    }

    @PreDestroy
    public void destroys() {
        running = false;
        threadPoolExecutor.shutdownNow();
    }

    public void activeService() {
        running = true;
        if (threadPoolExecutor.isShutdown()) {
            init();
            LOG.info("线程池关闭，重新初始化线程池及任务");
        }
        if (threadStatus.isDone()) {
            init();
            LOG.info("线程池任务结束，重新!");
        }
    }

    public void addQueue(IQueueTaskHandler taskHandler){
        if(!running){
            LOG.warn("service is stop");
            return ;
        }

        boolean isFull = queue.offer(taskHandler);
        if(!isFull){
            LOG.warn("添加任务到队列失败");
        }
    }

    public boolean empty(){
        return queue.isEmpty();
    }


    private ThreadPoolExecutor getThread(){
        return ThreadPoolUtils.doCreate(1,1,0,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>(),this.getClass().getName());
    }

}
