package com.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @Description: 片区
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/
@ApiModel("片区信息")
@Entity
@Table(name = "e_area")
public class AreaEntity extends BaseEntity {

	private static final long serialVersionUID = 5419500821993004857L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", dataType = "long")
	private Long id;
	
	@Column
	@ApiModelProperty(value = "片区号", dataType = "string")
	private String areaNo;
	
	@Column
	@ApiModelProperty(value = "片区名", dataType = "string")
	private String areaName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
