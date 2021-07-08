package xyz.wongs.drunkard.base.persistence.jpa.service;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbstractPo;
import xyz.wongs.drunkard.base.utils.Reflections;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Service基类
 * @Description
 * @author WCNGS@QQ.COM
 * @Github <a>https://github.com/rothschil</a>
 * @date 20/12/18 11:05
 * @Version 1.0.0
*/
@Transactional(readOnly = true,rollbackFor = Exception.class)
public abstract class BaseService<T extends AbstractPo<?>, ID extends Serializable> {

    protected JpaRepository<T, ID> jpaRepository;

    public BaseService() {}
    /**
     * 重要
     * **/
    public abstract void setJpaRepository(JpaRepository<T, ID> jpaRepository);

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:16
     * @Param t 实体信息
     * @return List
     **/
    public List findByEntity(T t) {
        return jpaRepository.findAll(getExample(t));
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:16
     * @Param t 实体信息
     * @Param page 页
     * @Param size 每页数量
     * @return Page
     **/
    public Page findPageByEntity(int page, int size, T t) {
        size=size==0?10:size;
        return jpaRepository.findAll(getExample(t),PageRequest.of(page, size));
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:16
     * @Param t 实体信息
     * @return Example
     **/
    private Example getExample(T t){
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }
        return Example.of(t,matcher);
    }

    /** 保存单个实体
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param t 实体
     * @return T  返回id对应的实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public T save(T t) {
        return jpaRepository.save(t);
    }

    /** 保存
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param t 实体
     * @return T  返回id对应的实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public T saveAndFlush(T t) {
        t = save(t);
        jpaRepository.flush();
        return t;
    }

    /** 根据主键删除相应实体
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param id 主键
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(ID id) {
        jpaRepository.delete(findOne(id));
    }

    /** 删除实体
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param t              实体
     **/
    @Transactional(rollbackFor = Exception.class)
    public void delete(T t) {
        jpaRepository.delete(t);
    }

    /** 按照主键查询
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param id                id 主键
     * @return T  返回id对应的实体
     **/
    public T findOne(ID id) {
        return jpaRepository.getOne(id);
    }

    /** 实体是否存在
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param id                id 主键
     * @return boolean   存在 返回true，否则false
     **/
    public boolean exists(ID id) {
        return findOne(id)==null;
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 统计实体总数
     * @Date 2018/7/3 22:07
     * @return List<T>
     **/
    public long count() {
        return jpaRepository.count();
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 查询所有实体
     * @Date 2018/7/3 22:07
     * @return List<T>
     **/
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /** 按照顺序查询所有实体
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description //TODO
     * @Date 2021/7/8-14:02
     * @Param sort
     * @return List<T>
     **/
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 分页及排序查询实体
     * @Date 2021/7/8-14:02
     * @Param pageable  分页及排序数据
     * @return Page<T>
     **/
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    /**
     * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
     * @Description 分页
     * @Date 2021/7/8-14:02
     * @Param page  页数
     * @Param size  每页数量
     * @return Page<T>
     **/
    public Page<T> findEntityNoCriteria(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAll(pageable);
    }

}
