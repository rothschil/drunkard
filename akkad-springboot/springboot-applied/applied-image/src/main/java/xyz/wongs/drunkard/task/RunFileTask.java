package xyz.wongs.drunkard.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.file.FileUtil;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.base.utils.thread.ThreadPoolUtils;
import xyz.wongs.drunkard.task.hadler.impl.FileInfoHandler;
import xyz.wongs.drunkard.task.queue.FileInfoQueue;
import xyz.wongs.drunkard.task.thread.FileSizeThread;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName RunFileTask
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/30 12:58
 * @Version 1.0.0
*/
@Component
@Slf4j
public class RunFileTask {

    public static final String THREAD_NAME ="RUN_FILE_NAME";

    @Autowired
    public FileInfoService fileInfoService;

    @Autowired
    private FileInfoQueue fileInfoQueue;

    @Autowired
    private FileInfoHandler fileInfoHandler;

    private ThreadPoolExecutor executor = ThreadPoolUtils.doCreate(3,5,THREAD_NAME);

    public void run(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return;
        }
        listFiles(file);
        //关闭线程池
        executor.shutdown();
    }

    public void listFiles(File file){
        File[] files = file.listFiles();
        List<FileInfo> lists = new ArrayList<FileInfo>();
        for (File fl : files) {
            if(fl.isDirectory()){
                listFiles(fl);
                continue;
            }
            String suffixName = FileUtil.getSuffix(fl);
            if(!ImageConst.LIST_SUFFIX.contains(suffixName.toUpperCase())){
                continue;
            }
            Future<String> result = executor.submit(new FileSizeThread(fl));
            String fileName = FileUtil.getName(fl);
            long size = fl.length();
            String filePath = FileUtil.getAbsolutePath(fl);
            try {
                FileInfo fileInfo = FileInfo.builder().fileName(fileName).filePath(filePath).fileSize(size).suffixName(suffixName)
                        .md5(result.get()).build();
                lists.add(fileInfo);
            } catch (InterruptedException e){
                e.printStackTrace();
            } catch (ExecutionException e){
                e.printStackTrace();
            }
        }
        if(!lists.isEmpty()){
            fileInfoHandler.setLists(lists);
            fileInfoQueue.addQueue(fileInfoHandler);
        }
    }
}
