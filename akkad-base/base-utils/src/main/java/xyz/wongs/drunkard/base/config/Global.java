package xyz.wongs.drunkard.base.config;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.PropertiesLoader;
import xyz.wongs.drunkard.base.utils.StringUtils;

import java.util.Map;


/**
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description 全局配置类 .在第一次调用的时候实例化自己
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/7 - 20:12
 * @Version 1.0.0
 */
@Component
public class Global {

	/**
	 * 保存全局属性值
	 */
	private static final Map<String, String> GLOBAL_ATTR_MAP = Maps.newHashMap();

	/**
	 * 属性文件加载对象
	 */
	private static final PropertiesLoader PROPERTIES_LOADER = new PropertiesLoader("config.properties");

	/**
	 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
	 * @Description 获取配置
	 * @Date 2021/7/7-20:16
	 * @Param key
	 * @return String
	 **/
	public static String getConfig(String key) {
		String value = GLOBAL_ATTR_MAP.get(key);
		if (value == null){
			value = PROPERTIES_LOADER.getProperty(key);
			GLOBAL_ATTR_MAP.put(key, value != null ? value : StringUtils.EMPTY);
		}
		return value;
	}
}
