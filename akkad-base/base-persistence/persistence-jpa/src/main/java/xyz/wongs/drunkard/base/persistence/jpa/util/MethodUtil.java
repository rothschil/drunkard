package xyz.wongs.drunkard.base.persistence.jpa.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.persistence.jpa.entity.AbstractPo;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;

import javax.annotation.Nullable;
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
 * @date 2018/7/4 20:54
 **/
@Slf4j
public class MethodUtil {

    public static Specification getSpecification(AbstractPo t){
        return new Specification<AbstractPo>() {
            @Override
            public Predicate toPredicate(@Nullable Root<AbstractPo> root, @Nullable CriteriaQuery<?> query,@Nullable CriteriaBuilder cb) {
                List<Predicate> list = MethodUtil.getFieldValue(t,root,cb);
                Predicate[] pre = new Predicate[list.size()];
                pre = list.toArray(pre);
                assert query != null;
                CriteriaQuery<?> quy = query.where(pre);
                return quy.getRestriction();
            }
        };
    }

    /**
     * @Description
     * @param entity 实体基类
     * @param root root
     * @param cb    CriteriaBuilder
     * @date 20/12/18 10:12
     */
    public static List<Predicate> getFieldValue(AbstractPo entity, Root<?> root, CriteriaBuilder cb) {
        List<Predicate> lp = new ArrayList<>();
        Class<?> cls = entity.getClass();
        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();
        Predicate predicate;
        for (Field field : fields) {
            try {
                //校验是否有GETTER、SETTER的方法
                if(!checkGetSetMethodAndAnnotation(methods,field)){
                    continue;
                }
                String fieldGetName = StringUtils.parGetName(field.getName());

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
                    case Constant.BASIC_TYPE_INT:
                        predicate = doInteger(cb, root, field, value);
                        break;
                    case Constant.BASIC_TYPE_BIG_DECIMAL:
                        predicate = cb.equal(root.get(field.getName()).as(BigDecimal.class), new BigDecimal(value));
                        break;
                    case Constant.BASIC_TYPE_LONG:
                        predicate = cb.equal(root.get(field.getName()).as(Long.class), Long.valueOf(value));
                        break;
                    case Constant.BASIC_TYPE_DATE:
                        predicate = cb.equal(root.get(field.getName()).as(Date.class), DateUtils.parseDate(value));
                        break;
                    default:
                        predicate = cb.equal(root.get(field.getName()).as(String.class), value);
                        break;
                }
                lp.add(predicate);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(), e.getCause());
            }
        }
        return lp;
    }

    /** 检查Set Get方法以及忽略Transient 的注解
     * @Description
     * @param methods Null
     * @param field Null
     * @return boolean
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

    /**
     * @Description
     * @param cb No
     * @param root No
     * @param field No
     * @param value No
     * @return javax.persistence.criteria.Predicate
     * @date 20/12/22 14:39
     */
    public static Predicate doInteger(CriteriaBuilder cb,Root<?> root,Field field,String value){
        return cb.equal(root.get(field.getName()).as(Integer.class), Integer.valueOf(value));
    }

}
