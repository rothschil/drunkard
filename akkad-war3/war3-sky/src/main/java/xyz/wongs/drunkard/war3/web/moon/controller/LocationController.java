package xyz.wongs.drunkard.war3.web.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.service.LocationService;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Validated
@ResponseResult
@RestController
@RequestMapping(value = "/area")
public class LocationController{

    @Autowired
    @Qualifier("locationService")
    private LocationService locationService;

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:59
     * @Param lv 层级
     * @return Location
     **/
    @RequestMapping(value = "/{lv}", method = RequestMethod.GET)
    public List<Location> getLocationListByLevel(@PathVariable(value = "lv") Integer lv) {
        return locationService.getLocationListByLevel(lv);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:59
     * @return Map
     **/
    @ApplicationLog
    @GetMapping("/test")
    public Map<String, Object> test() {
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("info", "测试成功");
        return data;
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:59
     * @Param userId id
     * @return Map
     **/
    @ApplicationLog
    @GetMapping("/valid")
    public Map<String, Object> testValidator(@NotNull(message = "userId不能为空") Integer userId) {
        HashMap<String, Object> data = new HashMap<>(3);
        data.put("info", "测试成功 [userId]="+userId);
        return data;
    }


}

