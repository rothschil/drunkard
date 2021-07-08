package xyz.wongs.drunkard.base.persistence.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbstractPo;

import javax.persistence.criteria.Predicate;
import java.io.Serializable;
import java.util.List;

/**
 * <p>抽象DAO层基类 提供一些简便方法<br/>
 * 想要使用该接口需要在spring配置文件的jpa:repositories中添加
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/8 - 13:59
 * @Version 1.0.0
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractPo, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 根据主键删除
     * @param ids 主键数组
     */
    void delete(ID[] ids);

    /** 根据SQL，查询结果，获取结果列表
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param sql
     * @return List
     **/
    List listBySql(String sql);

    /** 根据HQL，查询结果，获取结果列表
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param hql HQL语句
     **/
    List listByHql(String hql);

    /** 根据SQL语句获取目标
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param sql
     * @return Object
     **/
    Object getTarget(String sql);

    /** 按照SQL执行修改命令
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param sql 原生SQL语句
     * @Param args 参数
     **/
    void updateBySql(String sql,Object...args);

    /** 按照HQL执行修改命令
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param hql HQL语句
     * @Param args 参数
     **/
    void updateByHql(String hql,Object...args);

    /** 根据实体信息查询
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 利用实体结合 Specification 默认设置进行分页
     * @Date 2021/7/8-14:35
     * @Param t 实体类
     * @param pageable 分页信息
     * @return Page<T>
     **/
    Page find(T t, Pageable pageable);

    /** 根据实体信息查询
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 利用Specification 默认设置进行分页
     * @Date 2021/7/8-14:35
     * @Param spec  条件
     * @param pageable 分页信息
     * @return Page<T>
     **/
    Page find(Specification<T> spec, Pageable pageable);

    /**
     * 按照分页方式查询
     * @Description
     * @param t 非空
     * @param pageable 非空
     * @param list 多种查询条件,可以自定义实现，拓展为动态查询，可以为空，为空时候，自动从实体的属性中获取
     * @return org.springframework.data.domain.Page<T>
     * @date 20/12/22 16:25
     */
    Page findByCriteriaQuery(T t, Pageable pageable,List<Predicate> list);

    /** 以SQL方式，执行批量插入
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param sql 原生SQL语句
     * @return int
     **/
    int batchInsert(String sql);

    /** 以list方式，执行批量插入
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:42
     * @Param list 实体集合
     * @return int
     **/
    int batchInsert(List<T> list);

}
