package com.order.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* @Description: TODO
* @author lguochao
* @date 2019年11月6日
* @version V1.0
*/
@Entity
@Table(name = "e_user")
@ApiModel("用户信息")
public class UserEntity extends BaseEntity {

	private static final long serialVersionUID = -2591814027138683931L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", dataType = "long")
	private Long id;
	
	@Column
	@ApiModelProperty(value = "用户名", dataType = "string")
	private String name;
	
	@Column
	@ApiModelProperty(value = "密码", dataType = "string")
	private String password;
	
	@Transient
	@ApiModelProperty(value = "角色名", dataType = "string")
	private String roleName;

	@Transient
	@ApiModelProperty(value = "角色id", dataType = "long")
	private Long roleId;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
