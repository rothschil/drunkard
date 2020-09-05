package xyz.wongs.drunkard.war3.web.zonecode.task;


import xyz.wongs.drunkard.war3.web.domain.area.entity.Location;

public interface ProcessService {


	/** 解析并初始化区县
	 * @Description
	 * @param url
	 * @param location
	 * @return
	 * @throws
	 * @date 2020/9/5 10:24
	*/
	boolean initLevelTwo(String url, Location location);
	/**
	 *
	 * @Title: htmlParser
	 * @Description: 解析HTML
	 * @return: void
	 */
	void initLevelOne(String url, Location location);

	void thridLevelResolve(String url, Location location);
	void thridLevelResolve(String url, Location location, String flag);


	/**
	 * 初始化省、直辖区、自治区
	 * @method      intiRootUrl
	 * @author      WCNGS@QQ.COM
	 * @version
	 * @see
	 * @param url
	 * @return      void
	 * @exception
	 * @date        2018/6/30 23:29
	 */
	boolean intiRootUrl(String url);


	void getLocationThrid();

}
