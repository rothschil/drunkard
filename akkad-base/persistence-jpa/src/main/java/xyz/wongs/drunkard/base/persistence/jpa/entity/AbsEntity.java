package xyz.wongs.drunkard.base.persistence.jpa.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Persistable;
import xyz.wongs.drunkard.base.entity.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  ┏┓　　　┏┓
 *┏┛┻━━━┛┻┓
 *┃　　　　　　　┃ 　
 *┃　　　━　　　┃
 *┃　┳┛　┗┳　┃
 *┃　　　　　　　┃
 *┃　　　┻　　　┃
 *┃　　　　　　　┃
 *┗━┓　　　┏━┛
 *　　┃　　　┃神兽保佑
 *　　┃　　　┃代码无BUG！
 *　　┃　　　┗━━━┓
 *　　┃　　　　　　　┣┓
 *　　┃　　　　　　　┏┛
 *　　┗┓┓┏━┳┓┏┛
 *　　　┃┫┫　┃┫┫
 *　　　┗┻┛　┗┻┛
 * @ClassName AbstractEntity
 * @Description 抽象实体基类，如果主键是数据库端自动生成 请使用{@link BaseEntity}，如果是Oracle 请使用{@link BaseOracleEntity}
 * @author WCNGS@QQ.COM
 * @date 2018/11/15 18:45
 * @Version 1.0.0
*/
@SuppressWarnings({})
@MappedSuperclass
public abstract class AbsEntity<ID extends Serializable> extends AbstractEntity<ID> implements Persistable<ID> {


	private static final long serialVersionUID = 1L;

	@Override
	public abstract ID getId();

    /**
     * Sets the id of the entity.
     *
     * @param id the id to set
     */
    public abstract void setId(final ID id);

//    @Column(name="CREATE_DATE", columnDefinition="DATE")
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @Temporal(value = TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }

    @Override
    public boolean isNew() {

        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        AbsEntity<?> that = (AbsEntity<?>) obj;

        return null == this.getId() ? false : this.getId().equals(that.getId());
    }


    @Override
    public int hashCode() {

        int hashCode = 17;

        hashCode += null == getId() ? 0 : getId().hashCode() * 31;

        return hashCode;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
