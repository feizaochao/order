package com.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
@Entity
@Table(name = "e_order")
public class OrderEntity extends BaseEntity {

	private static final long serialVersionUID = -8684608025296648260L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*订单号*/
	@Column
	private String orderNo;
	
	/*归属项目*/
	@Column
	private String ownerPorject;
	
	/*业态*/
	@Column
	private int status;
	
	/*是否使用我司网络*/
	@Column
	private int isUseNetwork;
	
	/*区划号*/
	@Column
	private int areaNumber;
	
	/*客户*/
	@Column
	private Long customerId;
	
	/*合同*/
	@Column
	private Long contractId;

	@Transient
	private CustomerEntity customer;
	
	@Transient
	private ContractEntity contract;
	
	@Transient
	private String areaName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOwnerPorject() {
		return ownerPorject;
	}

	public void setOwnerPorject(String ownerPorject) {
		this.ownerPorject = ownerPorject;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsUseNetwork() {
		return isUseNetwork;
	}

	public void setIsUseNetwork(int isUseNetwork) {
		this.isUseNetwork = isUseNetwork;
	}

	public int getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(int areaNumber) {
		this.areaNumber = areaNumber;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public ContractEntity getContract() {
		return contract;
	}

	public void setContract(ContractEntity contract) {
		this.contract = contract;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
