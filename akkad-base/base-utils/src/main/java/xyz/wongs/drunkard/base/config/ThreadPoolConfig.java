package xyz.wongs.drunkard.base.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import xyz.wongs.drunkard.base.utils.thread.ThreadPoolUtils;
import xyz.wongs.drunkard.base.utils.thread.Threads;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/** 线程池任务配置
 * @ClassName ThreadPoolConfig
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 15:19
 * @Version 1.0.0
*/
@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize = 200;

    /**
     * 队列最大长度
     */
    private int queueCapacity = 1000;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 300;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private String threadName = "ThreadPool";

    /**
         @Bean(name = "threadPoolTaskExecutor")
         public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
         executor.setMaxPoolSize(maxPoolSize);
         executor.setCorePoolSize(corePoolSize);
         executor.setQueueCapacity(queueCapacity);
         executor.setKeepAliveSeconds(keepAliveSeconds);
         // 线程池对拒绝任务(无线程可用)的处理策略
         executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
         return executor;
         ThreadPoolTaskExecutor executor = ThreadPoolUtils.doCreate(corePoolSize,maxPoolSize,queueCapacity,threadName);
         }
     **/
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }



    /**
     * 执行周期性或定时任务
     */
    @Bean(name = "scheduledExecutorService")
    protected ScheduledExecutorService scheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }
}
