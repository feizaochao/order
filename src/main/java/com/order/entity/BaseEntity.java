package com.order.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 8567638791949702216L;

	@Column(name = "create_time", length = 10, nullable = true)
	@ApiModelProperty(value = "新增时间", dataType = "date")
	@ExcelProperty("新增时间")
	private Date createTime;
	
	@Column(name = "update_time", length = 10, nullable = true)
	@ApiModelProperty(value = "修改时间", dataType = "date")
	@ExcelProperty("修改时间")
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
