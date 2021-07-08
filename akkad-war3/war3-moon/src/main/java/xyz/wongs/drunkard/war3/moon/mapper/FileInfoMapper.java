package xyz.wongs.drunkard.war3.moon.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.moon.entity.FileInfo;

import java.util.List;

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

    @Select("select * from tb_file_info limit #{limit}")
    //@Options(fetchSize = Integer.MIN_VALUE)//mysql情况比较特殊，只能这样设置
    Cursor<FileInfo> queryByCursor(@Param("limit") int limit);
}