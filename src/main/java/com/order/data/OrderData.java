package com.order.data;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/5
 */
public class OrderData {

    @ExcelProperty({"订单信息", "订单号"})
    private String orderNo;

    @ExcelProperty({"订单信息", "归属项目"})
    private String ownerProject;

    @ExcelProperty({"订单信息", "业态"})
    private String status;

    @ExcelProperty({"订单信息", "是否使用我司网络"})
    private String isUseNetWork;

    @ExcelProperty({"订单信息", "区划号"})
    private int areaNumber;

    @ExcelProperty({"客户信息", "客户名称"})
    private String customerName;

    @ExcelProperty({"客户信息", "联系人"})
    private String contact;

    @ExcelProperty({"客户信息", "商城编号"})
    private String mailNo;

    @ExcelProperty({"客户信息", "证件号"})
    private String licenseNo;

    @ExcelProperty({"客户信息", "客户存留信息"})
    private String persist;

    @ExcelProperty({"客户信息", "证件地址"})
    private String licenseAddress;

    @ExcelProperty({"客户信息", "地址"})
    private String address;

    @ExcelProperty({"客户信息", "备注"})
    private String remarks;

    @ExcelProperty({"客户信息", "片区名"})
    private String areaName;

    @ExcelProperty({"合同信息", "站点名称"})
    private String siteName;

    @ExcelProperty({"合同信息", "合同编号"})
    private String contractNo;

    @ExcelProperty({"合同信息", "合同甲方"})
    private String partyA;

    @ExcelProperty({"合同信息", "合同乙方"})
    private String partyB;

    @ExcelProperty({"合同信息", "合同开始时间"})
    private String contractStartTime;

    @ExcelProperty({"合同信息", "合同结束时间"})
    private String contractEndTime;

    @ExcelProperty({"合同信息", "合同金额"})
    private BigDecimal contractAmount;

    @ExcelProperty({"合同信息", "电费"})
    private BigDecimal electricityFee;

    @ExcelProperty({"合同信息", "开始时间"})
    private String startTime;

    @ExcelProperty({"合同信息", "结束时间"})
    private String endTime;

    @ExcelProperty({"合同信息", "应收电费"})
    private BigDecimal electricityCharge;

    @ExcelProperty({"合同信息", "电费资料提交情况"})
    private String electricitySubmitType;

    @ExcelProperty({"合同信息", "已交费用给物业"})
    private BigDecimal electricityPaid;

    @ExcelProperty({"合同信息", "缴费日期"})
    private String paidTime;

    @ExcelProperty({"宽带信息", "运营商"})
    private String operator;

    @ExcelProperty({"宽带信息", "宽带类型"})
    private String type;

    @ExcelProperty({"宽带信息", "宽带资费"})
    private BigDecimal price;

    @ExcelProperty({"宽带信息", "固话"})
    private String phone;

    @ExcelProperty({"宽带信息", "固话数量"})
    private int phoneNum;

    @ExcelProperty({"宽带信息", "语音资费"})
    private BigDecimal voiceTariff;

    @ExcelProperty({"收费信息", "每周期收费金额"})
    private BigDecimal chargeAmount;

    @ExcelProperty({"收费信息", "收费日期"})
    private String chargeDate;

    @ExcelProperty({"收费信息", "下期续费日期"})
    private String nextChargeDate;

    @ExcelProperty({"开票信息", "是否开票"})
    private String isInvoice;

    @ExcelProperty({"开票信息", "开票类型"})
    private String invoiceType;

    @ExcelProperty({"开票信息", "开票日期"})
    private String invoiceDate;

    @ExcelProperty({"开票信息", "开票号码"})
    private String invoiceNum;

    @ExcelProperty({"新业务信息", "网络业务申请单"})
    private String requestOrder;

    @ExcelProperty({"新业务信息", "月份"})
    private String month;

    @ExcelProperty({"新业务信息", "受理日期"})
    private String date;

    @ExcelProperty({"新业务信息", "对口人"})
    private String counterpart;

    @ExcelProperty({"运营商信息", "产品号"})
    private String productNum;

    @ExcelProperty({"运营商信息", "账号状态"})
    private String accountStatus;

    @ExcelProperty({"运营商信息", "上网账号"})
    private String internetAccount;

    @ExcelProperty({"运营商信息", "宽带"})
    private String broadband;

    @ExcelProperty({"运营商信息", "固话"})
    private String operatorPhone;

    @ExcelProperty({"运营商信息", "运营商缴费周期"})
    private String paymentCycle;

    @ExcelProperty({"运营商信息", "缴费日期"})
    private String paymentDate;

    @ExcelProperty({"运营商信息", "运营商开票日期"})
    private String operatorInvoiceDate;

    @ExcelProperty({"运营商信息", "联系人"})
    private String operatorContact;

    @ExcelProperty({"运营商信息", "下次续约日期"})
    private String renewContract;

    @ExcelProperty({"运营商信息", "发票号码"})
    private String operatorInvoiceNum;

    @ExcelProperty({"运营商信息", "一期收费"})
    private BigDecimal primaryCharge;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsUseNetWork() {
        return isUseNetWork;
    }

    public void setIsUseNetWork(String isUseNetWork) {
        this.isUseNetWork = isUseNetWork;
    }

    public int getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(int areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getPersist() {
        return persist;
    }

    public void setPersist(String persist) {
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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public BigDecimal getVoiceTariff() {
        return voiceTariff;
    }

    public void setVoiceTariff(BigDecimal voiceTariff) {
        this.voiceTariff = voiceTariff;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(String chargeDate) {
        this.chargeDate = chargeDate;
    }

    public String getNextChargeDate() {
        return nextChargeDate;
    }

    public void setNextChargeDate(String nextChargeDate) {
        this.nextChargeDate = nextChargeDate;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getRequestOrder() {
        return requestOrder;
    }

    public void setRequestOrder(String requestOrder) {
        this.requestOrder = requestOrder;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCounterpart() {
        return counterpart;
    }

    public void setCounterpart(String counterpart) {
        this.counterpart = counterpart;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
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

    public String getOperatorPhone() {
        return operatorPhone;
    }

    public void setOperatorPhone(String operatorPhone) {
        this.operatorPhone = operatorPhone;
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

    public String getOperatorInvoiceDate() {
        return operatorInvoiceDate;
    }

    public void setOperatorInvoiceDate(String operatorInvoiceDate) {
        this.operatorInvoiceDate = operatorInvoiceDate;
    }

    public String getOperatorContact() {
        return operatorContact;
    }

    public void setOperatorContact(String operatorContact) {
        this.operatorContact = operatorContact;
    }

    public String getRenewContract() {
        return renewContract;
    }

    public void setRenewContract(String renewContract) {
        this.renewContract = renewContract;
    }

    public String getOperatorInvoiceNum() {
        return operatorInvoiceNum;
    }

    public void setOperatorInvoiceNum(String operatorInvoiceNum) {
        this.operatorInvoiceNum = operatorInvoiceNum;
    }

    public BigDecimal getPrimaryCharge() {
        return primaryCharge;
    }

    public void setPrimaryCharge(BigDecimal primaryCharge) {
        this.primaryCharge = primaryCharge;
    }
}
