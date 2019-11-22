package com.order.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: 订单-运营商信息
 * @date 2019/11/22
 */
@Entity
@Table(name = "e_order_operator")
public class OrderOperatorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*产品号*/
    @Column
    private String productNum;

    /*账号状态*/
    @Column(nullable = true)
    private int accountStatus;

    /*上网账号*/
    @Column
    private String internetAccount;

    /*宽带*/
    @Column
    private String broadband;

    /*固话*/
    @Column
    private String phone;

    /*运营商缴费周期*/
    @Column
    private String paymentCycle;

    /*缴费日期*/
    @Column
    private String paymentDate;

    /*运营商开票日期*/
    @Column
    private String invoiceDate;

    /*联系人*/
    @Column
    private String contact;

    /*下次续约日期*/
    @Column
    private String renewContract;

    /*发票号码*/
    @Column
    private String invoiceNum;

    /*一期收费*/
    @Column
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
