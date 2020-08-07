package xyz.wongs.drunkard.war3.web.addbook.controller;


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;
import xyz.wongs.drunkard.base.message.enums.ResponseCode;
import xyz.wongs.drunkard.base.message.response.ResponseResult;
import xyz.wongs.drunkard.base.web.BaseController;
import xyz.wongs.drunkard.domain.addbook.entity.RegUser;
import xyz.wongs.drunkard.domain.addbook.service.RegUserService;
import xyz.wongs.drunkard.war3.web.addbook.vo.RegUserVo;

import java.util.List;

/**
 * @ClassName AddBookController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
*/
@Slf4j
@Api(value="AddBookController")
@RestController
@RequestMapping(value = "/addBook")
public class AddBookController extends BaseController {

    @Autowired
    private RegUserService regUserService;

    @GetMapping("/")
    public String index(){
        return "wongs";
    }

    /**
     * @Description
     * @param regUserVo
     * @return void
     * @throws
     * @date 2020/8/4 19:14
     */
    @ApiOperation(value = "获取用户下所有朋友通讯录")
    @PostMapping("/getAddBook")
    public ResponseResult<List> getAddBookBy(@RequestBody RegUserVo regUserVo){

        ResponseResult<List> result = getResponseResult();
        RegUser regUser = RegUser.builder().build();
        List<RegUser> regUsers = null;
        try {
            BeanCopier copier = BeanCopier.create(RegUserVo.class, RegUser.class,false);
            copier.copy(regUserVo, regUser, null);

            regUsers = regUserService.selectByRegUser(regUser);
            if(!regUsers.isEmpty()){
                result.setData(regUsers);
            }
        } catch (Exception e) {
            log.error("Request Params Is {} ,But No Data", JSON.toJSON(regUserVo));
            result.setCode(ResponseCode.ATTR_COPY_ERROR.getCode());
            result.setMsg(ResponseCode.ATTR_COPY_ERROR.getMsg());
        }
        return result;
    }
}

