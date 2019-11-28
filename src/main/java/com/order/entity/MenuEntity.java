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
* @Description: TODO
* @author lguochao
* @date 2019年11月6日
* @version V1.0
*/
@ApiModel("菜单实例")
@Entity
@Table(name = "e_menu")
public class MenuEntity extends BaseEntity {

	private static final long serialVersionUID = 2745180556084333489L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", dataType = "long")
	private Long id;
	
	@Column
	@ApiModelProperty(value = "name", dataType = "string")
	private String name;
	
	@Column
	@ApiModelProperty(value = "path", dataType = "string")
	private String path;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
