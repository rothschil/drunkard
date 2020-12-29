package xyz.wongs.drunkard.task;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.wongs.drunkard.base.BaseTest;

public class FileInfoCase extends BaseTest {

    @Autowired
    public RunFileTask runFileTask;

    @Test
    public void test(){
        runFileTask.run("G:\\Image");
    }
}
