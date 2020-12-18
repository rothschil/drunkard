package xyz.wongs.drunkard.base.persistence.jpa.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * <p>Title:SimpleSpecification </p>
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
 * @date:   2017年8月5日 上午1:29:57  *
 * @param <T>
 * @since JDK 1.7
 */
public class SimpleSpecification<T> implements Specification<T> {

	/**
	 * 查询条件列表
	 */
	private List<SpecificationOperator> opers;



	public SimpleSpecification() {
	}



	public SimpleSpecification(List<SpecificationOperator> opers) {
		this.opers = opers;
	}



	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		int index = 0;
		//通过resultPre来组合多个条件
		Predicate resultPre = null;
		for(SpecificationOperator sfo:opers){
			if(index++==0){
				resultPre = generatePredicate(root,criteriaBuilder,sfo);
				continue;
			}
			Predicate pre = generatePredicate(root,criteriaBuilder,sfo);
			if(pre==null) continue;

			if("and".equalsIgnoreCase(sfo.getJoin())) {
				resultPre = criteriaBuilder.and(resultPre,pre);
			} else if("or".equalsIgnoreCase(sfo.getJoin())) {
				resultPre = criteriaBuilder.or(resultPre,pre);
			}
		}

		return resultPre;
	}

	private Predicate generatePredicate(Root<T> root,CriteriaBuilder criteriaBuilder, SpecificationOperator op) {
        /*
        * 根据不同的操作符返回特定的查询*/
        if("=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.equal(root.get(op.getKey()),op.getValue());
        } else if(">=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.ge(root.get(op.getKey()), (Number)op.getValue());
        } else if("<=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.le(root.get(op.getKey()),(Number)op.getValue());
        } else if(">".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.gt(root.get(op.getKey()),(Number)op.getValue());
        } else if("<".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.lt(root.get(op.getKey()),(Number)op.getValue());
        } else if(":".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),"%"+op.getValue()+"%");
        } else if("l:".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),op.getValue()+"%");
        } else if(":l".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.like(root.get(op.getKey()),"%"+op.getValue());
        } else if("null".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.isNull(root.get(op.getKey()));
        } else if("!null".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.isNotNull(root.get(op.getKey()));
        } else if("!=".equalsIgnoreCase(op.getOper())) {
            return criteriaBuilder.notEqual(root.get(op.getKey()),op.getValue());
        }
        return null;
    }

}
