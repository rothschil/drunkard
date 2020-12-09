package xyz.wongs.drunkard.war3.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.wongs.drunkard.common.core.controller.BaseController;
import xyz.wongs.drunkard.common.core.domain.AjaxResult;
import xyz.wongs.drunkard.framework.shiro.service.SysRegisterService;
import xyz.wongs.drunkard.war3.core.domain.SysUser;
import xyz.wongs.drunkard.war3.core.service.ISysConfigService;


/**
 * 注册验证
 * 
 * @author ruoyi
 */
@Controller
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @GetMapping("/register")
    public String register()
    {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public AjaxResult ajaxRegister(SysUser user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
