package xyz.wongs.drunkard.domain.addbook.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
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

    /**
     * @Description
     * @param registerUserId
     * @return 
     * @throws 
     * @date 2020/9/9 15:42
    */
    @Override
    int deleteByPrimaryKey(Long registerUserId);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    @Override
    int insert(RegisterUser record);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    @Override
    int insertSelective(RegisterUser record);

    /**
     * @Description
     * @param registerUserId
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    @Override
    RegisterUser selectByPrimaryKey(Long registerUserId);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    @Override
    int updateByPrimaryKeySelective(RegisterUser record);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    @Override
    int updateByPrimaryKey(RegisterUser record);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:42
     */
    List<RegisterUser> selectByRegUser(RegisterUser regUser);
}