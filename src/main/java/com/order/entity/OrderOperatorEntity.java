package com.order.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-运营商信息
 * @date 2019/11/22
 */
@ApiModel("订单-运营商信息")
@Entity
@Table(name = "e_order_operator")
public class OrderOperatorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "id", dataType = "long")
    private Long id;

    /*产品号*/
    @Column
    @ApiModelProperty(value = "产品号", dataType = "string")
    private String productNum;

    /*账号状态*/
    @Column(nullable = true)
    @ApiModelProperty(value = "账号状态", dataType = "int")
    private int accountStatus;

    /*上网账号*/
    @Column
    @ApiModelProperty(value = "上网账号", dataType = "string")
    private String internetAccount;

    /*宽带*/
    @Column
    @ApiModelProperty(value = "宽带", dataType = "string")
    private String broadband;

    /*固话*/
    @Column
    @ApiModelProperty(value = "固话", dataType = "string")
    private String phone;

    /*运营商缴费周期*/
    @Column
    @ApiModelProperty(value = "运营商缴费周期", dataType = "string")
    private String paymentCycle;

    /*缴费日期*/
    @Column
    @ApiModelProperty(value = "缴费日期", dataType = "string")
    private String paymentDate;

    /*运营商开票日期*/
    @Column
    @ApiModelProperty(value = "运营商开票日期", dataType = "string")
    private String invoiceDate;

    /*联系人*/
    @Column
    @ApiModelProperty(value = "联系人", dataType = "string")
    private String contact;

    /*下次续约日期*/
    @Column
    @ApiModelProperty(value = "下次续约日期", dataType = "string")
    private String renewContract;

    /*发票号码*/
    @Column
    @ApiModelProperty(value = "发票号码", dataType = "string")
    private String invoiceNum;

    /*一期收费*/
    @Column
    @ApiModelProperty(value = "一期收费", dataType = "double")
    private BigDecimal primaryCharge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getInternetAccount() {
        return internetAccount;
    }

    public void setInternetAccount(String internetAccount) {
        this.internetAccount = internetAccount;
    }

    public String getBroadband() {
        return broadband;
    }

    public void setBroadband(String broadband) {
        this.broadband = broadband;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentCycle() {
        return paymentCycle;
    }

    public void setPaymentCycle(String paymentCycle) {
        this.paymentCycle = paymentCycle;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRenewContract() {
        return renewContract;
    }

    public void setRenewContract(String renewContract) {
        this.renewContract = renewContract;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public BigDecimal getPrimaryCharge() {
        return primaryCharge;
    }

    public void setPrimaryCharge(BigDecimal primaryCharge) {
        this.primaryCharge = primaryCharge;
    }
}
