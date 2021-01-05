package xyz.wongs.drunkard.base.persistence.jpa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbsEntity;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WCNGS@QQ.CO
 * @version V1.0
 * @Title:
 * @Package spring-cloud xyz.wongs.tools.utils
 * @Description: TODO
 * @date 2018/7/4 20:54
 **/
@Slf4j
public class MethodUtil<T extends AbsEntity> {


    public static Specification getSpecification(AbsEntity t){
        return new Specification<AbsEntity>() {
            @Override
            public Predicate toPredicate(Root<AbsEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = MethodUtil.getFieldValue(t,root,cb);
                Predicate[] pre = new Predicate[list.size()];
                pre = list.toArray(pre);
                return query.where(pre).getRestriction();
            }
        };
    }

    /**
     *
     * if (Constant.BASIC_TYPE_INTEGER.equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(Integer.class), Integer.valueOf(value)));
         } else if("BigDecimal".equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(BigDecimal.class), new BigDecimal(value)));
         } else if("Long".equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(Long.class), Long.valueOf(value)));
         } else if("Date".equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(Date.class), DateUtils.parseDate(value)));
         } else if("int".equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(Integer.class), Integer.valueOf(value)));
         } else if("String".equals(fieldType)) {
         lp.add(cb.equal(root.get(fields[i].getName()).as(String.class), value));
         }
     * @Description
     * @param bean
     * @param root
     * @param cb
     * @param lp
     * @return void
     * @throws
     * @date 20/12/18 10:12
     */
    public static List<Predicate> getFieldValue(AbsEntity entity, Root<?> root, CriteriaBuilder cb) {
        List<Predicate> lp = new ArrayList<>();
        Class<?> cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Predicate predicate = null;
        for (Field field : fields) {
            try {
                //校验是否有GETTER、SETTER的方法
                if(!checkGetSetMethodAndAnnotation(methods,field)){
                    continue;
                }
                String fieldGetName = StringUtils.parGetName(field.getName());
                String fieldSetName = StringUtils.parSetName(field.getName());

                Method fieldSetMet = cls.getMethod(fieldGetName);

                Object o = fieldSetMet.invoke(entity);
                //Type conversion
                if (null == o) {
                    continue;
                }
                String fieldType = field.getType().getSimpleName();
                String value = o.toString();
                switch (fieldType) {
                    case Constant.BASIC_TYPE_INTEGER:
                        predicate = doInteger(cb, root, field, value);
                        break;
                    case Constant.BASIC_TYPE_BIGDECIMAL:
                        predicate = cb.equal(root.get(field.getName()).as(BigDecimal.class), new BigDecimal(value));
                        break;
                    case Constant.BASIC_TYPE_Long:
                        predicate = cb.equal(root.get(field.getName()).as(Long.class), Long.valueOf(value));
                        break;
                    case Constant.BASIC_TYPE_DATE:
                        predicate = cb.equal(root.get(field.getName()).as(Date.class), DateUtils.parseDate(value));
                        break;
                    case Constant.BASIC_TYPE_INT:
                        predicate = doInteger(cb, root, field, value);
                        break;
                    default:
                        predicate = cb.equal(root.get(field.getName()).as(String.class), value);
                        break;
                }
                lp.add(predicate);
            } catch (NoSuchMethodException e) {
                log.error(e.getMessage(), e.getCause());
                continue;
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e.getCause());
                continue;
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e.getCause());
                continue;
            }
        }
        return lp;
    }

    /** 检查Set Get方法以及忽略Transient 的注解
     * @Description
     * @param methods
     * @param field
     * @return boolean
     * @throws
     * @date 20/12/22 15:13
     */
    public static boolean checkGetSetMethodAndAnnotation(Method[] methods,Field field){
        boolean result = true;
        String fieldGetName = StringUtils.parGetName(field.getName());
        String fieldSetName = StringUtils.parSetName(field.getName());
        //校验是否有GETTER、SETTER的方法
        if (!StringUtils.checkGetMet(methods, fieldGetName) || !StringUtils.checkSetMet(methods, fieldSetName)) {
            result = false;
        } else {
            //检查 Transient 注解
            Annotation annotation = field.getAnnotation(javax.persistence.Transient.class);
            if(StringUtils.isNotNull(annotation)){
                result = false;
            }
        }
        return result;
    }

//    public static void getFieldValue(Object bean, Root<?> root, CriteriaBuilder cb, List<Predicate> lp){
//        try {
//            Class<?> cls = bean.getClass();
//            Field[] fields = cls.getDeclaredFields();
//            Method[] methods = cls.getDeclaredMethods();
//            Predicate predicate = null;
//
//            for (int i=0;i<fields.length;i++) {
//                String fieldType = fields[i].getType().getSimpleName();
//                String fieldGetName = StringUtils.parGetName(fields[i].getName());
//                String fieldSetName = StringUtils.parSetName(fields[i].getName());
//                //校验是否有GETTER、SETTER的方法
//                if (!StringUtils.checkGetMet(methods, fieldGetName) || StringUtils.checkSetMet(methods, fieldSetName)) {
//                    continue;
//                }
//                Method fieldSetMet = cls.getMethod(fieldGetName);
//                Object o =fieldSetMet.invoke(bean);
//                //Type conversion
//                if(null==o) {
//                    continue;
//                }
//                String value = o.toString();
//                switch (fieldType){
//                    case Constant.BASIC_TYPE_INTEGER:
//                        predicate=doInteger(cb,root,fields[i],value);
//                        break;
//                    case Constant.BASIC_TYPE_BIGDECIMAL:
//                        predicate = cb.equal(root.get(fields[i].getName()).as(BigDecimal.class), new BigDecimal(value));
//                        break;
//                    case Constant.BASIC_TYPE_Long:
//                        predicate = cb.equal(root.get(fields[i].getName()).as(Long.class), Long.valueOf(value));
//                        break;
//                    case Constant.BASIC_TYPE_DATE:
//                        predicate = cb.equal(root.get(fields[i].getName()).as(Date.class), DateUtils.parseDate(value));
//                        break;
//                    case Constant.BASIC_TYPE_INT:
////                        predicate = cb.equal(root.get(fields[i].getName()).as(Integer.class), Integer.valueOf(value));
//                        predicate=doInteger(cb,root,fields[i],value);
//                        break;
//
//                    default:
//                        predicate = cb.equal(root.get(fields[i].getName()).as(String.class), value);
//                        break;
//                }
//                lp.add(predicate);
//            }
//        } catch (SecurityException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        } catch (NumberFormatException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        } catch (NoSuchMethodException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        } catch (IllegalAccessException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        } catch (IllegalArgumentException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        } catch (InvocationTargetException e) {
//            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
//        }
//    }

    /**
     * @Description
     * @param cb
     * @param root
     * @param field
     * @param value
     * @return javax.persistence.criteria.Predicate
     * @throws
     * @date 20/12/22 14:39
     */
    public static Predicate doInteger(CriteriaBuilder cb,Root<?> root,Field field,String value){
        return cb.equal(root.get(field.getName()).as(Integer.class), Integer.valueOf(value));
    }

}
