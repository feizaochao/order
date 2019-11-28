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
* @author Robb G C LUO
* @date 2019年11月21日
* @version v1.0
*/
@ApiModel("订单信息")
@Entity
@Table(name = "e_order")
public class OrderEntity extends BaseEntity {

	private static final long serialVersionUID = -8684608025296648260L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "long")
	private Long id;
	
	/*订单号*/
	@Column
    @ApiModelProperty(value = "订单号", dataType = "string")
	private String orderNo;
	
	/*归属项目*/
	@Column
    @ApiModelProperty(value = "归属项目", dataType = "string")
	private String ownerProject;
	
	/*业态*/
	@Column(nullable = true)
    @ApiModelProperty(value = "业态", dataType = "int")
	private int status;
	
	/*是否使用我司网络*/
	@Column(nullable = true)
    @ApiModelProperty(value = "是否使用我司网络", dataType = "int")
	private int isUseNetwork;
	
	/*区划号*/
	@Column(nullable = true)
    @ApiModelProperty(value = "区划号", dataType = "int")
	private int areaNumber;
	
	/*客户*/
	@Column
    @ApiModelProperty(value = "客户信息", dataType = "long")
	private Long customerId;
	
	/*合同*/
	@Column
    @ApiModelProperty(value = "合同信息", dataType = "long")
	private Long contractId;

	/*宽带信息*/
	@Column
    @ApiModelProperty(value = "宽带信息", dataType = "long")
	private Long broadbandId;

	/*收费信息*/
	@Column
    @ApiModelProperty(value = "收费信息", dataType = "long")
	private Long chargeId;

	/*开票信息*/
	@Column
    @ApiModelProperty(value = "开票信息", dataType = "long")
	private Long invoiceId;

	/*新业务信息*/
	@Column
    @ApiModelProperty(value = "新业务信息", dataType = "long")
	private Long newBusinessId;

	/*需缴纳运营商套餐成本信息*/
	@Column
    @ApiModelProperty(value = "需缴纳运营商套餐成本信息", dataType = "long")
	private Long operatorId;

	/*备注*/
	@Column
    @ApiModelProperty(value = "备注", dataType = "string")
	private String remarks;

	@Transient
    @ApiModelProperty(value = "客户实体", dataType = "object")
	private CustomerEntity customer;
	
	@Transient
    @ApiModelProperty(value = "合同实体", dataType = "object")
	private ContractEntity contract;

	@Transient
    @ApiModelProperty(value = "宽带实体", dataType = "object")
	private OrderBroadbandEntity broadband;

	@Transient
    @ApiModelProperty(value = "收费实体", dataType = "object")
	private OrderChargeEntity charge;

	@Transient
    @ApiModelProperty(value = "开票实体", dataType = "object")
	private OrderInvoiceEntity invoice;

	@Transient
    @ApiModelProperty(value = "新业务实体", dataType = "object")
	private OrderNewBusinessEntity newBusiness;

	@Transient
    @ApiModelProperty(value = "运营商实体", dataType = "object")
	private OrderOperatorEntity operator;

	@Transient
    @ApiModelProperty(value = "片区名", dataType = "string")
	private String areaName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getOwnerProject() {
        return ownerProject;
    }

    public void setOwnerProject(String ownerProject) {
        this.ownerProject = ownerProject;
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

    public Long getBroadbandId() {
        return broadbandId;
    }

    public void setBroadbandId(Long broadbandId) {
        this.broadbandId = broadbandId;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getNewBusinessId() {
        return newBusinessId;
    }

    public void setNewBusinessId(Long newBusinessId) {
        this.newBusinessId = newBusinessId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
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

    public OrderBroadbandEntity getBroadband() {
        return broadband;
    }

    public void setBroadband(OrderBroadbandEntity broadband) {
        this.broadband = broadband;
    }

    public OrderChargeEntity getCharge() {
        return charge;
    }

    public void setCharge(OrderChargeEntity charge) {
        this.charge = charge;
    }

    public OrderInvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderInvoiceEntity invoice) {
        this.invoice = invoice;
    }

    public OrderNewBusinessEntity getNewBusiness() {
        return newBusiness;
    }

    public void setNewBusiness(OrderNewBusinessEntity newBusiness) {
        this.newBusiness = newBusiness;
    }

    public OrderOperatorEntity getOperator() {
        return operator;
    }

    public void setOperator(OrderOperatorEntity operator) {
        this.operator = operator;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
