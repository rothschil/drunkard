package xyz.wongs.drunkard.base.utils.thread;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
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
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
