package xyz.wongs.drunkard.base.utils.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DrunkardThreadFactory
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:47
 * @Version 1.0.0
*/
public class DrunkardThreadFactory implements ThreadFactory {
    private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    DrunkardThreadFactory() {
        //默认namePrefix = default-name-pool
        this("drunkard-pool");
    }

    DrunkardThreadFactory(String name){
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        //此时namePrefix就是 name + 第几个用这个工厂创建线程池的
        this.namePrefix = name +
                POOL_NUMBER.getAndIncrement();
    }

    @Override
    public Thread newThread(Runnable r) {
        //此时线程的名字 就是 namePrefix + -thread- + 这个线程池中第几个执行的线程
        Thread t = new Thread(group, r,
                namePrefix + "-thread-"+threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
