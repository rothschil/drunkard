package xyz.wongs.drunkard.mapper.quanmin;


import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.entity.quanmin.InformSms;

public interface InformSmsMapper extends BaseMapper<InformSms,Long> {
    int deleteByPrimaryKey(Integer informId);

    @Override
    InformSms selectByPrimaryKey(Long informId);

    @Override
    int updateByPrimaryKeySelective(InformSms record);

    @Override
    int updateByPrimaryKey(InformSms record);
}