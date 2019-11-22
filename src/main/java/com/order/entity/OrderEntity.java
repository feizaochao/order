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
	private String ownerProject;
	
	/*业态*/
	@Column(nullable = true)
	private int status;
	
	/*是否使用我司网络*/
	@Column(nullable = true)
	private int isUseNetwork;
	
	/*区划号*/
	@Column(nullable = true)
	private int areaNumber;
	
	/*客户*/
	@Column
	private Long customerId;
	
	/*合同*/
	@Column
	private Long contractId;

	/*宽带信息*/
	@Column
	private Long broadbandId;

	/*收费信息*/
	@Column
	private Long chargeId;

	/*开票信息*/
	@Column
	private Long invoiceId;

	/*新业务信息*/
	@Column
	private Long newBusinessId;

	/*需缴纳运营商套餐成本信息*/
	@Column
	private Long operatorId;

	/*备注*/
	@Column
	private String remarks;

	@Transient
	private CustomerEntity customer;
	
	@Transient
	private ContractEntity contract;

	@Transient
	private OrderBroadbandEntity broadband;

	@Transient
	private OrderChargeEntity charge;

	@Transient
	private OrderInvoiceEntity invoice;

	@Transient
	private OrderNewBusinessEntity newBusiness;

	@Transient
	private OrderOperatorEntity operator;

	@Transient
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
