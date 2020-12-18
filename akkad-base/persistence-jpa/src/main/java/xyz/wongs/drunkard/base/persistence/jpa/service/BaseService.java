package xyz.wongs.drunkard.base.persistence.jpa.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.wongs.drunkard.base.entity.AbstractEntity;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbsEntity;
import xyz.wongs.drunkard.base.utils.Reflections;
import xyz.wongs.drunkard.base.utils.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: BaseService
 * @Description: Service基类
 * @author WCNGS
 * @date 2017年4月24日
 *
 */
public abstract class BaseService<T extends AbsEntity<?>, ID extends Serializable> {


    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected JpaRepository<T, ID> jpaRepository;




    public BaseService() {
    }

    public abstract void setJpaRepository(JpaRepository<T, ID> jpaRepository);

    public boolean retBoolFindByExample(T t){
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }
        Example<T> example = Example.of(t,matcher);
        if(jpaRepository.findAll(example).size()>0){
            return true;
        }
        return false;
    }

    public boolean retBoolSave(T t ){
        try{
            this.save(t);
            return true;
        }catch (RuntimeException re){
            return false;
        }
    }

    public List<T> findByProperty(T t,String propertyName, Object value) {
        try {
            Class<?> cls = t.getClass();
            Field[] fields = cls.getDeclaredFields();
            Method[] methods = cls.getDeclaredMethods();
            for (int i=0;i<fields.length;i++) {
                if(fields[i].getName().equals(propertyName)){
                    String fieldSetName = StringUtils.parSetName(fields[i].getName());
                    for (Method met : methods) {
                        if (fieldSetName.equals(met.getName())) {
                            met.invoke(t,value);
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return findByEntity(t);
    }

    public boolean retBoolDelete(T t) {
        try {
            this.delete(t);
            return true;
        } catch (RuntimeException re) {
            return false;
        }
    }

    public List<T> findByEntity(T t) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }

/*                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("password");*/

        Example<T> example = Example.of(t,matcher);
        return jpaRepository.findAll(example);
    }





    public Page<T> findPageByEntity(int page, int size, T t) {
        size=size==0?10:size;
        // TODO Auto-generated method stub
        Pageable pageable = PageRequest.of(page, size);
        ExampleMatcher matcher = ExampleMatcher.matching();
        List<String> fields = new ArrayList<String>();
        Reflections.getField(t,fields);
        for (String fld: fields){
            matcher.withMatcher(fld,ExampleMatcher.GenericPropertyMatchers.exact());
        }

/*                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address" ,ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("password");*/

        Example<T> example = Example.of(t,matcher);
        return jpaRepository.findAll(example,pageable);
    }

    /**
     * 保存单个实体
     *
     * @param t 实体
     * @return 返回保存的实体
     */
    public T save(T t) {
        return jpaRepository.save(t);
    }

    public T saveAndFlush(T t) {
        t = save(t);
        jpaRepository.flush();
        return t;
    }


    /**
     * 根据主键删除相应实体
     *
     * @param id 主键
     */
    public void delete(ID id) {
        jpaRepository.delete(findOne(id));
    }

    /**
     * 删除实体
     *
     * @param t 实体
     */
    public void delete(T t) {
        jpaRepository.delete(t);
    }



    /**
     * 按照主键查询
     *
     * @param id 主键
     * @return 返回id对应的实体
     */
    public T findOne(ID id) {
        return jpaRepository.getOne(id);
    }


    /**
     * 实体是否存在
     * @method      exists
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param id                id 主键
     * @return      boolean   存在 返回true，否则false
     * @exception
     * @date        2018/7/3 22:08
     */
    public boolean exists(ID id) {
        return findOne(id)==null?true:false;
    }


    /**
     * 统计实体总数
     * @method      count
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param
     * @return      long
     * @exception
     * @date        2018/7/3 22:07
     */
    public long count() {
        return jpaRepository.count();
    }


    /**
     * 查询所有实体
     * @method      findAll
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param
     * @return      java.utils.List<T>
     * @exception
     * @date        2018/7/3 22:07
     */
    public List<T> findAll() {
        return jpaRepository.findAll();
    }

    /**
     * 按照顺序查询所有实体
     * @method      findAll
     * @author      WCNGS@QQ.COM
     * @version
     * @see
     * @param sort
     * @return      java.utils.List<T>
     * @exception
     * @date        2018/7/3 22:06
     */
    public List<T> findAll(Sort sort) {
        return jpaRepository.findAll(sort);
    }


    /**
     * 分页及排序查询实体
     *
     * @param pageable 分页及排序数据
     * @return
     */
    public Page<T> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    public Page<T> findEntityNoCriteria(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return findAll(pageable);
    }

//    /**
//     *
//     * @param clazz
//     * @param pkName
//     * @return
//     */
//    public static Object getPkValueByName(Object clazz, String pkName) {
//        try {
//            String firstLetter = pkName.substring(0, 1).toUpperCase();
//            String getter = "get" + firstLetter + pkName.substring(1);
//            Method method = clazz.getClass().getMethod(getter, new Class[]{});
//            Object value = method.invoke(clazz, new Object[]{});
//        } catch (Exception e) {
//            return null;
//        }
//    }


//    public Page<T> findCriteria(Integer page, Integer size,final AbstractEntity<?> ae) {
//        Pageable pageable = new PageRequest(page, size);
//
////        Sort sort = new Sort(Sort.Direction.DESC, "id");
//
//        return findAll(pageable);
//    }

//    /***
//     *
//     * @Title: findEntityCriteria
//     * @Description: 根据对象 ，的查询分页
//     * @param page zero-based page index.
//     * @param size the size of the page to be returned.
//     * @param t 实体对象
//     * @return  Page<T>
//     */
//    public abstract Page<T> findEntityCriteria(Integer page, Integer size, T t);
//    {
//
//    	Pageable pageable = new PageRequest(page, size);
//		Page<T> lePage = null;
//		try {
//			Example<T> example = Example.of(t);
//			lePage = jpaRepository.findAll(example, pageable);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return lePage;
//    }


}
