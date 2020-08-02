package xyz.wongs.drunkard.base.utils.thread;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadInfo
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:51
 * @Version 1.0.0
*/
public class ThreadInfo implements Runnable{

    @Getter
    @Setter
    private String value;

    public ThreadInfo(){

    }

    public ThreadInfo(String value){
        this.value=value;
    }

    @Override
    public void run() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前时间 "+localDateTime.getMinute()+":"+localDateTime.getSecond()+" 当前线程名: "+Thread.currentThread().getName()+" BEGIN "+value );
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
