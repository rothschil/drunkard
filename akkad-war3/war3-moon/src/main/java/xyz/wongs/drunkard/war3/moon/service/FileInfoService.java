package xyz.wongs.drunkard.war3.moon.service;//package xyz.wongs.drunkard.war3.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.base.persistence.mybatis.service.BaseService;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;
import xyz.wongs.drunkard.war3.moon.mapper.FileInfoMapper;

import java.util.List;

/**
 * @ClassName FileInfoService
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 16:11
 * @Version 1.0.0
*/
@Slf4j
@Service(value="fileInfoService")
@Transactional(readOnly = true)
public class FileInfoService extends BaseService<FileInfo, Long> {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    protected BaseMapper<FileInfo, Long> getMapper() {
        return fileInfoMapper;
    }

	@Transactional(readOnly = false)
	public void insert(List<FileInfo> lists){
        fileInfoMapper.batchInsert(lists);
	}

    public void queryByCursor(int limit){
        Cursor<FileInfo> cursor = fileInfoMapper.queryByCursor(limit);
        cursor.forEach(foo -> {log.error(foo.toString());});
    }
}
