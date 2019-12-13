package com.order.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
@ApiModel("合同实体")
@Entity
@Table(name = "e_contract")
public class ContractEntity extends BaseEntity {

	private static final long serialVersionUID = -3922494873886316493L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(value = "id", dataType = "long")
	@ExcelIgnore
	private Long id;
	/*----基本信息----*/
	/*站点名称*/
	@Column
	@ApiModelProperty(value = "站点名称", dataType = "string")
	@ExcelProperty("站点名称")
	private String siteName;
	
	/*合同名称*/
	@Column
	@ApiModelProperty(value = "合同名称", dataType = "string")
	@ExcelProperty("合同名称")
	private String contractName;
	
	/*合同编号*/
	@Column
	@ApiModelProperty(value = "合同编号", dataType = "string")
	@ExcelProperty("合同编号")
	private String contractNo;
	
	/*合同甲方*/
	@Column
	@ApiModelProperty(value = "合同甲方", dataType = "string")
	@ExcelProperty("合同甲方")
	private String partyA;
	
	/*合同乙方*/
	@Column
	@ApiModelProperty(value = "合同乙方", dataType = "string")
	@ExcelProperty("合同乙方")
	private String partyB;
	
	/*合同开始时间*/
	@Column
	@ApiModelProperty(value = "合同开始时间", dataType = "string")
	@ExcelProperty("合同开始时间")
	private String contractStartTime;
	
	/*合同结束时间*/
	@Column
	@ApiModelProperty(value = "合同结束时间", dataType = "string")
	@ExcelProperty("合同结束时间")
	private String contractEndTime;
	
	/*合同金额*/
	@Column
	@ApiModelProperty(value = "合同金额", dataType = "double")
	@ExcelProperty("合同金额")
	private BigDecimal contractAmount;
	
	/*电费*/
	@Column
	@ApiModelProperty(value = "电费", dataType = "double")
	@ExcelProperty("电费")
	private BigDecimal electricityFee;
	
	/*---- 电费情况 ----*/
	/*开始时间*/
	@Column
	@ApiModelProperty(value = "开始时间", dataType = "string")
	@ExcelProperty("开始时间")
	private String startTime;
	
	/*结束时间*/
	@Column
	@ApiModelProperty(value = "结束时间", dataType = "string")
	@ExcelProperty("结束时间")
	private String endTime;
	
	/*应收电费*/
	@Column
	@ApiModelProperty(value = "应收电费", dataType = "double")
	@ExcelProperty("应收电费")
	private BigDecimal electricityCharge;
	
	/*电费资料提交情况*/
	@Column
	@ApiModelProperty(value = "电费资料提交情况", dataType = "int")
	@ExcelProperty("电费资料提交情况")
	private String electricitySubmitType;
	
	/*已交费用给物业*/
	@Column
	@ApiModelProperty(value = "已交费用给物业", dataType = "double")
	@ExcelProperty("已交费用给物业")
	private BigDecimal electricityPaid;

	/*缴费日期*/
	@Column
	@ApiModelProperty(value = "缴费日期", dataType = "string")
	@ExcelProperty("缴费日期")
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

	public String getElectricitySubmitType() {
		return electricitySubmitType;
	}

	public void setElectricitySubmitType(String electricitySubmitType) {
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
