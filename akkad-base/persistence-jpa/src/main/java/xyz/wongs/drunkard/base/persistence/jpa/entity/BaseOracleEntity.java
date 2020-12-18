package xyz.wongs.drunkard.base.persistence.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * <p> 如果是如mysql这种自动生成主键的，请参考{@link BaseEntity}
 * <p/>
 * 子类只需要在类头上加 @SequenceGenerator(name="seq", sequenceName="你的sequence名字")
 * <p/>
 * <p/>
 * @ClassName: BaseOracleEntity
 * @Description: TODO
 * @author schwarzchild
 * @date 2016年5月15日 下午9:00:02 * * @param <PK>
 */
@MappedSuperclass
public abstract class BaseOracleEntity<PK extends Serializable> extends AbsEntity<PK> {

	private static final long serialVersionUID = 1L;


}
