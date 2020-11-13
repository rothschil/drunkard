package xyz.wongs.drunkard.war3.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.aop.annotion.ApplicationLog;
import xyz.wongs.drunkard.base.message.annoation.ResponseResult;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@ResponseResult
public class IndexController {

    /**
     * @Description
     * @param regUserVo
     * @return void
     * @throws
     * @date 2020/8/4 19:14
     */
    @GetMapping("/index")
    public String index(){
        log.error("=====");
        return "I AM INDEX";
    }

    @ApplicationLog
    @GetMapping("/test")
    public Map<String, Object> test() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("info", "测试成功");
        return data;
    }

    @ApplicationLog
    @GetMapping("/fail")
    public Integer error() {
        int res = 0; // 查询结果数
        if( res == 0 ) {
            throw new DrunkardException("没有数据");
        }
        return res;
    }
}
