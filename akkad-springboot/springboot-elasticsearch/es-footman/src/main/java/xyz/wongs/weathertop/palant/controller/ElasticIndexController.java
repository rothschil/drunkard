package xyz.wongs.weathertop.palant.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.weathertop.base.service.BaseElasticService;
import xyz.wongs.weathertop.palant.vo.IdxVo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WCNGS@QQ.COM
 * @ClassName ElasticIndexController
 * @Description ElasticSearch索引的基本管理，提供对外查询、删除和新增功能
 * @Github <a>https://github.com/rothschil</a>
 * @date 2019/11/19 11:22
 * @Version 1.0.0
 */
@Slf4j
@RequestMapping("/elastic")
@RestController
public class ElasticIndexController {

    @Autowired
    BaseElasticService baseElasticService;


    /**
     * @param idxVo
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description 创建Elastic索引
     * @date 2019/11/19 11:07
     */
    @PostMapping(value = "/createIndex")
    public Map<String, String> createIndex(@RequestBody IdxVo idxVo) {
        try {
            //索引不存在，再创建，否则不允许创建
            if (!baseElasticService.isExistsIndex(idxVo.getIdxName())) {
                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}", idxVo.getIdxName(), idxSql);
                baseElasticService.createIndex(idxVo.getIdxName(), idxSql);
            } else {
                throw new DrunkardException(ResultCode.DATA_HAS_EXISTED);
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


    /**
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public void indexExist(@PathVariable(value = "index") String index) {

        try {
            if (!baseElasticService.isExistsIndex(index)) {
                log.error("index={},不存在", index);
                throw new DrunkardException(ResultCode.DATA_NOT_EXIST);
            } else {
                throw new DrunkardException(ResultCode.DATA_HAS_EXISTED);
            }
        } catch (Exception e) {
            throw new DrunkardException(ResultCode.RUNTIME_EXCEPTION);
        }
    }

    @GetMapping(value = "/del/{index}")
    public Map<String, String> indexDel(@PathVariable(value = "index") String index) {
        try {
            baseElasticService.deleteIndex(index);
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
}
