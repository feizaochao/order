package com.order.entity;
/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月20日
* @version v1.0
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "e_customer")
public class CustomerEntity extends BaseEntity {

	private static final long serialVersionUID = 8628307307476563043L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*客户名称*/
	@Column
	private String name;
	
	/*片区号*/
	@Column
	private Long areaId;
	
	/*联系人*/
	@Column
	private String contact;
	
	/*商城编号*/
	@Column
	private String mallNo;
	
	/*证件号*/
	@Column
	private String licenseNo;
	
	/*存留信息*/
	@Column
	private int persist;
	
	/*证件地址*/
	@Column
	private String licenseAddress;
	
	/*地址*/
	@Column
	private String address;
	
	/*备注*/
	@Column
	private String remarks;

	@Transient
	private String areaName;
	
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

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMallNo() {
		return mallNo;
	}

	public void setMallNo(String mallNo) {
		this.mallNo = mallNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public int getPersist() {
		return persist;
	}

	public void setPersist(int persist) {
		this.persist = persist;
	}

	public String getLicenseAddress() {
		return licenseAddress;
	}

	public void setLicenseAddress(String licenseAddress) {
		this.licenseAddress = licenseAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
