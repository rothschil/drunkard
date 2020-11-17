package xyz.wongs.drunkard.war3.web.zonecode.task;

import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;
import java.util.List;

/**
 * @ClassName ProcessService
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 15:14
 * @Version 1.0.0
*/
public interface ProcessService {


	/** 初始化省、直辖区、自治区
	 * @Description
	 * @param url
	 * @return boolean
	 * @throws 
	 * @date 2020/9/9 15:16
	*/
	boolean intiRootUrl(String url);

	/** 初始化省会城市等
	 * @Description
	 * @param url
	 * @param parentLocation
	 * @return void
	 * @throws
	 * @date 2020/9/9 15:16
	 */
	void initLevelOne(String url, Location parentLocation);

	/** 解析并初始化区县
	 * @Description
	 * @param url
	 * @param location
	 * @return
	 * @throws
	 * @date 2020/9/5 10:24
	*/
	boolean initLevelTwo(String url, Location location);

	/** 解析并初始化区县
	 * @Description
	 * @param url
	 * @param location
	 * @param flag
	 * @return
	 * @throws
	 * @date 2020/9/5 10:24
	 */
	void initLevelThrid(String url, Location location, String flag);

	/** 解析并初始化乡\镇\街道
	 * @Description
	 * @param url
	 * @param location
	 * @return
	 * @throws
	 * @date 2020/9/5 10:24
	 */
	void initLevelThrid(String url, Location location);

//	/** 解析并初始化 自然村\社区
//	 * @Description
//	 * @param url
//	 * @param location
//	 * @param flag
//	 * @return
//	 * @throws
//	 * @date 2020/9/5 10:24
//	 */
//	void initLevelFour(String url, Location location,String flag);

	/** 解析并初始化 自然村\社区
	 * @Description
	 * @param url
	 * @param thridLevelLocas
	 * @return
	 * @throws
	 * @date 2020/9/5 10:24
	 */
	void initLevelFour(String url, List<Location> thridLevelLocas);

}
