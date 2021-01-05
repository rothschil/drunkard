package xyz.wongs.drunkard.base.persistence.jpa.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


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
public abstract class BaseOracle4Date<PK extends Serializable> extends AbsEntity<PK> {

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
