package xyz.wongs.drunkard.base.persistence.jpa.repository.kit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.data.domain.Sort.Direction;
import xyz.wongs.drunkard.base.constant.Constant;

/**
 * @ClassName SimpleSortBuilder
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/17 16:43
 * @Version 1.0.0
*/
public class SimpleSortBuilder {

	/**
	 *
	 * @Title: generateSort
	 * @Description: 调用的时候使用SimpleSortBuilder.generateSort("name","xh_d");表示先以name升序，之后以xh降序
	 * @param fields
	 * @return  Sort
	 */
	public static Sort generateSort(String... fields) {
		List<Order> orders = new ArrayList<Order>();
		for(String f:fields) {
			orders.add(generateOrder(f));
		}
		return Sort.by(orders);
	}

	/**
	 * @Description 默认是降序，xx_dxxx，分割出来 xx 以及 dxx开头，其中第一个是 条件，第二个dxx是升降序
	 * @param direction
	 * @param f
	 * @return org.springframework.data.domain.Sort.Order
	 * @throws
	 * @date 20/12/17 17:23
	 */
	private static Order generateOrder(String f) {
		int lg = 2;
		Order order = null;
		String[] ff = f.split(Constant.UNDERSCORE);
		if(ff.length>=lg) {
			if(ff[1].equals(Constant.PREIX_SORT)) {
				order = new Order(Direction.DESC,ff[0]);
			} else {
				order = new Order(Direction.ASC,ff[0]);
			}
			return order;
		}
		// 默认是降序
		order = new Order(Direction.DESC,f);
		return order;
	}
}
