package xyz.wongs.drunkard.task;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.wongs.drunkard.base.BaseTest;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

public class FileInfoCase extends BaseTest {

    private static Logger LOG = LoggerFactory.getLogger(FileInfoCase.class);

    @Autowired
    public RunFileTask runFileTask;

    @Autowired
    public FileInfoService fileInfoService;

    @Test
    public void test(){
        long start  = System.currentTimeMillis();
        runFileTask.run("G:\\Image");
        long end  = System.currentTimeMillis();
        LOG.info("耗时 cost ={} 秒",(end-start)/1000);
    }

}
