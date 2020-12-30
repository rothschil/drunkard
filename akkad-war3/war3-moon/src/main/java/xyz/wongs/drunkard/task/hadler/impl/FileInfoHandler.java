package xyz.wongs.drunkard.task.hadler.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.task.hadler.IntfFileInfoHandler;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.service.FileInfoService;

import java.util.List;

@Data
@Slf4j
@Component("fileInfoHandler")
public class FileInfoHandler implements IntfFileInfoHandler {

    private List<FileInfo> lists;

    @Autowired
    private FileInfoService fileInfoService;

    public void processData(){
        fileInfoService.insert(lists);
    }
}
