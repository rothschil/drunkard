package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunkard.domain.addbook.entity.PersonalGrp;

public interface PersonalGrpMapper extends BaseMapper<PersonalGrp,Long> {
    int deleteByPrimaryKey(Long personalGrpId);

    int insert(PersonalGrp record);

    int insertSelective(PersonalGrp record);

    PersonalGrp selectByPrimaryKey(Long personalGrpId);

    int updateByPrimaryKeySelective(PersonalGrp record);

    int updateByPrimaryKeyWithBLOBs(PersonalGrp record);

    int updateByPrimaryKey(PersonalGrp record);
}