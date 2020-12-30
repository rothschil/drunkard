package xyz.wongs.drunkard.task.thread;

import org.apache.commons.codec.digest.DigestUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/** 异步获取文件名
 * @ClassName FileSizeThread
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/30 13:16
 * @Version 1.0.0
*/
public class FileSizeThread implements Callable<String> {

    private File file;

    public FileSizeThread(){

    }

    public FileSizeThread(File file){
        this.file = file;
    }

    @Override
    public String call() throws Exception {
        try {
            return DigestUtils.md5Hex(new FileInputStream(file));
        } catch (IOException e) {
            return StringUtils.EMPTY;
        }
    }
}
