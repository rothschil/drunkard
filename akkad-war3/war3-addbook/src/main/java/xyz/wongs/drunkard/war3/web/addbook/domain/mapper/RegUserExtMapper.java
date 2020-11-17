package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.RegUserExt;

/** 注册账号的扩展信息
 * @ClassName RegUserExtMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:11
 * @Version 1.0.0
*/
public interface RegUserExtMapper extends BaseMapper<RegUserExt,Long> {

    @Override
    int deleteByPrimaryKey(Long uRegUserExtId);

    @Override
    int insert(RegUserExt record);

    @Override
    int insertSelective(RegUserExt record);

    @Override
    RegUserExt selectByPrimaryKey(Long uRegUserExtId);

    @Override
    int updateByPrimaryKeySelective(RegUserExt record);

    @Override
    int updateByPrimaryKey(RegUserExt record);
}