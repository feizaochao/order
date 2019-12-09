package com.order.data;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.order.entity.*;
import com.order.service.OrderService;
import com.qiniu.util.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Robb G C LUO
 * @version v1.0
 * @Description: TODO
 * @date 2019/12/9
 */
public class OrderUploadListener extends AnalysisEventListener<OrderData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderUploadListener.class);

    private OrderService orderService;

    public OrderUploadListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void invoke(OrderData orderData, AnalysisContext analysisContext) {
        LOGGER.info("解析到一条数据：{}", Json.encode(orderData));

        // 订单信息
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(orderData.getOrderNo());
        orderEntity.setOwnerProject(orderData.getOwnerProject());
        // orderEntity.setStatus(orderData.getStatus()); // 需要枚举转换
        // orderEntity.setIsUseNetwork(orderData.getIsUseNetWork());
        orderEntity.setAreaNumber(orderData.getAreaNumber());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(orderData.getCustomerName());
        customerEntity.setContact(orderData.getContact());
        customerEntity.setMallNo(orderData.getMailNo());
        customerEntity.setLicenseNo(orderData.getLicenseNo());
        customerEntity.setLicenseAddress(orderData.getLicenseAddress());
        // customerEntity.setPersist(orderData.getPersist());
        customerEntity.setAddress(orderData.getAddress());
        customerEntity.setRemarks(orderData.getRemarks());
        customerEntity.setAreaName(orderData.getAreaName());
        orderEntity.setCustomer(customerEntity);

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setSiteName(orderData.getSiteName());
        contractEntity.setContractNo(orderData.getContractNo());
        contractEntity.setPartyA(orderData.getPartyA());
        contractEntity.setPartyB(orderData.getPartyB());
        contractEntity.setContractStartTime(orderData.getContractStartTime());
        contractEntity.setContractEndTime(orderData.getContractEndTime());
        contractEntity.setContractAmount(orderData.getContractAmount());
        contractEntity.setElectricityFee(orderData.getElectricityFee());
        contractEntity.setStartTime(orderData.getStartTime());
        contractEntity.setEndTime(orderData.getEndTime());
        contractEntity.setElectricityCharge(orderData.getElectricityCharge());
//        contractEntity.setElectricitySubmitType(orderData.getElectricitySubmitType());
        contractEntity.setElectricityPaid(orderData.getElectricityPaid());
        contractEntity.setPaidTime(orderData.getPaidTime());
        orderEntity.setContract(contractEntity);

        OrderBroadbandEntity broadbandEntity = new OrderBroadbandEntity();
        broadbandEntity.setOperator(orderData.getOperator());
        broadbandEntity.setType(orderData.getType());
        broadbandEntity.setPrice(orderData.getPrice());
        broadbandEntity.setPhone(orderData.getPhone());
        broadbandEntity.setPhoneNum(orderData.getPhoneNum());
        broadbandEntity.setVoiceTariff(orderData.getVoiceTariff());
        orderEntity.setBroadband(broadbandEntity);

        OrderChargeEntity chargeEntity = new OrderChargeEntity();
        chargeEntity.setChargeAmount(orderData.getChargeAmount());
        chargeEntity.setChargeDate(orderData.getChargeDate());
        chargeEntity.setNextChargeDate(orderData.getNextChargeDate());
        orderEntity.setCharge(chargeEntity);

        OrderInvoiceEntity invoiceEntity = new OrderInvoiceEntity();
//        invoiceEntity.setIsInvoice(orderData.getIsInvoice());
//        invoiceEntity.setType(orderData.getInvoiceType());
        invoiceEntity.setInvoiceDate(orderData.getInvoiceDate());
        invoiceEntity.setInvoiceNum(orderData.getInvoiceNum());
        orderEntity.setInvoice(invoiceEntity);

        OrderNewBusinessEntity newBusinessEntity = new OrderNewBusinessEntity();
        newBusinessEntity.setRequestOrder(orderData.getRequestOrder());
        newBusinessEntity.setMonth(orderData.getMonth());
        newBusinessEntity.setDate(orderData.getDate());
        newBusinessEntity.setCounterpart(orderData.getCounterpart());
        orderEntity.setNewBusiness(newBusinessEntity);

        OrderOperatorEntity operatorEntity = new OrderOperatorEntity();
        operatorEntity.setProductNum(orderData.getProductNum());
//        operatorEntity.setAccountStatus(orderData.getAccountStatus());
        operatorEntity.setInternetAccount(orderData.getInternetAccount());
        operatorEntity.setBroadband(orderData.getBroadband());
        operatorEntity.setPhone(orderData.getOperatorPhone());
        operatorEntity.setPaymentCycle(orderData.getPaymentCycle());
        operatorEntity.setPaymentDate(orderData.getPaymentDate());
        operatorEntity.setInvoiceDate(orderData.getOperatorInvoiceDate());
        operatorEntity.setContact(orderData.getOperatorContact());
        operatorEntity.setRenewContract(orderData.getRenewContract());
        operatorEntity.setPrimaryCharge(orderData.getPrimaryCharge());
        orderEntity.setOperator(operatorEntity);

        orderService.addOrder(orderEntity);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        LOGGER.info("所有数据解析完成");
    }
}
