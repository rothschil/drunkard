package xyz.wongs.drunkard.framework.shiro.web.filter;


import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.wongs.drunkard.base.utils.MessageUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;
import xyz.wongs.drunkard.base.utils.bean.SpringUtils;
import xyz.wongs.drunkard.common.utils.ShiroUtils;
import xyz.wongs.drunkard.framework.manager.AsyncManager;
import xyz.wongs.drunkard.framework.manager.factory.AsyncFactory;
import xyz.wongs.drunkard.war3.constant.Constants;
import xyz.wongs.drunkard.war3.core.domain.SysUser;
import xyz.wongs.drunkard.war3.core.service.ISysUserOnlineService;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 退出过滤器
 * 
 * @author ruoyi
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter
{
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);

    /**
     * 退出后重定向的地址
     */
    private String loginUrl;

    public String getLoginUrl()
    {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception
    {
        try
        {
            Subject subject = getSubject(request, response);
            String redirectUrl = getRedirectUrl(request, response, subject);
            try
            {
                SysUser user = ShiroUtils.getSysUser();
                if (StringUtils.isNotNull(user))
                {
                    String loginName = user.getLoginName();
                    // 记录用户退出日志
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGOUT, MessageUtils.message("user.logout.success")));
                    // 清理缓存
                    SpringUtils.getBean(ISysUserOnlineService.class).removeUserCache(loginName, ShiroUtils.getSessionId());
                }
                // 退出登录
                subject.logout();
            }
            catch (SessionException ise)
            {
                log.error("logout fail.", ise);
            }
            issueRedirect(request, response, redirectUrl);
        }
        catch (Exception e)
        {
            log.error("Encountered session exception during logout.  This can generally safely be ignored.", e);
        }
        return false;
    }

    /**
     * 退出跳转URL
     */
    @Override
    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject)
    {
        String url = getLoginUrl();
        if (StringUtils.isNotEmpty(url))
        {
            return url;
        }
        return super.getRedirectUrl(request, response, subject);
    }
}
