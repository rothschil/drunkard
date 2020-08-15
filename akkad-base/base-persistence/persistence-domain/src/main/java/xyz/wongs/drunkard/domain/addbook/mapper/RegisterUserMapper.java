package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.DictRegion;
import xyz.wongs.drunkard.domain.addbook.entity.RegisterUser;

import java.util.List;

/**
 * @ClassName RegisterUserMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/15 22:16
 * @Version 1.0.0
*/
public interface RegisterUserMapper extends BaseMapper<RegisterUser,Long> {
    int deleteByPrimaryKey(Long registerUserId);

    int insert(RegisterUser record);

    int insertSelective(RegisterUser record);

    RegisterUser selectByPrimaryKey(Long registerUserId);

    int updateByPrimaryKeySelective(RegisterUser record);

    int updateByPrimaryKey(RegisterUser record);

    List<RegisterUser> selectByRegUser(RegisterUser regUser);
}