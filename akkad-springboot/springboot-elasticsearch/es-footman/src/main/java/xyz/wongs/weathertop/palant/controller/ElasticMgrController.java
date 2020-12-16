package xyz.wongs.weathertop.palant.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.weathertop.base.entiy.ElasticEntity;
import xyz.wongs.weathertop.base.service.BaseElasticService;
import xyz.wongs.weathertop.palant.domain.entity.Location;
import xyz.wongs.weathertop.palant.domain.service.LocationService;
import xyz.wongs.weathertop.palant.utils.ElasticUtil;
import xyz.wongs.weathertop.palant.vo.ElasticDataVo;
import xyz.wongs.weathertop.palant.vo.QueryVo;

import java.util.*;

/**
 * 数据管理
 *
 * @author WCNGS@QQ.COM
 * @ClassName ElasticMgrController
 * @Description
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/10/25 16:55
 * @Version 1.0.0
 */
@Slf4j
@RequestMapping("/elasticMgr")
@RestController
public class ElasticMgrController {

    @Autowired
    private BaseElasticService baseElasticService;

    @Autowired
    LocationService locationService;


    /**
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description 新增数据
     * @date 2019/11/20 17:10
     */
    @PostMapping(value = "/add")
    public Map<String, String> add(@RequestBody ElasticDataVo elasticDataVo) {
        try {
            if (!StringUtils.isNotEmpty(elasticDataVo.getIdxName())) {
                log.warn("索引为空");
                throw new DrunkardException(ResultCode.PARAMS_IS_BANK);
            }
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(elasticDataVo.getElasticEntity().getId());
            elasticEntity.setData(elasticDataVo.getElasticEntity().getData());

            baseElasticService.insertOrUpdateOne(elasticDataVo.getIdxName(), elasticEntity);

        } catch (Exception e) {
            log.error("插入数据异常，metadataVo={},异常信息={}", elasticDataVo.toString(), e.getMessage());
            throw new DrunkardException(ResultCode.RUNTIME_EXCEPTION);
        }
        Map<String, String> result = new HashMap<String, String>() {
            {
                put(Constant.RESULT, Constant.SUCCESS);
            }
        };
        return result;
    }


    /**
     * @param elasticDataVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description 删除
     * @date 2019/11/21 9:56
     */
    @PostMapping(value = "/delete")
    public Map<String, String> delete(@RequestBody ElasticDataVo elasticDataVo) {
        try {
            if (!StringUtils.isNotEmpty(elasticDataVo.getIdxName())) {
                log.warn("索引为空");
                throw new DrunkardException(ResultCode.PARAMS_IS_BANK);
            }
            baseElasticService.deleteOne(elasticDataVo.getIdxName(), elasticDataVo.getElasticEntity());
        } catch (Exception e) {
            log.error("删除数据失败");
            throw new DrunkardException(ResultCode.RUNTIME_EXCEPTION);
        }
        Map<String, String> result = new HashMap<String, String>() {
            {
                put(Constant.RESULT, Constant.SUCCESS);
            }
        };
        return result;

    }

    /**
     * @param index 初始化Location区域，写入数据。
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description
     * @date 2019/11/20 17:10
     */
    @GetMapping(value = "/addLocation/{index}")
    public Map<String, String> addLocation(@PathVariable(value = "index") String index) {
        try {
            for (int lv = 0; lv < 4; lv++) {
                addLocationPage(1, 100, index, lv);
            }

        } catch (Exception e) {
            throw new DrunkardException(ResultCode.RUNTIME_EXCEPTION);
        }
        Map<String, String> result = new HashMap<String, String>() {
            {
                put(Constant.RESULT, Constant.SUCCESS);
            }
        };
        return result;
    }

    public void addLocationPage(int pageNum, int pageSize, String index, int lv) {
        Location location = new Location();
        location.setLv(lv);
        PageHelper.startPage(pageNum, pageSize);
        List<Location> locations = locationService.getList2(location);
        PageInfo pageInfo = new PageInfo(locations);
        if (!pageInfo.getList().isEmpty()) {
            log.error("第{}层级，第{}页，开始入ES库", lv, pageNum);
            insertDatas(index, locations);
            if (pageInfo.isHasNextPage()) {
                addLocationPage(pageInfo.getNextPage(), pageSize, index, lv);
            }
        }
    }


    public void insertDatas(String idxName, List<Location> locations) {
        List<ElasticEntity> elasticEntitys = new ArrayList<ElasticEntity>(locations.size());
        for (Location _loca : locations) {
            ElasticEntity elasticEntity = new ElasticEntity();
            elasticEntity.setId(_loca.getId().toString());
//            elasticEntity.setData(gson.toJson(_loca));
//            elasticEntitys.add(elasticEntity);
//            log.error(_loca.toString());
        }
        baseElasticService.insertBatch(idxName, elasticEntitys);
    }

    /**
     * @param queryVo 查询实体对象
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description
     * @date 2019/11/21 9:31
     */
    @GetMapping(value = "/get")
    public Map<String, Object> get(@RequestBody QueryVo queryVo) {

        if (!StringUtils.isNotEmpty(queryVo.getIdxName())) {
            log.warn("索引为空");
            throw new DrunkardException(ResultCode.PARAMS_IS_BANK);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Class<?> clazz = ElasticUtil.getClazz(queryVo.getClassName());
            Map<String, Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders = null;
            for (String ke : keys) {
                queryBuilders = QueryBuilders.matchQuery(ke, params.get(ke));
            }
            if (null != queryBuilders) {
                SearchSourceBuilder searchSourceBuilder = ElasticUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticService.search(queryVo.getIdxName(), searchSourceBuilder, clazz);
                result.put(Constant.RESULT, data);
            }
        } catch (Exception e) {
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(), e.getMessage());
            throw new DrunkardException(ResultCode.RUNTIME_EXCEPTION);
        }
        return result;
    }

}
