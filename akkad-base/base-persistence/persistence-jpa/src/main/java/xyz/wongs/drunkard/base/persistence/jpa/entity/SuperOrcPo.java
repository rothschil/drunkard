package xyz.wongs.drunkard.base.persistence.jpa.entity;

import xyz.wongs.drunkard.base.po.BasePo;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法
 * <p> 如果是如mysql这种自动生成主键的，请参考{@link BasePo}
 * <p/>
 * 子类只需要在类头上加 @SequenceGenerator(name="seq", sequenceName="你的sequence名字")
 * <p/>
 * <p/>
 * @Author <a href="mailto:WCNGS@QQ.COM">Sam</a>
 * @Description //TODO
 * @Github <a>https://github.com/rothschil</a>
 * @date 2021/7/8 - 13:54
 * @Version 1.0.0
 */
@MappedSuperclass
public abstract class SuperOrcPo<PK extends Serializable> extends AbstractPo<PK> {
	private static final long serialVersionUID = 1L;


}
