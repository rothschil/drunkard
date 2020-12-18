package xyz.wongs.drunkard.base.persistence.jpa.util;

import xyz.wongs.drunkard.base.constant.Constant;
import xyz.wongs.drunkard.base.message.enums.ResultCode;
import xyz.wongs.drunkard.base.message.exception.DrunkardException;
import xyz.wongs.drunkard.base.utils.DateUtils;
import xyz.wongs.drunkard.base.utils.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
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
public class MethodUtil {


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
    public static void getFieldValue(Object bean, Root<?> root, CriteriaBuilder cb, List<Predicate> lp){
        try {
            Class<?> cls = bean.getClass();
            Field[] fields = cls.getDeclaredFields();
            Method[] methods = cls.getDeclaredMethods();
            Predicate predicate = null;

            for (int i=0;i<fields.length;i++) {
                String fieldType = fields[i].getType().getSimpleName();
                String fieldGetName = StringUtils.parGetName(fields[i].getName());
                String fieldSetName = StringUtils.parSetName(fields[i].getName());
                //校验是否有GETTER、SETTER的方法
                if (!StringUtils.checkGetMet(methods, fieldGetName) || StringUtils.checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldGetName);
                Object o =fieldSetMet.invoke(bean);
                //Type conversion
                if(null==o) {
                    continue;
                }
                String value = o.toString();
                switch (fieldType){
                    case Constant.BASIC_TYPE_INTEGER:
                        predicate = cb.equal(root.get(fields[i].getName()).as(Integer.class), Integer.valueOf(value));
                        break;
                    case Constant.BASIC_TYPE_BIGDECIMAL:
                        predicate = cb.equal(root.get(fields[i].getName()).as(BigDecimal.class), new BigDecimal(value));
                        break;
                    case Constant.BASIC_TYPE_Long:
                        predicate = cb.equal(root.get(fields[i].getName()).as(Long.class), Long.valueOf(value));
                        break;
                    case Constant.BASIC_TYPE_DATE:
                        predicate = cb.equal(root.get(fields[i].getName()).as(Date.class), DateUtils.parseDate(value));
                        break;
                    case Constant.BASIC_TYPE_INT:
                        predicate = cb.equal(root.get(fields[i].getName()).as(Integer.class), Integer.valueOf(value));
                        break;
                    default:
                        predicate = cb.equal(root.get(fields[i].getName()).as(String.class), value);
                        break;
                }
                lp.add(predicate);
            }
        } catch (SecurityException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        } catch (NumberFormatException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        } catch (NoSuchMethodException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        } catch (IllegalAccessException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        } catch (IllegalArgumentException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        } catch (InvocationTargetException e) {
            throw new DrunkardException(ResultCode.DATA_PARSE_EXCEPTION,e);
        }
    }



}
