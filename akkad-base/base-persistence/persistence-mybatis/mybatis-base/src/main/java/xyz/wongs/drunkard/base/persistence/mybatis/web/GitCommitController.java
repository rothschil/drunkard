package xyz.wongs.drunkard.base.persistence.mybatis.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

/**
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/8/2 14:13
 * @Version 1.0.0
*/
@Controller
public class GitCommitController {

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-10:54
     * @Param
     * @return String
     **/
    @RequestMapping("/git/commit/info")
    @ResponseBody
    public String showGitCommitInfo() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("git", defaultIfNull(null, Locale.getDefault()));
        Map<String, String> map = new TreeMap<>();
        Enumeration<String> keysEnumeration = resourceBundle.getKeys();

        while (keysEnumeration.hasMoreElements()) {
            String key = keysEnumeration.nextElement();
            map.put(key, resourceBundle.getString(key));
        }
        return JSON.toJSONString(map, SerializerFeature.PrettyFormat);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-10:55
     * @Param
     * @return String
     **/
    @RequestMapping("/git/commit/id")
    @ResponseBody
    public String showGitCommitId() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("git", defaultIfNull(null, Locale.getDefault()));
        return resourceBundle.getString("git.commit.id");
    }

}