package xyz.wongs.drunkard.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.file.FileUtil;
import xyz.wongs.drunkard.base.utils.security.Md5Utils;
import xyz.wongs.drunkard.war3.domain.entity.FileInfo;
import xyz.wongs.drunkard.war3.domain.service.FileInfoService;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ResultCode 定义的接口状态码
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/12/28 17:21
 * @Version 1.0.0
 */
@Component
@Slf4j
public class RunFileTask {

    @Autowired
    public FileInfoService fileInfoService;

    public void run(String path){
        File file = new File(path);
        if(!file.isDirectory()){
            return;
        }
        listFiles(file);
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
            String fileName = FileUtil.getName(fl);
            float size = fl.length()/1024;
            size = scale(size);
            String filePath = FileUtil.getAbsolutePath(fl);
            FileInfo fileInfo = FileInfo.builder().fileName(fileName).filePath(filePath).fileSize(size).suffixName(suffixName)
                    .md5(Md5Utils.getMd5(fl)).build();
            lists.add(fileInfo);
        }
        if(!lists.isEmpty()){
            fileInfoService.insert(lists);
        }
    }

    public static void main(String[] args) {
        new RunFileTask().run("G:\\Image\\200323\\2015");
    }


    float scale(Float floatValue) {
        DecimalFormat format = new DecimalFormat("#.00");
        String scaled = format.format(floatValue);
        return Float.parseFloat(scaled);
    }

    double scale(Double doubleValue) {
        DecimalFormat format = new DecimalFormat("#.00");
        String scaled = format.format(doubleValue);
        return Double.parseDouble(scaled);
    }
}
