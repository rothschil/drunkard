package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.addbook.entity.RegUser;

import java.util.List;

/**
 * @ClassName RegUserMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:03
 * @Version 1.0.0
*/
public interface RegUserMapper extends BaseMapper<RegUser,Long> {

    /**
     * 待补充
     * @param uId
     * @return
     */
    @Override
    int deleteByPrimaryKey(Long uId);

    /**
     * 待补充
     * @param  record
     * @return
     */
    @Override
    Long insert(RegUser record);

    /**
     * 待补充
     * @param record
     * @return
     */
    @Override
    Long insertSelective(RegUser record);

    /**
     * 待补充
     * @param uId
     * @return
     */
    @Override
    RegUser selectByPrimaryKey(Long uId);

    /**
     * 根据用户信息查询
     * @param regUser
     * @return
     */
    List<RegUser> selectByRegUser(RegUser regUser);

    /**
     * 待补充
     * @param record
     * @return
     */
    @Override
    int updateByPrimaryKeySelective(RegUser record);

    /**
     * 待补充
     * @param record
     * @return
     */
    @Override
    int updateByPrimaryKeyWithBlob(RegUser record);

    /** 待补充
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/8/2 14:11
    */
    @Override
    int updateByPrimaryKey(RegUser record);
}