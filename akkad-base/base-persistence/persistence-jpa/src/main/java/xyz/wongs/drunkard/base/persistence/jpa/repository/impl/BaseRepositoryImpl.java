package xyz.wongs.drunkard.base.persistence.jpa.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbsEntity;
import xyz.wongs.drunkard.base.persistence.jpa.repository.BaseRepository;
import xyz.wongs.drunkard.base.persistence.jpa.util.MethodUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BaseRepositoryImpl<T extends AbsEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;
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
        for (Object arg : args) {
            query.setParameter(++i, arg);
        }
        query.executeUpdate();
    }

    @Override
    public void updateByHql(String hql, Object... args) {
        Query query = entityManager.createQuery(hql);
        int i = 0;
        for (Object arg : args) {
            query.setParameter(++i, arg);
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

    @Override
    public int batchInsert(List<T> list) {
        int i = 0;
        for (T t : list) {
            i++;
            entityManager.persist(t);
            if (i % 1000 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        return 0;
    }

    /** 利用Specification 默认设置进行分页
     * @Description
     * @param spec
     * @param pageable
     * @return org.springframework.data.domain.Page<T>
     * @throws
     * @date 20/12/22 16:26
     */
    @Override
    public Page<T> find(Specification<T> spec, Pageable pageable) {
        return super.findAll(spec, pageable);
    }

    /** 利用实体结合 Specification 默认设置进行分页
     * @Description
     * @param t
     * @param pageable
     * @return org.springframework.data.domain.Page<T>
     * @throws
     * @date 20/12/22 16:26
     */
    @Override
    public Page<T> find(T t, Pageable pageable) {
        Specification spec = MethodUtil.getSpecification(t);
        return find(spec, pageable);
    }


    /**
     * @Description
     * @param t 非空
     * @param pageable 非空
     * @param list 多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
     * @return org.springframework.data.domain.Page<T>
     * @throws
     * @date 20/12/22 16:25
     */
    @Override
	public Page<T> findByCriteriaQuery(T t,Pageable pageable,List<Predicate> list) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery query = cb.createQuery();
		// Root 定义查询的From子句中能出现的类型
		Root<T> root = query.from(t.getClass());

        // 多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
        if(list.isEmpty()){
            list = MethodUtil.getFieldValue(t,root,cb);
        }

        List<Expression<?>> grouping = new ArrayList<Expression<?>>();
        grouping.add(root.get("id"));
        grouping.add(root.get("flag"));
        grouping.add(root.get("localCode"));
        grouping.add(root.get("localName"));
        grouping.add(root.get("lv"));
        grouping.add(root.get("supLocalCode"));
        grouping.add(root.get("url"));
        query.multiselect(
                root.get("id"),
                root.get("flag"),
                root.get("localCode"),
                root.get("localName"),
                root.get("lv"),
                root.get("supLocalCode"),
                root.get("url"),
                cb.sum(root.get("id")));


		query.where(list.toArray(new Predicate[list.size()]));
        query.groupBy(grouping);

        TypedQuery<T> typedQuery = entityManager.createQuery(query);
//        typedQuery.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
//        typedQuery.setMaxResults(pageable.getPageSize());


        Long total = getTotal(query);
		List<T> content = total > typedQuery.getFirstResult() ? typedQuery.getResultList() : Collections.<T>emptyList();
		return new PageImpl<T>(content, pageable, total);
    }

    /**
     * @Description
     * @param query
     * @return long
     * @throws
     * @date 20/12/22 16:17
     */
    private Long getTotal(CriteriaQuery query){
        Long total = 0L;
        List<T> totals = entityManager.createQuery(query).getResultList();
        if(!totals.isEmpty()){
            Integer size = totals.size();
            total = size.longValue();
        }
        return total;
    }
}
