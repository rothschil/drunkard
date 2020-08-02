package xyz.wongs.drunkard.base.utils.thread;

import lombok.extern.slf4j.Slf4j;
import xyz.wongs.drunkard.base.utils.StringUtils;

import java.util.AbstractQueue;
import java.util.concurrent.*;

/**
 * @author WCNGS@QQ.COM
 * @ClassName Threads
 * @Description 线程相关工具类.
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/6/19 17:31
 * @Version 1.0.0
 */
@Slf4j
public class Threads {

    /**
     * sleep等待,单位为毫秒
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * sleep等待,单位为秒
     */
    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * 停止线程池
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        int timeOut = 120;
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(timeOut, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(timeOut, TimeUnit.SECONDS)) {
                        log.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            log.error(t.getMessage(), t);
        }
    }

    /**
     * @Description
     * @param coreSize      核心线程数
     * @param maxSize       最大数量
     * @param keepAlive     存活时间
     * @param queueSize     指定有界队列的大小
     * @param threadName    线程名字
     * @return java.util.concurrent.ExecutorService
     * @throws
     * @date 2020/8/2 14:56
     */
    public static ExecutorService createThreadPool(int coreSize,int maxSize,int keepAlive,int queueSize,String threadName){
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(queueSize);
        ThreadFactory factory = null;
        if(StringUtils.isNotBlank(threadName)){
            factory = new DrunkardThreadFactory(threadName);
        }
        factory = new DrunkardThreadFactory();
        return new ThreadPoolExecutor(coreSize, maxSize, keepAlive, TimeUnit.SECONDS,queue,factory,new DrunkardHandler());
    }
}
