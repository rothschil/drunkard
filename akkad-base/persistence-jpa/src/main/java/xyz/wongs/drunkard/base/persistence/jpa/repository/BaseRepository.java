package xyz.wongs.drunkard.base.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


/**
 * <p>抽象DAO层基类 提供一些简便方法<br/>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * @author WCNGS
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
@Transactional(readOnly=true,rollbackFor = Exception.class)
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 根据主键删除
     *
     * @param ids
     */
    void delete(ID[] ids);

    /**
     *
     * @param sql
     * @return
     */
    List<Object[]> listBySQL(String sql);

    public Long getTargetId(String sql);

    /**
     *
     * @param sql
     * @param args
     */
    @Transactional(rollbackFor = Exception.class)
    void updateBySql(String sql,Object...args);


    @Transactional(rollbackFor = Exception.class)
    void updateByHql(String hql,Object...args);

    Page<T> findCriteria(Specification<T> spec, Pageable pageable);

    int batchInsert(String sql);

}
