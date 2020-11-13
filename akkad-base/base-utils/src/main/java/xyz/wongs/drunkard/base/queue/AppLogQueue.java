package xyz.wongs.drunkard.base.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.handler.IQueueTaskHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/** 异步处理日志的队列
 * @ClassName AppLogQueue
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/13 16:14
 * @Version 1.0.0
*/
@Slf4j
@Component
public class AppLogQueue {

    private final LinkedBlockingQueue<IQueueTaskHandler> queue = new LinkedBlockingQueue<IQueueTaskHandler>(500);

    // 类似于一个线程总管 保证所有的任务都在队列之中
    private ExecutorService service = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());
    /**
     * 检查服务是否运行
     */
    private volatile boolean running = true;

    /**
     * 线程状态
     */
    private Future<?> threadStatus = null;

    @PostConstruct
    public void init(){
        threadStatus = service.submit(new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(running){
                            try {
                                IQueueTaskHandler taskHandler = queue.take();
                                taskHandler.processData();
                            } catch (InterruptedException e) {
                                log.error("服务停止，退出", e);
                                running = false;
                            }
                        }
                    }
                },"Save App Log Thread"));
    }

    @PreDestroy
    public void destory() {
        running = false;
        service.shutdownNow();
    }

    public void activeService() {
        running = true;
        if (service.isShutdown()) {
            service = Executors.newSingleThreadExecutor();
            init();
            log.info("线程池关闭，重新初始化线程池及任务");
        }
        if (threadStatus.isDone()) {
            init();
            log.info("线程池任务结束，重新初始化任务");
        }
    }

    public boolean addQueue(IQueueTaskHandler taskHandler){
        if(!running){
            log.warn("service is stop");
            return false;
        }

        boolean isFull = queue.offer(taskHandler);
        if(!isFull){
            log.warn("添加任务到队列失败");
        }
        return isFull;
    }

    public boolean empty(){
        return queue.isEmpty();
    }

}
