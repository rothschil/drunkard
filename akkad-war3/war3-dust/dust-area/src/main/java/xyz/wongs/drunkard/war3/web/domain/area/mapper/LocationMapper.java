package xyz.wongs.drunkard.war3.web.domain.area.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;

import java.util.List;

/**
 * @ClassName LocationMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:12
 * @Version 1.0.0
*/
public interface LocationMapper extends BaseMapper<Location,Long> {

    /** 根据主键ID删除实体信息
     * @Description
     * @param id
     * @return 
     * @throws 
     * @date 2020/9/9 15:12
    */
    @Override
    int deleteByPrimaryKey(Long id);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int insert(Location record);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int insertSelective(Location record);

    /**
     * @Description
     * @param id
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    Location selectByPrimaryKey(Long id);

    /**
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int updateByPrimaryKeySelective(Location record);

    /**
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    int getCount(Location location);

    /**
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    List<Location> getList(Location location);

    /**
     * @Description
     * @param locations
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    int insertBatchByOn(List<Location> locations);

    /**
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    List<Location> getList2(Location location);

}