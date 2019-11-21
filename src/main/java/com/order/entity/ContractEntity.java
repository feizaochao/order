package com.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @Description: 合同实体
* @author Robb G C LUO
* @date 2019年11月19日
* @version v1.0
*/
@Entity
@Table(name = "e_contract")
public class ContractEntity extends BaseEntity {

	private static final long serialVersionUID = -3922494873886316493L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*----基本信息----*/
	/*站点名称*/
	@Column
	private String siteName;
	
	/*合同名称*/
	@Column
	private String contractName;
	
	/*合同编号*/
	@Column
	private String contractNo;
	
	/*合同甲方*/
	@Column
	private String partyA;
	
	/*合同乙方*/
	@Column
	private String partyB;
	
	/*合同开始时间*/
	@Column
	private String contractStartTime;
	
	/*合同结束时间*/
	@Column
	private String contractEndTime;
	
	/*合同金额*/
	@Column
	private BigDecimal contractAmount;
	
	/*电费*/
	@Column
	private BigDecimal electricityFee;
	
	/*---- 电费情况 ----*/
	/*开始时间*/
	@Column
	private String startTime;
	
	/*结束时间*/
	@Column
	private String endTime;
	
	/*应收电费*/
	@Column
	private BigDecimal electricityCharge;
	
	/*电费资料提交情况*/
	@Column
	private int electricitySubmitType;
	
	/*已交费用给物业*/
	@Column
	private BigDecimal electricityPaid;

	/*缴费日期*/
	@Column
	private String paidTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getContractStartTime() {
		return contractStartTime;
	}

	public void setContractStartTime(String contractStartTime) {
		this.contractStartTime = contractStartTime;
	}

	public String getContractEndTime() {
		return contractEndTime;
	}

	public void setContractEndTime(String contractEndTime) {
		this.contractEndTime = contractEndTime;
	}

	public BigDecimal getElectricityFee() {
		return electricityFee;
	}

	public void setElectricityFee(BigDecimal electricityFee) {
		this.electricityFee = electricityFee;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getElectricityCharge() {
		return electricityCharge;
	}

	public void setElectricityCharge(BigDecimal electricityCharge) {
		this.electricityCharge = electricityCharge;
	}

	public int getElectricitySubmitType() {
		return electricitySubmitType;
	}

	public void setElectricitySubmitType(int electricitySubmitType) {
		this.electricitySubmitType = electricitySubmitType;
	}

	public BigDecimal getElectricityPaid() {
		return electricityPaid;
	}

	public void setElectricityPaid(BigDecimal electricityPaid) {
		this.electricityPaid = electricityPaid;
	}

	public String getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(String paidTime) {
		this.paidTime = paidTime;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}
}
