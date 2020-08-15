package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunkard.domain.addbook.entity.RegUserExt;

public interface RegUserExtMapper extends BaseMapper<RegUserExt,Long> {
    int deleteByPrimaryKey(Long uRegUserExtId);

    int insert(RegUserExt record);

    int insertSelective(RegUserExt record);

    RegUserExt selectByPrimaryKey(Long uRegUserExtId);

    int updateByPrimaryKeySelective(RegUserExt record);

    int updateByPrimaryKey(RegUserExt record);
}