package xyz.wongs.drunkard.base.utils.thread;

import javax.validation.constraints.NotNull;
import java.util.concurrent.*;

/**
 * @ClassName ThreadPoolUtils
 * @Description  手工创建线程池
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/19 16:05
 * @Version 1.0.0
*/
public class ThreadPoolUtils {


    /**
     * @Description
     * @param corePoolSize  核心线程数
     * @param maximumPoolSize   允许并行最大核心线程数
     * @param theadName 指定线程名字
     * @return java.util.concurrent.ExecutorService
     * @throws
     * @date 20/11/19 16:23
     */
    public static ExecutorService doCreate(int corePoolSize,int maximumPoolSize,@NotNull String theadName){
        return doCreate(corePoolSize, maximumPoolSize,0,theadName);
    }

    /**
     * @Description
     * @param corePoolSize  核心线程数
     * @param maximumPoolSize   允许并行最大核心线程数
     * @param queueSize 有界队列的大小
     * @param theadName 指定线程名字
     * @return java.util.concurrent.ExecutorService
     * @throws
     * @date 20/11/19 16:23
     */
    public static ExecutorService doCreate(int corePoolSize,int maximumPoolSize,
                                                     @NotNull int queueSize,
                                                     @NotNull String theadName){
        return doCreate(corePoolSize, maximumPoolSize,0, TimeUnit.SECONDS, queueSize,theadName);
    }

    /**
     * @Description
     * @param corePoolSize  核心线程数
     * @param maximumPoolSize   允许并行最大核心线程数
     * @param keepAliveTime 当线程数大于内核数时，这是多余的空闲线程将在终止之前等待新任务的最长时间
     * @param unit
     * @param queueSize 有界队列的大小
     * @param theadName 指定线程名字
     * @return java.util.concurrent.ExecutorService
     * @throws
     * @date 20/11/19 16:23
     */
    public static ExecutorService doCreate(int corePoolSize,int maximumPoolSize,int keepAliveTime,TimeUnit unit,
                                                     @NotNull int queueSize,
                                                     @NotNull String theadName){
        // 1、指定有界队列，并明确大小
        queueSize= queueSize==0?8:queueSize;
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueSize);

        // 2、自定义线程名字
        ThreadFactory threadFactory = new CoustomThreadFactory(theadName);

        // 3、自定义线程池超出容量的拒绝策略
        RejectedExecutionHandler policy = new CoustomRejectedExecutionHandler();
        return doCreate(corePoolSize, maximumPoolSize,keepAliveTime, unit, queue,threadFactory, policy);
    }

    /** 判断 核心线程池大小 是否 超出 CPU数量，设定合理的线程池大小
     * @Description
     * @param corePoolSize
     * @return int
     * @throws
     * @date 20/11/19 16:13
     */
    public static int getCorePoolSize(int corePoolSize){
        int prcessorSize = Runtime.getRuntime().availableProcessors();
        //核心线程池大小 超出 CPU数量两倍
        int bic = 2;
        if(corePoolSize > prcessorSize * bic){
            corePoolSize = prcessorSize * bic;
        }
        return corePoolSize;
    }

    /**
     * @Description
     * @param corePoolSize  核心线程数
     * @param maximumPoolSize   允许并行最大核心线程数
     * @param keepAliveTime 当线程数大于内核数时，这是多余的空闲线程将在终止之前等待新任务的最长时间
     * @param unit
     * @param workQueue 在执行任务之前用于保留任务的队列，此队列将仅保存execute方法提交的Runnable任务。
     * @param threadFactory 执行程序创建新线程时要使用的工厂
     * @param handler   当线等待队列中的数量超过既定容量，所需要处理策略
     * @return java.util.concurrent.ExecutorService
     * @throws
     * @date 20/11/19 16:23
     */
    public static ExecutorService doCreate(int corePoolSize,int maximumPoolSize,int keepAliveTime,TimeUnit unit,
            @NotNull BlockingQueue<Runnable> workQueue,
            @NotNull ThreadFactory threadFactory,
            @NotNull RejectedExecutionHandler handler){
        // 初始化大小
        int poolSize = getCorePoolSize(corePoolSize);
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,keepAliveTime, unit, workQueue,threadFactory, handler);
    }
}
