package xyz.wongs.drunkard.domain.real.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.wongs.drunkard.base.persistence.mybatis.mapper.BaseMapper;
import xyz.wongs.drunkard.domain.real.entity.RelCertId;

import java.util.List;

/**
 * @ClassName RelCertIdMapper
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/17 11:15
 * @Version 1.0.0
*/
public interface RelCertIdMapper extends BaseMapper<RelCertId,Long> {

    int deleteByPrimaryKey(Long certSort);

    RelCertId selectByPrimaryKey(Long certSort);

    int updateByPrimaryKeySelective(RelCertId record);

    int updateByPrimaryKey(RelCertId record);

    int updateBatch(@Param("relStat") String relStat,@Param("list") List list);

    List<RelCertId> getList2(@Param("size") int size);

    int getCount();
}