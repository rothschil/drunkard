package xyz.wongs.drunkard.war3.moon.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;

import java.util.List;

public interface FileInfoMapper extends BaseMapper<FileInfo,Long> {
    int deleteByPrimaryKey(Long id);

    FileInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FileInfo record);

    int updateByPrimaryKey(FileInfo record);

    void batchInsert(List<FileInfo> lists);
}