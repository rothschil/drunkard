package xyz.wongs.drunkard.base.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.common.text.Convert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName ServletUtils
 * @Description 客户端工具类
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/6/19 17:21
 * @Version 1.0.0
*/
public class ServletUtils {

    /**
     * 定义移动端请求的所有可能类型
     */
    private final static String[] agent = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser" };

    /**
     * 判断User-Agent 是不是来自于手机
     */
    public static boolean checkAgentIsMobile(String ua)
    {
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;")))
        {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh"))
            {
                for (String item : agent)
                {
                    if (ua.contains(item))
                    {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue) {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name) {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType(Constant.APPLICATION_JSON);
            response.setCharacterEncoding(Constant.UTF8);
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     *
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String indexOf = "XMLHttpRequest";
        String accept = request.getHeader(Constant.REQ_HEADER_ACCEPT);
        if (accept != null && accept.indexOf(Constant.APPLICATION_JSON) != -1) {
            return true;
        }

        String xRequestedWith = request.getHeader(Constant.REQ_HEADER);
        if (xRequestedWith != null && xRequestedWith.indexOf(indexOf) != -1) {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, Constant.SUFFIX_JSON, Constant.SUFFIX_XML)) {
            return true;
        }

        String ajax = request.getParameter(Constant.REQ_HEADER_AJAX);
        if (StringUtils.inStringIgnoreCase(ajax, Constant.SUFFIX_JSON.substring(1), Constant.SUFFIX_XML.substring(1))) {
            return true;
        }
        return false;
    }
}
