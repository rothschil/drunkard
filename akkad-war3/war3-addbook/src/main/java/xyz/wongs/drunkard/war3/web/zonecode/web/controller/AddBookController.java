package xyz.wongs.drunkard.war3.web.zonecode.web.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.wongs.drunkard.domain.addbook.service.RegUserService;

/**
 * @ClassName AddBookController
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 13:32
 * @Version 1.0.0
*/
@Api(value="AddBookController")
@RestController
@RequestMapping(value = "/addBook")
public class AddBookController {

    @Autowired
    @Qualifier("regUserService")
    private RegUserService regUserService;


    @ApiOperation(value = "获取用户下所有朋友通讯录")
    @RequestMapping(value="/getAddBook",method= RequestMethod.POST)
    public void getAddBookBy(){

    }
}

