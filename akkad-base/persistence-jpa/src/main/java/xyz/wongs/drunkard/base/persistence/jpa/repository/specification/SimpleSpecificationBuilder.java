package xyz.wongs.drunkard.base.persistence.jpa.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

/**
 *
 * <p>Title:SimpleSpecificationBuilder </p>
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
 * @date:   2017年8月5日 上午1:27:55  *
 * @param <T>
 * @since JDK 1.7
 */
@SuppressWarnings({"rawtypes"})
public class SimpleSpecificationBuilder<T> {

    /**
     * 条件列表
     */
    private List<SpecificationOperator> opers;

    /**
     * 构造函数，初始化的条件是and
     */
    public SimpleSpecificationBuilder(String key,String oper,Object value) {
        SpecificationOperator so = new SpecificationOperator();
        so.setJoin("and");
        so.setKey(key);
        so.setOper(oper);
        so.setValue(value);
        opers = new ArrayList<SpecificationOperator>();
        opers.add(so);
    }

    public SimpleSpecificationBuilder() {
        opers = new ArrayList<SpecificationOperator>();
    }

    /**
     * 完成条件的添加
     * @return
     */
	public SimpleSpecificationBuilder add(String key,String oper,Object value,String join) {
        SpecificationOperator so = new SpecificationOperator();
        so.setKey(key);
        so.setValue(value);
        so.setOper(oper);
        so.setJoin(join);
        opers.add(so);
        return this;
    }


    /**
     * 添加or条件的重载
     * @return this，方便后续的链式调用
     */
    public SimpleSpecificationBuilder addOr(String key,String oper,Object value) {
        return this.add(key,oper,value,"or");
    }

    /**
     * 添加and的条件
     * @return
     */
    public SimpleSpecificationBuilder add(String key,String oper,Object value) {
        return this.add(key,oper,value,"and");
    }


    public Specification generateSpecification() {
        Specification<T> specification = new SimpleSpecification<T>(opers);
        return specification;
    }
}