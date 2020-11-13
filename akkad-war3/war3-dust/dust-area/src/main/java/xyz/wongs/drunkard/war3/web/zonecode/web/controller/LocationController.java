package xyz.wongs.drunkard.war3.web.zonecode.web.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.web.BaseController;
import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;
import xyz.wongs.drunkard.war3.web.domain.area.service.LocationService;
import xyz.wongs.drunkard.war3.web.utils.ZoneCodeStringUtils;
import xyz.wongs.drunkard.war3.web.zonecode.task.ProcessService;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Api(value = "areas")
@RestController
@RequestMapping(value = "/areas")
public class LocationController extends BaseController {

    @Autowired
    @Qualifier("locationService")
    private LocationService locationService;

    /**
     *
     * @Title: getLocationListByLevel
     * @Description: 请求参数在URL中，需要在 @ApiImplicitParam 中加上 "paramType="path""
     * @param lv
     * @return  List<LocationEntity>
     */
    @ApiOperation(value = "获取行政区域列表", notes = "根据层级获取行政列表")
    @ApiImplicitParam(name = "lv", value = "层级", required = true, dataType = "Integer", paramType = "path")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful — 请求已完成"),
            @ApiResponse(code = 400, message = "请求中有语法问题，或不能满足请求"),
            @ApiResponse(code = 401, message = "未授权客户机访问数据"),
            @ApiResponse(code = 404, message = "服务器找不到给定的资源；文档不存在"),
            @ApiResponse(code = 500, message = "服务器不能完成请求")}
    )
    @RequestMapping(value = "/{lv}", method = RequestMethod.GET)
    public List<Location> getLocationListByLevel(@PathVariable(value = "lv") Integer lv) {
        List<Location> r = locationService.getLocationListByLevel(lv);
        return r;
    }

}

