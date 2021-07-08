package xyz.wongs.drunkard.base.persistence.jpa.entity;

import org.springframework.format.annotation.DateTimeFormat;
import xyz.wongs.drunkard.base.po.BasePo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

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
public abstract class SuperOrcDate<PK extends Serializable> extends AbstractPo<PK> {

	private static final long serialVersionUID = 1L;

	@Column(name="UPDATE_DATE", columnDefinition="DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updateDate;

	@Column(name="STATUS_DATE", columnDefinition="DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date statusDate;

	@Column(name = "STATUS_CD",length = 4)
	private String statusCd;

	@Column(name = "CREATE_USER",length = 12)
	private Long createUser;

	@Column(name = "UPDATE_USER",length = 12)
	private Long updateUser;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
}
