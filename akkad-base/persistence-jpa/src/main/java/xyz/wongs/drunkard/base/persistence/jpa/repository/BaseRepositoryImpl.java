package xyz.wongs.drunkard.base.persistence.jpa.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import xyz.wongs.drunkard.base.constant.Constant;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked"})
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	private final EntityManager entityManager;

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager=em;
	}

	@Override
	public void delete(ID[] ids) {

	}

	@Override
	public Long getTargetId(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return Long.valueOf(query.getSingleResult().toString());
	}

	@Override
	public void updateBySql(String sql, Object... args) {
		Query query = entityManager.createNativeQuery(sql);
		int i = 0;
		for(Object arg:args) {
			query.setParameter(++i,arg);
		}
		query.executeUpdate();
	}

	@Override
	public void updateByHql(String hql, Object... args) {
		Query query = entityManager.createQuery(hql);
		int i = 0;
		for(Object arg:args) {
			query.setParameter(++i,arg);
		}
		query.executeUpdate();
	}

	@Override
	public List<Object[]> listBySQL(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}

	@Override
	public int batchInsert(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		return query.executeUpdate();
	}


	public Page<T> find(Class rootCls, CriteriaQuery<T> criteria, int pageNo, int pageSize) {

		//count
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery criteriaC = builder.createQuery();
		Root root = criteriaC.from(rootCls);
		criteriaC.select(builder.count(root));
		criteriaC.where(criteria.getRestriction());
		List<Long> totals = entityManager.createQuery(criteriaC).getResultList();
		Long total = 0L;
		for (Long element : totals) {
			total += element == null ? 0 : element;
		}
		//content
		TypedQuery<T> query = entityManager.createQuery(criteria);
		query.setFirstResult((pageNo - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<T> content = total > query.getFirstResult() ? query.getResultList() : Collections.<T> emptyList();
		Sort sort = Sort.by(Sort.Direction.DESC, Constant.DEFAULT_SORT);
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<T> pageRst = new PageImpl<T>(content, pageable, total);
		return pageRst;

	}

	@Override
	public Page<T> findCriteria(Specification<T> spec, Pageable pageable){
		  return super.findAll(spec,pageable);
	}

}
