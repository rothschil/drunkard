/**
 * Copyright &copy; 2012-2016 <a href="https://wongs.xyz">UECC</a> All rights reserved.
 */
package xyz.wongs.drunkard.base.utils;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;


/**
 *  ┏┓　　　┏┓
 *┏┛┻━━━┛┻┓
 *┃　　　　　　　┃ 　
 *┃　　　━　　　┃
 *┃　┳┛　┗┳　┃
 *┃　　　　　　　┃
 *┃　　　┻　　　┃
 *┃　　　　　　　┃
 *┗━┓　　　┏━┛
 *　　┃　　　┃神兽保佑
 *　　┃　　　┃代码无BUG！
 *　　┃　　　┗━━━┓
 *　　┃　　　　　　　┣┓
 *　　┃　　　　　　　┏┛
 *　　┗┓┓┏━┳┓┏┛
 *　　　┃┫┫　┃┫┫
 *　　　┗┻┛　┗┻┛
 * @ClassName IdGen
 * @Description 封装各种生成唯一性ID算法的工具类.
 * @author WCNGS@QQ.COM
 * @date 2019/2/27 18:08
 * @Version 1.0.0
*/
@Service
@Lazy(false)
public class IdGen {

	private static SecureRandom random = new SecureRandom();

	/**
	 * @Description 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 * @param
	 * @return java.lang.String
	 * @throws
	 * @date 20/12/2 10:34
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * @Description	使用SecureRandom随机生成Long.
	 * @param
	 * @return long
	 * @throws
	 * @date 20/12/2 10:34
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
}
