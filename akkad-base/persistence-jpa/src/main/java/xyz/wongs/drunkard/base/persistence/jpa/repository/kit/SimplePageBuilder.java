package xyz.wongs.drunkard.base.persistence.jpa.repository.kit;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import xyz.wongs.drunkard.base.constant.Constant;

/**
 * @ClassName SimplePageBuilder
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/17 16:47
 * @Version 1.0.0
*/
public class SimplePageBuilder {

	/**
	 * 以常量的形式存储，在实际的运用中应该从properties文件中取得，思路都一样
	 */
	public static final int size = 15;

	/**
	 *
	 * @Title: generate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param size
	 * @param sort
	 * @return  Pageable
	 */
	public static Pageable generate(int page,int size,Sort sort) {
		if(sort==null) {
			sort = Sort.by(Constant.DEFAULT_SORT);
		}
		return PageRequest.of(page, size,sort);
	}

	/**
	 *
	 * @Title: generate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @return  Pageable
	 */
	public static Pageable generate(int page) {
		return generate(page,size,null);
	}

	/**
	 *
	 * @Title: generate
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param page
	 * @param sort
	 * @return  Pageable
	 */
	public static Pageable generate(int page,Sort sort) {
		return generate(page,size,sort);
	}
}
