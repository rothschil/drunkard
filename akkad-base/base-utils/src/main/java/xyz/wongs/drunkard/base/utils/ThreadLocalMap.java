package xyz.wongs.drunkard.base.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * @ClassName ThreadLocalMap
 * @Description 
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 2020/9/9 14:50
 * @Version 1.0.0
*/
@Slf4j
public class ThreadLocalMap {
	protected final static ThreadLocal<Map<Object,Object>> THREAD_CONTEXT = new MapThreadLocal();

	private ThreadLocalMap(){};

	public static void put(Object key,Object value){
		//写入前remove之前
		getContextMap().remove(key);

		getContextMap().put(key,value);
	}

	public static Object remove(Object key){
		return getContextMap().remove(key);
	}

	public static Object get(Object key){
		return getContextMap().get(key);
	}

	public static boolean containsKey(Object key){
		return getContextMap().containsKey(key);
	}

	private static class MapThreadLocal extends ThreadLocal<Map<Object,Object>> {
		@Override
		protected Map<Object,Object> initialValue() {
			return new HashMap<Object,Object>() {

				private static final long serialVersionUID = 3637958959138295593L;

				@Override
				public Object put(Object key, Object value) {
					if (log.isDebugEnabled()) {
						if (containsKey(key)) {
							log.debug("Overwritten attribute to thread context: " + key
									+ " = " + value);
						} else {
							log.debug("Added attribute to thread context: " + key + " = "
									+ value);
						}
					}

					return super.put(key, value);
				}
			};
		}
	}

	/**
	 * 取得thread context Map的实例。
	 *
	 * @return thread context Map的实例
	 */
	protected static Map<Object,Object> getContextMap() {
		return (Map<Object,Object>) THREAD_CONTEXT.get();
	}


	/**
	 * 清理线程所有被hold住的对象。以便重用！
	 */

	public static void reset(){
		getContextMap().clear();
	}
}