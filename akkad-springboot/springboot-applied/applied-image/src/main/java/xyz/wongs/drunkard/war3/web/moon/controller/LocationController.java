//package xyz.wongs.drunkard.war3.web.moon.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
//import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
//import xyz.wongs.drunkard.base.message.exception.DrunkardException;
//import xyz.wongs.drunkard.war3.domain.entity.Location;
//import xyz.wongs.drunkard.war3.domain.service.LocationService;
//
//import javax.validation.constraints.NotNull;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @ClassName LocationController
// * @Description
// * @author WCNGS@QQ.COM
// * @Github <a>https://github.com/rothschil</a>
// * @date 20/11/18 11:04
// * @Version 1.0.0
//*/
//@Validated
//@ResponseResult
//@RestController
//@RequestMapping(value = "/areas")
//public class LocationController{
//
//    @Autowired
//    @Qualifier("locationService")
//    private LocationService locationService;
//
//    /**
//     *
//     * @Title: getLocationListByLevel
//     * @Description: 请求参数在URL中，需要在 @ApiImplicitParam 中加上 "paramType="path""
//     * @param lv
//     * @return  List<LocationEntity>
//     */
//    @RequestMapping(value = "/{lv}", method = RequestMethod.GET)
//    public List<Location> getLocationListByLevel(@PathVariable(value = "lv") Integer lv) {
//        return locationService.getLocationListByLevel(lv);
//    }
//
//    @ApplicationLog
//    @GetMapping("/test")
//    public Map<String, Object> test() {
//        HashMap<String, Object> data = new HashMap<>(3);
//        data.put("info", "测试成功");
//        return data;
//    }
//
//    @ApplicationLog
//    @GetMapping("/fail")
//    public Integer error() {
//        // 查询结果数
//        int res = 0;
//        if( res == 0 ) {
//            throw new DrunkardException("没有数据");
//        }
//        return res;
//    }
//
//    @ApplicationLog
//    @GetMapping("/vali")
//    public Map<String, Object> testValidator(@NotNull(message = "userId不能为空") Integer userId) {
//        HashMap<String, Object> data = new HashMap<>(3);
//        data.put("info", "测试成功");
//        return data;
//    }
//
//
//}
//
