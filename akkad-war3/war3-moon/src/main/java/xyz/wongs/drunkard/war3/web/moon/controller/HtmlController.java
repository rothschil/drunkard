package xyz.wongs.drunkard.war3.web.moon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.war3.domain.entity.Location;
import xyz.wongs.drunkard.war3.domain.service.LocationService;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LocationController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/11/18 11:04
 * @Version 1.0.0
*/
@Validated
@ResponseResult
@Controller
public class HtmlController {

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
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String idex() {
        return "redirect:/list";
    }

    /**
     *
     * @Title: getLocationListByLevel
     * @Description: 请求参数在URL中，需要在 @ApiImplicitParam 中加上 "paramType="path""
     * @param lv
     * @return  List<LocationEntity>
     */
    @ApplicationLog
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(value = "pageNum", defaultValue = "0") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Location location = new Location();
        location.setLv(0);
        Page<Location> pages = locationService.getList(location,pageNum,pageSize);
        model.addAttribute("pages", pages);
        return "location";
    }
}

