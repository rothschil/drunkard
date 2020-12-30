package xyz.wongs.drunkard.task;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.wongs.drunkard.base.BaseTest;

import javax.validation.constraints.Size;

@Slf4j
public class FileInfoCase extends BaseTest {

    @Autowired
    public RunFileTask runFileTask;

    @Test
    public void test(){
        long start  = System.currentTimeMillis();
        runFileTask.run("G:\\Image");
        long end  = System.currentTimeMillis();
        log.info("耗时 cost ={} 秒",(end-start)/1000);
    }
}
