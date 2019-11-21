package com.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
* @Description: TODO
* @author LGC
* @date 2019年4月26日
* @version V1.0
*/
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8567638791949702216L;

	@Column(name = "create_time", length = 10, nullable = true)
	private Date createTime;
	
	@Column(name = "update_time", length = 10, nullable = true)
	private Date updateTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
