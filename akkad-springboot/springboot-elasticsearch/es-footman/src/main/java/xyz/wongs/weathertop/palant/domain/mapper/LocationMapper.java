package xyz.wongs.weathertop.palant.domain.mapper;

import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.weathertop.palant.domain.entity.Location;

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

    /** 写入
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int insert(Location record);

    /** 按照条件依次写入
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int insertSelective(Location record);

    /** 根据主键查询
     * @Description
     * @param id
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    Location selectByPrimaryKey(Long id);

    /** 根据主键修改 所选中的信息
     * @Description
     * @param record
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    int updateByPrimaryKeySelective(Location record);

    /** 根据属性获取数量
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    int getCount(Location location);

    /** 根据属性获取结果列表
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    @Override
    List<Location> getList(Location location);

    /** 批量插入
     * @Description
     * @param locations
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    int insertBatchByOn(List<Location> locations);

    /** 根据属性获取结果列表
     * @Description
     * @param location
     * @return
     * @throws
     * @date 2020/9/9 15:12
     */
    List<Location> getList2(Location location);

}