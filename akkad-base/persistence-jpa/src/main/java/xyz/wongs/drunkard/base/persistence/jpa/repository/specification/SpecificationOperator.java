package xyz.wongs.drunkard.base.persistence.jpa.repository.specification;

/**
 *
 * Created by konghao on 2016/12/15.
 * 操作符类，这个类中存储了键值对和操作符号，另外存储了连接下一个条件的类型是and还是or
 * 创建时通过 id>=7,其中id就是key,>=就是oper操作符，7就是value
 * 特殊的自定义几个操作符(:表示like %v%，b:表示v%,:b表示%v)
 * <p>Title:SpecificationOperator </p>
 * <p>@Description: </p>
 * <p>Company: </p>
 * ----------------------------------------
 *	\\	 /\/\   /\|	|/\   /\/\	 //
 *	 \\^^  ^^^  ^^|_|^^  ^^^  ^^//
 *	  \\^   ^^^  ^/Ϡ\^   ^^^  ^//
 *	   \\^ ^    ^/___\^    ^ ^//
 *	    \\^ ^^ ^//   \\^ ^^ ^//
 *	     \\	^^/(/     \)\^^ //
 *	      \\^'//       \\'^//
 *	       .==.   खान          .==.
 * ----------------------------------------
 * @author: <a href="wcngs@qq.com">WCNGS</a>
 * @date:   2017年8月5日 上午1:28:08  *
 * @since JDK 1.7
 */
public class SpecificationOperator {
    /**
     * 操作符的key，如查询时的name,id之类
     */
    private String key;
    /**
     * 操作符的value，具体要查询的值
     */
    private Object value;
    /**
     * 操作符，自己定义的一组操作符，用来方便查询
     */
    private String oper;
    /**
     * 连接的方式：and或者or
     */
    private String join;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }
}
