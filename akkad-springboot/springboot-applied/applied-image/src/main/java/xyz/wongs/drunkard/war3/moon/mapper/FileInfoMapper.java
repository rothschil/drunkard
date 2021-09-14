package xyz.wongs.drunkard.war3.moon.mapper;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;

import java.util.List;

/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/13 - 9:41
 * @Version 1.0.0
 */
public interface FileInfoMapper extends BaseMapper<FileInfo,Long> {
    @Override
    int deleteByPrimaryKey(Long id);

    @Override
    FileInfo selectByPrimaryKey(Long id);

    @Override
    int updateByPrimaryKeySelective(FileInfo record);

    @Override
    int updateByPrimaryKey(FileInfo record);

    void batchInsert(List<FileInfo> lists);

}